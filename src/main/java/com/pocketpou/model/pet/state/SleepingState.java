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

    public void feed(Pet pet) {
        /* La mascota no puede comer mientras duerme. */
    }

    public void play(Pet pet) {
        /* La mascota no puede jugar mientras duerme. */
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 30, 0);
        pet.updateAttributes(attributes);

        if (attributes.getEnergy() >= 70) {
            if (attributes.getHealth() <= 30) {
                pet.changeState(new SickState());
            } else if (attributes.getHunger() >= 70) {
                pet.changeState(new HungryState());
            } else if (attributes.getHappiness() <= 30) {
                pet.changeState(new DepressedState());
            } else {
                pet.changeState(new HappyState());
            }
        }
    }

    public void heal(Pet pet) {
        /* La mascota debe despertar antes de curarse. */
    }

    public String getStateName() {
        return "Sleeping";
    }
}
