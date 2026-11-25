package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está feliz.
 */
public final class HappyState implements PetState {

    public boolean feed(Pet pet, int hungerReduction) {
        pet.updateAttributes(pet.getAttributes().adjust(-hungerReduction, 0, 0, 5));
        pet.updateStateFromNeeds();
        return true;
    }

    public boolean play(Pet pet) {
        pet.updateAttributes(pet.getAttributes().adjust(15, 0, -20, 15));
        pet.updateStateFromNeeds();
        return true;
    }

    public void sleep(Pet pet) {
        pet.changeState(new SleepingState(this));
    }

    public boolean heal(Pet pet, int healthRecovery) {
        pet.updateAttributes(pet.getAttributes().adjust(0, healthRecovery, 0, 0));
        pet.updateStateFromNeeds();
        return true;
    }

    public String getStateName() {
        return "Happy";
    }
}
