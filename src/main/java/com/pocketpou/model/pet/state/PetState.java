package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;

/*
 * PATTERN: State
 * Rol: Interfaz del estado.
 * Función: Definir las acciones que cambian su comportamiento
 * según el estado actual de la mascota.
 */
public interface PetState {

    void feed(Pet pet);

    void play(Pet pet);

    void sleep(Pet pet);

    void heal(Pet pet);

    String getStateName();
}
