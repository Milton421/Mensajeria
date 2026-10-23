package com.mensajeria.model;

import java.time.LocalDate;

/** Vista completa de un envio (incluye datos del cliente, mensajero y paquete). */
public record Envio(
        int id,
        int idCliente,
        String clienteNombre,
        int idPaquete,
        String tipoPaquete,
        String descripcionPaquete,
        Integer idMensajero,
        String mensajeroNombre,
        LocalDate fechaEnvio,
        String direccionDestino,
        String estado,
        String observaciones,
        String nombreDestinatario,
        String telefonoDestinatario,
        LocalDate fechaEntrega,
        String recibidoPor) {

    public static final String REGISTRADO = "Registrado";
    public static final String ASIGNADO = "Asignado";
    public static final String EN_TRANSITO = "En tránsito";
    public static final String ENTREGADO = "Entregado";
    public static final String FALLIDA = "Entrega fallida";
    public static final String CANCELADO = "Cancelado";

    public static final String[] ESTADOS = {REGISTRADO, ASIGNADO, EN_TRANSITO, ENTREGADO, FALLIDA, CANCELADO};

    public boolean pendiente() {
        return REGISTRADO.equals(estado) || ASIGNADO.equals(estado) || EN_TRANSITO.equals(estado);
    }
}
