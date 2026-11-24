package com.pocketpou.model.pet;

import com.pocketpou.model.pet.observer.PetObserver;
import com.pocketpou.model.pet.state.PetState;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/*
 * PATTERN: State
 * Rol: Contexto.
 * PATTERN: Observer
 * Rol: Sujeto observable.
 * Función: Mantener el estado actual de la mascota y notificar
 * a los observadores cuando cambian sus atributos vitales.
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

    public String getStateName() {
        return state.getName();
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
