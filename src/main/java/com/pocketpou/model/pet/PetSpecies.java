package com.pocketpou.model.pet;

/*
 * Responsabilidad: Enumerar las especies de mascota disponibles.
 */
public enum PetSpecies {
    CLASSIC_POU("Classic Pou"),
    BLOBBY("Blobby"),
    NEO_ALIEN("NeoAlien");

    private final String displayName;

    PetSpecies(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
