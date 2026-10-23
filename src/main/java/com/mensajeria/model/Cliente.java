package com.mensajeria.model;

public record Cliente(int id, String nombre, String telefono, String direccion, String correo) {
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
