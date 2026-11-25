package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está deprimida.
 */
public final class DepressedState implements PetState {

    public boolean feed(Pet pet, int hungerReduction) {
        PetAttributes attributes = pet.getAttributes().adjust(-hungerReduction, 0, 0, 5);
        pet.updateAttributes(attributes);
        updateState(pet);
        return true;
    }

    public boolean play(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(10, 0, -10, 25);
        pet.updateAttributes(attributes);
        updateState(pet);
        return true;
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 20, 0);
        pet.changeState(new SleepingState(this));
        pet.updateAttributes(attributes);
    }

    public boolean heal(Pet pet, int healthRecovery) {
        PetAttributes attributes = pet.getAttributes().adjust(0, healthRecovery, 0, 0);
        pet.updateAttributes(attributes);
        updateState(pet);
        return true;
    }

    public String getStateName() {
        return "Depressed";
    }

    private void updateState(Pet pet) {
        if (!pet.updateStateFromNeeds() && pet.getAttributes().getHappiness() >= 60) {
            pet.changeState(new HappyState());
        }
    }
}
