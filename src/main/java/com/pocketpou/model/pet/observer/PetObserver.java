package com.pocketpou.model.pet.observer;

import com.pocketpou.model.pet.Pet;

/*
 * PATTERN: Observer
 * Rol: Interfaz del observador.
 * Función: Definir el método que recibe la mascota observada
 * para consultar sus atributos vitales.
 */
public interface PetObserver {

    void update(Pet pet);
}
