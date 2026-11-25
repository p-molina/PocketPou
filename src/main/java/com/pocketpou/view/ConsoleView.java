package com.pocketpou.view;

import com.pocketpou.model.inventory.Inventory;
import com.pocketpou.model.item.Accessory;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.player.Player;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/*
 * Responsabilidad: Gestionar la entrada y la salida de información
 * mediante la consola.
 */
public final class ConsoleView {

    private final Scanner input;
    private final PrintStream output;

    public ConsoleView(InputStream input, PrintStream output) {
        this.input = new Scanner(input, StandardCharsets.UTF_8);
        this.output = output;
    }

    public void showMessage(String message) {
        output.println(message);
    }

    public String readLine(String prompt) {
        output.print(prompt);
        output.flush();
        if (!input.hasNextLine()) {
            return null;
        }
        return input.nextLine();
    }

    public int readChoice(String prompt, int minimum, int maximum) {
        while (true) {
            String line = readLine(prompt);
            if (line == null) {
                return -1;
            }

            try {
                int choice = Integer.parseInt(line.trim());
                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
            } catch (NumberFormatException exception) {
                showMessage("Introduce un número entero.");
                continue;
            }
            showMessage("Introduce un número entre " + minimum + " y " + maximum + ".");
        }
    }

    public void showMenu(Player player) {
        Pet pet = player.getPet();
        PetAttributes attributes = pet.getAttributes();
        output.printf("%n%s · %d monedas%n", player.getName(), player.getCoins());
        output.printf("Mascota: %s (%s)%n", pet.getName(), pet.getSpecies().getDisplayName());
        showMessage("Estado: " + getStateLabel(pet.getStateName()));
        output.printf("Hambre: %d/100 · Salud: %d/100 · Energía: %d/100%n",
                attributes.getHunger(), attributes.getHealth(), attributes.getEnergy());
        output.printf("Felicidad: %d/100 · Edad: %d turnos%n", attributes.getHappiness(), attributes.getAge());
        showMessage("""

                1. Alimentar mascota
                2. Curar mascota
                3. Dormir / Despertar
                4. Minijuegos
                5. Inventario
                6. Tienda
                7. Personalizar mascota
                8. Decorar habitación
                9. Guardar partida
                10. Jugar con la mascota
                0. Salir""");
    }

    public void showItems(List<Item> items, boolean showPrices) {
        if (items.isEmpty()) {
            showMessage("No hay productos.");
        }
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            String line = (index + 1) + ". " + item.getName();
            if (item instanceof Accessory) {
                Accessory accessory = (Accessory) item;
                line += " [" + accessory.getSlot().getDisplayName() + "]";
            }
            if (showPrices) {
                line += " - " + item.getPrice() + " monedas";
            }
            showMessage(line);
        }
    }

    public void showAppearance(Inventory inventory, boolean room) {
        showMessage(room ? "\nHabitación" : "\nAccesorios de la mascota");
        for (Accessory.Slot slot : Accessory.Slot.values()) {
            if (slot.isRoom() == room) {
                Accessory equipped = inventory.getEquipped(slot);
                String name = equipped == null ? "Por defecto" : equipped.getName();
                showMessage(slot.getDisplayName() + ": " + name);
            }
        }
    }

    private String getStateLabel(String state) {
        switch (state) {
            case "Happy": return "Feliz";
            case "Hungry": return "Hambrienta";
            case "Sick": return "Enferma";
            case "Sleeping": return "Dormida";
            case "Depressed": return "Triste";
            default: return state;
        }
    }
}
