package com.pocketpou.model.pet.state;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está enferma.
 */
public final class SickState implements PetState {

    public String getName() {
        return "Sick";
    }
}
