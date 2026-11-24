package com.pocketpou.model.pet.state;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está durmiendo.
 */
public final class SleepingState implements PetState {

    public String getName() {
        return "Sleeping";
    }
}
