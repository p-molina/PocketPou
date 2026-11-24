package com.pocketpou.view;

import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.pet.observer.PetObserver;

import java.util.Objects;

/*
 * PATTERN: Observer
 * Rol: Observador concreto.
 * Función: Preparar la futura presentación en consola
 * de los cambios notificados por la mascota.
 */
public final class ConsoleNotifier implements PetObserver {

    private final ConsoleView view;

    public ConsoleNotifier(ConsoleView view) {
        this.view = Objects.requireNonNull(view, "view must not be null");
    }

    public void update(PetAttributes attributes) {
    }
}
