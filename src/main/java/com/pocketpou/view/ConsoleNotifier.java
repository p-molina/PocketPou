package com.pocketpou.view;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.pet.observer.PetObserver;

import java.util.Objects;

/*
 * PATTERN: Observer
 * Rol: Observador concreto.
 * Función: Consultar los atributos notificados y mostrar
 * avisos cuando alguno alcanza un valor crítico.
 */
public final class ConsoleNotifier implements PetObserver {

    private final ConsoleView view;

    public ConsoleNotifier(ConsoleView view) {
        this.view = Objects.requireNonNull(view, "view must not be null");
    }

    public void update(Pet pet) {
        PetAttributes attributes = pet.getAttributes();

        if (attributes.getHunger() > 80) {
            view.showMessage("¡Tu mascota tiene mucha hambre!");
        }

        if (attributes.getHealth() < 20) {
            view.showMessage("¡Tu mascota necesita atención médica!");
        }

        if (attributes.getEnergy() < 20) {
            view.showMessage("¡Tu mascota necesita descansar!");
        }

        if (attributes.getHappiness() < 20) {
            view.showMessage("¡Tu mascota está triste!");
        }
    }
}
