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

    public void feed(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(-15, 0, 0, 5);
        pet.updateAttributes(attributes);
        updateState(pet);
    }

    public void play(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(10, 0, -10, 25);
        pet.updateAttributes(attributes);
        updateState(pet);
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 20, 0);
        pet.updateAttributes(attributes);
        updateState(pet);
    }

    public void heal(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 15, 0, 0);
        pet.updateAttributes(attributes);
        updateState(pet);
    }

    public String getStateName() {
        return "Depressed";
    }

    private void updateState(Pet pet) {
        PetAttributes attributes = pet.getAttributes();

        if (attributes.getHealth() <= 30) {
            pet.changeState(new SickState());
        } else if (attributes.getHunger() >= 70) {
            pet.changeState(new HungryState());
        } else if (attributes.getEnergy() <= 20) {
            pet.changeState(new SleepingState());
        } else if (attributes.getHappiness() >= 60) {
            pet.changeState(new HappyState());
        }
    }
}
