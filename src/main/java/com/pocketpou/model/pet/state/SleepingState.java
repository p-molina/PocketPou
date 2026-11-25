package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando está durmiendo.
 */
public final class SleepingState implements PetState {

    private final PetState previousState;

    public SleepingState(PetState previousState) {
        this.previousState = previousState;
    }

    public PetState getPreviousState() {
        return previousState;
    }

    public boolean feed(Pet pet, int hungerReduction) {
        return false;
    }

    public boolean play(Pet pet) {
        return false;
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 30, 0);
        pet.updateAttributes(attributes);

        if (attributes.getEnergy() < 70) {
            return;
        }
        if (attributes.getHealth() <= 30 || (previousState instanceof SickState && attributes.getHealth() < 60)) {
            pet.changeState(new SickState());
        } else if (attributes.getHunger() >= 70 || (previousState instanceof HungryState && attributes.getHunger() > 40)) {
            pet.changeState(new HungryState());
        } else if (attributes.getHappiness() <= 30 || (previousState instanceof DepressedState && attributes.getHappiness() < 60)) {
            pet.changeState(new DepressedState());
        } else {
            pet.changeState(new HappyState());
        }
    }

    public boolean heal(Pet pet, int healthRecovery) {
        return false;
    }

    public String getStateName() {
        return "Sleeping";
    }
}
