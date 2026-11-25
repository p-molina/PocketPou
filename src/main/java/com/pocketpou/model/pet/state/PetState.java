package com.pocketpou.model.pet.state;

import com.pocketpou.model.pet.Pet;

/*
 * PATTERN: State
 * Rol: Interfaz del estado.
 * Función: Definir las acciones que cambian su comportamiento
 * según el estado actual de la mascota.
 */
public interface PetState {

    boolean feed(Pet pet, int hungerReduction);

    boolean play(Pet pet);

    void sleep(Pet pet);

    boolean heal(Pet pet, int healthRecovery);

    String getStateName();
}
