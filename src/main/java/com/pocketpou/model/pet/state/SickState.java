package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está enferma.
 */
public final class SickState implements PetState {

    public void feed(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(-15, 0, 0, 0);
        pet.updateAttributes(attributes);
    }

    public void play(Pet pet) {
        /* La mascota debe curarse antes de jugar. */
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 20, 0);
        pet.updateAttributes(attributes);
    }

    public void heal(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 30, 0, 0);
        pet.updateAttributes(attributes);

        if (attributes.getHealth() >= 60) {
            if (attributes.getHunger() >= 70) {
                pet.changeState(new HungryState());
            } else if (attributes.getEnergy() <= 20) {
                pet.changeState(new SleepingState());
            } else if (attributes.getHappiness() <= 30) {
                pet.changeState(new DepressedState());
            } else {
                pet.changeState(new HappyState());
            }
        }
    }

    public String getStateName() {
        return "Sick";
    }
}
