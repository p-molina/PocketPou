package com.pocketpou.model.item.factory;

import com.pocketpou.model.item.Medicine;

/*
 * PATTERN: Factory Method
 * Rol: Creador concreto.
 * Función: Crear productos de tipo Medicine.
 */
public final class MedicineCreator implements ItemCreator {

    public Medicine createItem(String name) {
        return new Medicine(name);
    }
}
