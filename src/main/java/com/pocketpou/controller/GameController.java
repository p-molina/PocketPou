package com.pocketpou.controller;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.view.ConsoleView;

import java.util.Objects;

/*
 * Responsabilidad: Coordinar el flujo inicial de la aplicación
 * y la presentación de la mascota.
 */
public final class GameController {

    private final ConsoleView view;

    public GameController(ConsoleView view) {
        this.view = Objects.requireNonNull(view, "view must not be null");
    }

    public void start() {
        view.showMessage("Welcome to PocketPou!");
    }

    public void showPet(Pet pet) {
        view.showPet(Objects.requireNonNull(pet, "pet must not be null"));
    }
}
