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
        this.hunger = limitToRange(hunger);
        this.health = limitToRange(health);
        this.energy = limitToRange(energy);
        this.happiness = limitToRange(happiness);
        this.age = age;
    }

    public PetAttributes adjust(
            int hungerChange,
            int healthChange,
            int energyChange,
            int happinessChange
    ) {
        return new PetAttributes(
                hunger + hungerChange,
                health + healthChange,
                energy + energyChange,
                happiness + happinessChange,
                age
        );
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

    private int limitToRange(int value) {
        if (value < 0) {
            return 0;
        } else if (value > 100) {
            return 100;
        }

        return value;
    }
}
