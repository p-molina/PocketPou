package com.pocketpou.model.item;

/*
 * PATTERN: Factory Method
 * Rol: Producto concreto.
 * Función: Representar un alimento con su nombre,
 * precio y reducción del hambre.
 */
public final class Food implements Item {

    private final String name;
    private final int price;
    private final int hungerReduction;

    public Food(String name, int price, int hungerReduction) {
        this.name = name;
        this.price = price;
        this.hungerReduction = hungerReduction;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getHungerReduction() {
        return hungerReduction;
    }
}
