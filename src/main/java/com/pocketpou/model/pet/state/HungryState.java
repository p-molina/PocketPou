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

    public void feed(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(-30, 0, 0, 5);
        pet.updateAttributes(attributes);

        if (attributes.getHealth() <= 30) {
            pet.changeState(new SickState());
        } else if (attributes.getHunger() <= 40) {
            pet.changeState(new HappyState());
        }
    }

    public void play(Pet pet) {
        /* La mascota necesita comer antes de jugar. */
    }

    public void sleep(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 0, 15, 0);
        pet.updateAttributes(attributes);
    }

    public void heal(Pet pet) {
        PetAttributes attributes = pet.getAttributes().adjust(0, 20, 0, 0);
        pet.updateAttributes(attributes);
    }

    public String getStateName() {
        return "Hungry";
    }
}
