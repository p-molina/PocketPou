package com.pocketpou.model.pet.observer;

import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: Observer
 * Rol: Interfaz del observador.
 * Función: Definir la notificación que se recibe cuando cambian
 * los atributos vitales de la mascota.
 */
@FunctionalInterface
public interface PetObserver {

    void onAttributesChanged(PetAttributes attributes);
}
