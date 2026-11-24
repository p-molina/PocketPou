package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Item;

/*
 * PATTERN: Factory Method
 * Rol: Creador.
 * Función: Definir el método que delega en los creadores
 * concretos la construcción de cada producto.
 */
public abstract class ItemCreator {

    public abstract Item createItem(String name, int price, int effectValue);
}
