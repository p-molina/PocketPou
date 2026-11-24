package com.pocketpou.model.pet.observer;

import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: Observer
 * Rol: Interfaz del observador.
 * Función: Definir el método que recibirá los atributos
 * vitales de la mascota cuando sean notificados.
 */
@FunctionalInterface
public interface PetObserver {

    void update(PetAttributes attributes);
}
