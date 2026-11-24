package com.pocketpou.model.pet;

import com.pocketpou.model.pet.observer.PetObserver;
import com.pocketpou.model.pet.state.PetState;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/*
 * PATTERN: State
 * Rol: Contexto.
 * Función: Delegar las acciones de la mascota en su estado actual
 * y permitir que dicho estado cambie durante el juego.
 *
 * PATTERN: Observer
 * Rol: Sujeto observable.
 * Función: Notificar a los observadores cuando cambian
 * los atributos vitales de la mascota.
 */
public final class Pet {

    private final String name;
    private final PetSpecies species;
    private final Set<PetObserver> observers;
    private PetAttributes attributes;
    private PetState state;

    public Pet(String name, PetSpecies species, PetAttributes attributes, PetState state) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.species = Objects.requireNonNull(species, "species must not be null");
        this.attributes = Objects.requireNonNull(attributes, "attributes must not be null");
        this.state = Objects.requireNonNull(state, "state must not be null");
        this.observers = new LinkedHashSet<>();
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
        this.attributes = Objects.requireNonNull(attributes, "attributes must not be null");
        notifyObservers();
    }

    public void changeState(PetState state) {
        this.state = Objects.requireNonNull(state, "state must not be null");
    }

    public void feed() {
        state.feed(this);
    }

    public void play() {
        state.play(this);
    }

    public void sleep() {
        state.sleep(this);
    }

    public void heal() {
        state.heal(this);
    }

    public String getStateName() {
        return state.getStateName();
    }

    public void addObserver(PetObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer must not be null"));
    }

    public void removeObserver(PetObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (PetObserver observer : Set.copyOf(observers)) {
            observer.onAttributesChanged(attributes);
        }
    }
}
