package com.mensajeria.model;

public record Rol(int id, String nombre, String descripcion) {
    @Override
    public String toString() {
        return nombre;
    }
}
