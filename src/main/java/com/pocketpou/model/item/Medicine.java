package com.pocketpou.model.item;

import java.util.Objects;

/*
 * PATTERN: Factory Method
 * Rol: Producto concreto.
 * Función: Representar un medicamento con su nombre,
 * precio y recuperación de salud.
 */
public final class Medicine implements Item {

    private final String name;
    private final int price;
    private final int healthRecovery;

    public Medicine(String name, int price, int healthRecovery) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.price = price;
        this.healthRecovery = healthRecovery;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getHealthRecovery() {
        return healthRecovery;
    }
}
