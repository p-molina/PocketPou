package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;

/*
 * PATTERN: State
 * Rol: Estado concreto.
 * Función: Gestionar el comportamiento de la mascota
 * cuando tiene hambre.
 */
public final class HungryState implements PetState {

    public boolean feed(Pet pet, int hungerReduction) {
        PetAttributes attributes = pet.getAttributes().adjust(-hungerReduction, 0, 0, 5);
        pet.updateAttributes(attributes);

        if (attributes.getHealth() <= 30) {
            pet.changeState(new SickState());
        } else if (attributes.getHunger() <= 40) {
            pet.changeState(new HappyState());
        }
        return true;
    }

    public boolean play(Pet pet) {
        return false;
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 15, 0);
        pet.changeState(new SleepingState(this));
        pet.updateAttributes(attributes);
    }

    public boolean heal(Pet pet, int healthRecovery) {
        PetAttributes attributes = pet.getAttributes().adjust(0, healthRecovery, 0, 0);
        pet.updateAttributes(attributes);
        return true;
    }

    public String getStateName() {
        return "Hungry";
    }
}
