package com.pocketpou.model.pet.state;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está feliz.
 */
public final class HappyState implements PetState {

    public String getName() {
        return "Happy";
    }
}
