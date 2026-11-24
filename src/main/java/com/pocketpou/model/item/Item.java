package com.pocketpou.model.item;

/*
 * PATTERN: Factory Method
 * Rol: Interfaz del producto.
 * Función: Definir el nombre y el precio comunes
 * a todos los productos.
 */
public interface Item {

    String getName();

    int getPrice();
}
