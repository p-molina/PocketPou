package com.pocketpou.view;

import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

/*
 * Responsabilidad: Gestionar la entrada y la salida de información
 * mediante la consola.
 */
public final class ConsoleView {

    private final Scanner input;
    private final PrintStream output;

    public ConsoleView(InputStream input, PrintStream output) {
        this.input = new Scanner(Objects.requireNonNull(input, "input must not be null"));
        this.output = Objects.requireNonNull(output, "output must not be null");
    }

    public void showMessage(String message) {
        output.println(Objects.requireNonNull(message, "message must not be null"));
    }

    public String readLine(String prompt) {
        output.print(Objects.requireNonNull(prompt, "prompt must not be null"));
        return input.nextLine();
    }

    public void showPet(Pet pet) {
        Objects.requireNonNull(pet, "pet must not be null");
        output.printf("%s (%s) - %s%n", pet.getName(), pet.getSpecies(), pet.getStateName());
        showAttributes(pet.getAttributes());
    }

    public void showAttributes(PetAttributes attributes) {
        Objects.requireNonNull(attributes, "attributes must not be null");
        output.printf(
                "Hunger: %d, Health: %d, Energy: %d, Happiness: %d, Age: %d%n",
                attributes.getHunger(),
                attributes.getHealth(),
                attributes.getEnergy(),
                attributes.getHappiness(),
                attributes.getAge()
        );
    }
}
