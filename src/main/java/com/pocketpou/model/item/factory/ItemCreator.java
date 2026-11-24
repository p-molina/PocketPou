package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Item;

/*
 * PATTERN: Factory Method
 * Rol: Creador.
 * Función: Definir el método polimórfico utilizado
 * para crear objetos.
 */
public interface ItemCreator {

    Item createItem(String name);
}
