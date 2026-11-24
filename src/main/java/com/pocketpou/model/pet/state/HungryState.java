package com.pocketpou.model.pet.state;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando tiene hambre.
 */
public final class HungryState implements PetState {

    public String getName() {
        return "Hungry";
    }
}
