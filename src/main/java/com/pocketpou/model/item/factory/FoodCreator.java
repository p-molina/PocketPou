package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Food;

/*
 * PATTERN: Factory Method
 * Rol: Creador concreto.
 * Función: Crear objetos Food y encapsular su construcción.
 */
public final class FoodCreator extends ItemCreator {

    public Food createItem(String name, int price, int hungerReduction) {
        return new Food(name, price, hungerReduction);
    }
}
