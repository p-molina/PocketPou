package com.pocketpou.model.item;

import java.util.Objects;

/*
 * PATTERN: Factory Method
 * Rol: Producto concreto.
 * Función: Representar un alimento creado mediante
 * su creador correspondiente.
 */
public final class Food implements Item {

    private final String name;

    public Food(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public String getName() {
        return name;
    }
}
