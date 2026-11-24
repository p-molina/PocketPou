package com.pocketpou.model.item;

import java.util.Objects;

/*
 * PATTERN: Factory Method
 * Rol: Producto concreto.
 * Función: Representar un medicamento creado mediante
 * su creador correspondiente.
 */
public final class Medicine implements Item {

    private final String name;

    public Medicine(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public String getName() {
        return name;
    }
}
