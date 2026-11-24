package com.pocketpou.model.pet;

/*
 * Responsabilidad: Almacenar de forma inmutable los atributos
 * vitales de la mascota.
 */
public final class PetAttributes {

    private final int hunger;
    private final int health;
    private final int energy;
    private final int happiness;
    private final int age;

    public PetAttributes(int hunger, int health, int energy, int happiness, int age) {
        this.hunger = hunger;
        this.health = health;
        this.energy = energy;
        this.happiness = happiness;
        this.age = age;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getAge() {
        return age;
    }
}
