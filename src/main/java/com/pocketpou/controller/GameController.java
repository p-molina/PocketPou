package com.pocketpou.controller;

import com.pocketpou.model.inventory.Inventory;
import com.pocketpou.model.item.Accessory;
import com.pocketpou.model.item.Food;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.item.Medicine;
import com.pocketpou.model.minigame.GuessTheNumber;
import com.pocketpou.model.minigame.ReactionTest;
import com.pocketpou.model.persistence.SaveGame;
import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.pet.PetSpecies;
import com.pocketpou.model.pet.state.HappyState;
import com.pocketpou.model.player.Player;
import com.pocketpou.model.shop.Shop;
import com.pocketpou.view.ConsoleNotifier;
import com.pocketpou.view.ConsoleView;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Responsabilidad: Coordinar la partida y conectar las acciones
 * de consola con el modelo.
 */
public final class GameController {

    private final ConsoleView view;
    private final Shop shop;
    private final SaveGame saveGame;
    private final Random random;
    private Player player;

    public GameController(ConsoleView view, Shop shop, SaveGame saveGame, Random random) {
        this.view = view;
        this.shop = shop;
        this.saveGame = saveGame;
        this.random = random;
    }

    public void start() {
        player = selectPlayer();
        if (player == null) {
            return;
        }

        player.getPet().addObserver(new ConsoleNotifier(view));
        view.showMessage("¡Bienvenido a PocketPou, " + player.getName() + "!");
        player.getPet().notifyObservers();

        while (true) {
            view.showMenu(player);
            int choice = view.readChoice("\nSelecciona una opción: ", 0, 10);
            switch (choice) {
                case -1:
                case 0:
                    view.showMessage("¡Hasta pronto!");
                    return;
                case 1:
                    chooseConsumable(true);
                    break;
                case 2:
                    chooseConsumable(false);
                    break;
                case 3:
                    player.getPet().sleep();
                    break;
                case 4:
                    chooseMiniGame();
                    break;
                case 5:
                    view.showMessage("\nInventario");
                    view.showItems(player.getInventory().getItems(), false);
                    break;
                case 6:
                    openShop();
                    break;
                case 7:
                    customize(false);
                    break;
                case 8:
                    customize(true);
                    break;
                case 9:
                    savePlayer();
                    break;
                case 10:
                    playWithPet();
                    break;
            }
        }
    }

    private Player selectPlayer() {
        view.showMessage("=== POCKETPOU ===");
        while (true) {
            view.showMessage("1. Nueva partida\n2. Cargar partida\n0. Salir");
            int choice = view.readChoice("Selecciona una opción: ", 0, 2);
            if (choice == 1) {
                return createPlayer();
            }
            if (choice <= 0) {
                return null;
            }
            try {
                Player loadedPlayer = saveGame.load(shop);
                view.showMessage("Partida cargada.");
                return loadedPlayer;
            } catch (NoSuchFileException exception) {
                view.showMessage("Todavía no hay una partida guardada.");
            } catch (IOException exception) {
                view.showMessage("No se pudo cargar la partida.");
            }
        }
    }

    private Player createPlayer() {
        String playerName = readName("Nombre del jugador: ");
        if (playerName == null) {
            return null;
        }
        String petName = readName("Nombre de la mascota: ");
        if (petName == null) {
            return null;
        }

        PetSpecies[] species = PetSpecies.values();
        for (int index = 0; index < species.length; index++) {
            view.showMessage((index + 1) + ". " + species[index].getDisplayName());
        }
        int choice = view.readChoice("Selecciona la especie: ", 1, species.length);
        if (choice == -1) {
            return null;
        }
        Pet pet = new Pet(petName, species[choice - 1], new PetAttributes(20, 100, 80, 90, 0),
                new HappyState());
        return new Player(playerName, pet, new Inventory(), 100);
    }

    private String readName(String prompt) {
        while (true) {
            String name = view.readLine(prompt);
            if (name == null) {
                return null;
            }
            if (!name.isBlank()) {
                return name.strip();
            }
            view.showMessage("El nombre no puede estar vacío.");
        }
    }

    private void chooseConsumable(boolean food) {
        List<Item> options = new ArrayList<>();
        Inventory inventory = player.getInventory();
        for (Item item : inventory.getItems()) {
            if ((food && item instanceof Food) || (!food && item instanceof Medicine)) {
                options.add(item);
            }
        }
        if (options.isEmpty()) {
            view.showMessage(food ? "No tienes alimentos." : "No tienes medicamentos.");
            return;
        }
        int index = selectItem(options, false);
        if (index < 0) {
            return;
        }
        Item item = options.get(index);
        if (inventory.useItem(item, player.getPet())) {
            view.showMessage("Has utilizado " + item.getName() + ".");
        } else {
            view.showMessage("La mascota no puede usar ese producto en este momento.");
        }
    }

    private void playWithPet() {
        Pet pet = player.getPet();
        if (pet.play()) {
            view.showMessage("Has jugado con " + pet.getName() + ".");
            pet.advanceTurn();
        } else {
            view.showMessage("La mascota no puede jugar en su estado actual.");
        }
    }

    private void openShop() {
        List<Item> catalog = shop.getCatalog();
        while (true) {
            view.showMessage("\nTienda · Monedas: " + player.getCoins());
            int index = selectItem(catalog, true);
            if (index < 0) {
                return;
            }
            if (shop.buyItem(player, index)) {
                view.showMessage("Has comprado " + catalog.get(index).getName() + ".");
            } else {
                view.showMessage("No tienes suficientes monedas.");
            }
        }
    }

    private void customize(boolean room) {
        Inventory inventory = player.getInventory();
        List<Item> options = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            if (item instanceof Accessory) {
                Accessory accessory = (Accessory) item;
                if (accessory.getSlot().isRoom() == room) {
                    options.add(accessory);
                }
            }
        }
        view.showAppearance(inventory, room);
        if (options.isEmpty()) {
            view.showMessage("Compra artículos en la tienda para personalizar esta sección.");
            return;
        }
        view.showMessage("Selecciona un artículo para equiparlo o quitarlo.");
        int index = selectItem(options, false);
        if (index < 0) {
            return;
        }
        Accessory chosen = (Accessory) options.get(index);
        if (inventory.getEquipped(chosen.getSlot()) == chosen) {
            inventory.unequip(chosen.getSlot());
            view.showMessage("Has quitado " + chosen.getName() + ".");
        } else {
            inventory.equip(chosen);
            view.showMessage("Has equipado " + chosen.getName() + ".");
        }
    }

    private int selectItem(List<Item> items, boolean showPrices) {
        view.showItems(items, showPrices);
        view.showMessage("0. Volver");
        return view.readChoice("Selecciona un artículo: ", 0, items.size()) - 1;
    }

    private void chooseMiniGame() {
        view.showMessage("\n1. Adivina el número\n2. Prueba de reacción\n0. Volver");
        int choice = view.readChoice("Selecciona un minijuego: ", 0, 2);
        if (choice == 1) {
            playGuessTheNumber();
        } else if (choice == 2) {
            playReactionTest();
        }
    }

    private void playGuessTheNumber() {
        GuessTheNumber game = new GuessTheNumber(random);
        view.showMessage("\n" + game.getName() + " · Del 1 al " + GuessTheNumber.MAX_NUMBER + " en " + GuessTheNumber.MAX_ATTEMPTS + " intentos. Escribe 0 para volver.");
        long startTime = System.nanoTime();
        while (!game.isFinished()) {
            int number = view.readChoice("Tu intento: ", 0, GuessTheNumber.MAX_NUMBER);
            if (number <= 0) {
                return;
            }
            int result = game.guess(number);
            if (result == 0) {
                view.showMessage("¡Has acertado!");
            } else if (result > 0) {
                view.showMessage("El número secreto es mayor.");
            } else {
                view.showMessage("El número secreto es menor.");
            }
        }
        view.showMessage("El número secreto era " + game.getAnswer() + ".");
        finishMiniGame(game.getReward((System.nanoTime() - startTime) / 1_000_000_000.0));
    }

    private void playReactionTest() {
        ReactionTest game = new ReactionTest(random);
        view.showMessage("\n" + game.getName());
        view.showMessage("Espera la señal «¡YA!» y luego pulsa Enter.");

        // La señal se muestra mientras la consola espera la respuesta del jugador.
        Thread signal = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(game.getDelayMillis());
                    if (!Thread.currentThread().isInterrupted()) {
                        game.markSignal(System.nanoTime());
                        view.showMessage("¡YA!");
                    }
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        signal.start();

        String answer = view.readLine("");
        long answerTime = System.nanoTime();
        signal.interrupt();
        try {
            signal.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            return;
        }
        if (answer == null) {
            return;
        }

        long reactionNanos = game.getReactionNanos(answerTime);
        if (reactionNanos < 0) {
            view.showMessage("Pulsaste antes de la señal.");
            finishMiniGame(game.calculateReward(0, 1, 0));
        } else {
            view.showMessage("Tiempo de reacción: " + reactionNanos / 1_000_000 + " ms.");
            finishMiniGame(game.calculateReward(1, 1, reactionNanos / 1_000_000_000.0));
        }
    }

    private void finishMiniGame(int reward) {
        int previousCoins = player.getCoins();
        player.addCoins(reward);
        view.showMessage("Has ganado " + (player.getCoins() - previousCoins) + " monedas.");
        player.getPet().advanceTurn();
    }

    private void savePlayer() {
        try {
            saveGame.save(player);
            view.showMessage("Partida guardada.");
        } catch (IOException exception) {
            view.showMessage("No se pudo guardar la partida.");
        }
    }
}
