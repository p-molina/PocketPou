package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Food;

/*
 * PATTERN: Factory Method
 * Rol: Creador concreto.
 * Función: Crear productos de tipo Food.
 */
public final class FoodCreator implements ItemCreator {

    public Food createItem(String name) {
        return new Food(name);
    }
}
