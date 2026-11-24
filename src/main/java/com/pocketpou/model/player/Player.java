package com.pocketpou.model.player;

import com.pocketpou.model.inventory.Inventory;
import com.pocketpou.model.pet.Pet;

import java.util.Objects;

/*
 * Responsabilidad: Agrupar la mascota y el inventario
 * pertenecientes al jugador.
 */
public final class Player {

    private final Pet pet;
    private final Inventory inventory;

    public Player(Pet pet, Inventory inventory) {
        this.pet = Objects.requireNonNull(pet, "pet must not be null");
        this.inventory = Objects.requireNonNull(inventory, "inventory must not be null");
    }

    public Pet getPet() {
        return pet;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
