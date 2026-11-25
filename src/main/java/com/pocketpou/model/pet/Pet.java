package com.pocketpou.model.pet;

import com.pocketpou.model.pet.observer.PetObserver;
import com.pocketpou.model.pet.state.DepressedState;
import com.pocketpou.model.pet.state.HungryState;
import com.pocketpou.model.pet.state.PetState;
import com.pocketpou.model.pet.state.SickState;
import com.pocketpou.model.pet.state.SleepingState;

import java.util.ArrayList;
import java.util.List;

/*
 * PATTERN: State
 * Rol: Contexto.
 * Función: Delegar las acciones de la mascota en su estado actual
 * y gestionar sus cambios de estado durante el juego.
 *
 * PATTERN: Observer
 * Rol: Sujeto observable.
 * Función: Registrar observadores y notificarles automáticamente
 * los cambios en los atributos vitales de la mascota.
 */
public final class Pet {

    private final String name;
    private final PetSpecies species;
    private final List<PetObserver> observers = new ArrayList<>();
    private PetAttributes attributes;
    private PetState state;

    public Pet(String name, PetSpecies species, PetAttributes attributes, PetState state) {
        this.name = name;
        this.species = species;
        this.attributes = attributes;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public PetSpecies getSpecies() {
        return species;
    }

    public PetAttributes getAttributes() {
        return attributes;
    }

    public PetState getState() {
        return state;
    }

    public void updateAttributes(PetAttributes attributes) {
        this.attributes = attributes;
        notifyObservers();
    }

    public void changeState(PetState state) {
        this.state = state;
    }

    public boolean feed(int hungerReduction) {
        return state.feed(this, hungerReduction);
    }

    public boolean play() {
        return state.play(this);
    }

    public void sleep() {
        state.sleep(this);
    }

    public boolean heal(int healthRecovery) {
        return state.heal(this, healthRecovery);
    }

    public void advanceTurn() {
        int age = attributes.getAge() + 1;

        if (state instanceof SleepingState) {
            updateAttributes(new PetAttributes(attributes.getHunger(), attributes.getHealth(), attributes.getEnergy(), attributes.getHappiness(), age));
            return;
        }

        int hunger = attributes.getHunger() + 4;
        int health = attributes.getHealth();
        if (hunger >= 85) {
            health -= 5;
        }
        updateAttributes(new PetAttributes(hunger, health, attributes.getEnergy() - 3, attributes.getHappiness() - 2, age));

        if (!(state instanceof SickState)) {
            updateStateFromNeeds();
        }
    }

    public boolean updateStateFromNeeds() {
        if (attributes.getHealth() <= 30) {
            changeState(new SickState());
        } else if (attributes.getHunger() >= 70) {
            changeState(new HungryState());
        } else if (state instanceof HungryState) {
            return false;
        } else if (attributes.getEnergy() <= 20) {
            changeState(new SleepingState(state));
        } else if (attributes.getHappiness() <= 30) {
            changeState(new DepressedState());
        } else {
            return false;
        }
        return true;
    }

    public String getStateName() {
        return state.getStateName();
    }

    public void addObserver(PetObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PetObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (int index = 0; index < observers.size(); index++) {
            PetObserver observer = observers.get(index);
            observer.update(this);
        }
    }
}
