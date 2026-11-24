package com.pocketpou.model.pet.state;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está deprimida.
 */
public final class DepressedState implements PetState {

    public String getName() {
        return "Depressed";
    }
}
