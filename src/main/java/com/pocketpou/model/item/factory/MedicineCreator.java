package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Medicine;

/*
 * PATTERN: Factory Method
 * Rol: Creador concreto.
 * Función: Crear objetos Medicine y encapsular su construcción.
 */
public final class MedicineCreator extends ItemCreator {

    public Medicine createItem(String name, int price, int healthRecovery) {
        return new Medicine(name, price, healthRecovery);
    }
}
