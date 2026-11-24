package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Item;

/*
 * PATTERN: Factory Method
 * Rol: Creador.
 * Función: Definir el método polimórfico
 * de creación de productos.
 */
public abstract class ItemCreator {

    public abstract Item createItem(String name, int price, int effectValue);
}
