package com.pocketpou.model.player;

import com.pocketpou.model.inventory.Inventory;
import com.pocketpou.model.pet.Pet;

/*
 * Responsabilidad: Mantener la mascota, el inventario y el saldo del jugador.
 */
public final class Player {

    private final String name;
    private final Pet pet;
    private final Inventory inventory;
    private int coins;

    public Player(String name, Pet pet, Inventory inventory, int coins) {
        this.name = name;
        this.pet = pet;
        this.inventory = inventory;
        this.coins = Math.max(0, coins);
    }

    public String getName() {
        return name;
    }

    public Pet getPet() {
        return pet;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        if (amount > 0) {
            coins += Math.min(amount, Integer.MAX_VALUE - coins);
        }
    }

    public boolean hasEnoughCoins(int amount) {
        return amount >= 0 && coins >= amount;
    }

    public boolean spendCoins(int amount) {
        if (!hasEnoughCoins(amount)) {
            return false;
        }
        coins -= amount;
        return true;
    }
}
