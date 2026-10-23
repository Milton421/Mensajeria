package com.mensajeria.model;

public record Mensajero(int id, String nombre, String telefono, String direccion, String estado) {
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
