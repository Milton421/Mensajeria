# Sistema de Control de Mensajería

Aplicación de escritorio (Java Swing + Maven + Oracle) basada en la **Fase 1 (Definición del sistema)**
y la **Fase 2 (Modelo relacional)**.

## Requisitos
- JDK 17 o superior
- Apache NetBeans 17+ (con soporte Maven)
- Oracle Database 12c o superior (XE 21c sirve) — usa columnas IDENTITY
- Conexión a internet la primera vez (Maven descarga: driver Oracle `ojdbc11`, `FlatLaf` y `OpenPDF`)

## 1. Preparar la base de datos
1. Conéctese como administrador (por ejemplo `SYSTEM`) y cree el usuario de la aplicación
   (en XE 21c, dentro del PDB `XEPDB1`):
   ```sql
   ALTER SESSION SET CONTAINER = XEPDB1;
   CREATE USER MENSAJERIA IDENTIFIED BY mensajeria123 QUOTA UNLIMITED ON USERS;
   GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE TO MENSAJERIA;
   ```
2. Conéctese como `MENSAJERIA` y ejecute **`sql/script_oracle.sql`** (crea tablas, restricciones y los roles
   Administrador / Operador).

## 2. Configurar la conexión
Copie `src/main/resources/db.properties.example` como `src/main/resources/db.properties` y edite sus valores
(o coloque un `db.properties` junto al `.jar`):
```
db.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
db.user=MENSAJERIA
db.password=mensajeria123
```
(Oracle XE 11g: `jdbc:oracle:thin:@localhost:1521:XE`.)

## 3. Abrir y ejecutar en NetBeans
1. `File > Open Project…` y seleccione la carpeta `SistemaMensajeria`.
2. Clic derecho en el proyecto > **Clean and Build** (descarga dependencias).
3. Clic derecho > **Run** (clase principal `com.mensajeria.Main`).

También por consola: `mvn package` y luego `java -jar target/SistemaMensajeria-1.0.jar`.

## Primer ingreso
La primera vez que se abre la aplicación y no hay usuarios, se crea automáticamente:
- Usuario: `admin`  — Contraseña: `admin123`

**Cámbiela de inmediato** en *Usuarios > Cambiar contraseña*. Las claves se guardan con hash PBKDF2, nunca en texto legible.

## Módulos
| Módulo | Qué hace | Quién |
|---|---|---|
| Inicio | Resumen de envíos por estado y pendientes recientes | Todos |
| Envíos | Crear envío, asignar mensajero, cambiar estado, buscar/filtrar | Admin y Operador |
| Clientes | Registrar, buscar, actualizar | Admin y Operador (eliminar: solo Admin) |
| Mensajeros | Registrar, buscar, actualizar | Edición solo Admin (Operador consulta) |
| Usuarios | Crear usuarios, roles, estado, cambio de clave | Solo Admin |
| Reportes | Listados en PDF: registrados, pendientes, entregados, fallidos, cancelados | Todos |

## Reglas de estados (Fase 1)
`Registrado → Asignado → En tránsito → Entregado | Entrega fallida`, y `Cancelado` antes de entregar.
Una *Entrega fallida* puede reasignarse a un mensajero. *Entregado* registra fecha y quién recibió.

## Cambios respecto al modelo de la Fase 2
La Fase 1 pide registrar al destinatario, la fecha de entrega y quién recibió, pero el modelo de la Fase 2 no
tenía esas columnas. Se agregaron a `ENVIO`: `nombre_destinatario`, `telefono_destinatario`, `fecha_entrega`,
`recibido_por`. Además `ENVIO.id_paquete` es `UNIQUE` para respetar la relación 1:1 ENVIO–PAQUETE.
Si su catedrático no acepta ese cambio, actualice también el modelo ER y el relacional.

## Estructura
```
sql/script_oracle.sql        Tablas, restricciones, roles
src/main/java/com/mensajeria/
  Main.java                  Punto de entrada
  db/Conexion.java           Conexión JDBC a Oracle
  model/                     Registros: Cliente, Mensajero, Paquete, Envio, Usuario, Rol
  dao/                       Acceso a datos (SQL con PreparedStatement)
  util/                      Seguridad (hash), Sesion, Estilo, ReportePDF
  ui/                        Formularios JFrame (.java + .form) para NetBeans
```

## Editar las pantallas en NetBeans

En **Source Packages > com.mensajeria.ui**, abra un archivo `*Frame.java` y seleccione
la pestaña **Design**. Cada pantalla tiene su archivo `.form` con los campos,
botones, tablas y distribución editables en el diseñador:

- `LoginFrame`: inicio de sesión.
- `MainFrame`: menú principal.
- `InicioFrame`: resumen de envíos.
- `ClienteFrame`, `MensajeroFrame` y `UsuarioFrame`: administración de registros.
- `EnvioFrame`: consulta y seguimiento de envíos.
- `NuevoEnvioFrame`: registro de un envío.
- `ReporteFrame`: consulta y exportación de reportes.

Conserve juntos los archivos `.java` y `.form`. Modifique la apariencia desde
**Design** y la lógica desde los métodos fuera de los bloques `GEN-BEGIN`/`GEN-END`,
porque NetBeans regenera esos bloques. Los botones tienen sus eventos registrados
en el diseñador; las consultas y validaciones permanecen fuera del código generado.

Ejecute el sistema desde `com.mensajeria.Main` para iniciar sesión. El menú abre
una ventana por módulo y reutiliza las ventanas abiertas. Cerrar un módulo deja
el menú disponible; cerrar sesión cierra también sus ventanas. El formulario de
nuevo envío actualiza la lista al cerrarse después de guardar.

### Comprobación de formularios sin Oracle

Después de `mvn package`, ejecute en Windows:

```text
java -cp "target/test-classes;target/SistemaMensajeria-1.0.jar" com.mensajeria.ui.FormSmokeCheck
```

Esta prueba comprueba la creación de los nueve formularios, botones, permisos y
cierre/reapertura de ventanas sin consultar Oracle. Genera imágenes de las pantallas
en `target/ui-preview/`. Los flujos de registro y consulta de datos requieren Oracle.
