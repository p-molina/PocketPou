package com.pocketpou.model.persistence;

import com.pocketpou.model.inventory.Inventory;
import com.pocketpou.model.item.Accessory;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.pet.PetSpecies;
import com.pocketpou.model.pet.state.DepressedState;
import com.pocketpou.model.pet.state.HappyState;
import com.pocketpou.model.pet.state.HungryState;
import com.pocketpou.model.pet.state.PetState;
import com.pocketpou.model.pet.state.SickState;
import com.pocketpou.model.pet.state.SleepingState;
import com.pocketpou.model.player.Player;
import com.pocketpou.model.shop.Shop;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

/* Guarda y restaura una partida en un archivo de texto. */
public final class SaveGame {

    private final Path file;

    public SaveGame(Path file) {
        this.file = file;
    }

    public void save(Player player) throws IOException {
        Properties data = new Properties();
        Pet pet = player.getPet();
        PetAttributes attributes = pet.getAttributes();
        data.setProperty("player.name", player.getName());
        data.setProperty("player.coins", String.valueOf(player.getCoins()));
        data.setProperty("pet.name", pet.getName());
        data.setProperty("pet.species", pet.getSpecies().name());
        data.setProperty("pet.state", pet.getStateName());
        if (pet.getState() instanceof SleepingState) {
            SleepingState sleeping = (SleepingState) pet.getState();
            data.setProperty("pet.previousState", sleeping.getPreviousState().getStateName());
        }
        data.setProperty("pet.hunger", String.valueOf(attributes.getHunger()));
        data.setProperty("pet.health", String.valueOf(attributes.getHealth()));
        data.setProperty("pet.energy", String.valueOf(attributes.getEnergy()));
        data.setProperty("pet.happiness", String.valueOf(attributes.getHappiness()));
        data.setProperty("pet.age", String.valueOf(attributes.getAge()));
        Inventory inventory = player.getInventory();
        List<Item> items = inventory.getItems();
        data.setProperty("inventory.count", String.valueOf(items.size()));
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            String key = "item." + index + ".";
            data.setProperty(key + "name", item.getName());
            if (item instanceof Accessory) {
                Accessory accessory = (Accessory) item;
                if (inventory.getEquipped(accessory.getSlot()) == item) {
                    data.setProperty(key + "equipped", "true");
                }
            }
        }

        try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            data.store(writer, "Partida de PocketPou");
        }
    }

    public Player load(Shop shop) throws IOException {
        Properties data = new Properties();
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            data.load(reader);
            PetAttributes attributes = new PetAttributes(Integer.parseInt(data.getProperty("pet.hunger")), Integer.parseInt(data.getProperty("pet.health")), Integer.parseInt(data.getProperty("pet.energy")), Integer.parseInt(data.getProperty("pet.happiness")), Integer.parseInt(data.getProperty("pet.age")));
            PetState state;
            String stateName = data.getProperty("pet.state", "");
            if (stateName.equals("Sleeping")) {
                state = new SleepingState(readAwakeState(data.getProperty("pet.previousState", "")));
            } else {
                state = readAwakeState(stateName);
            }
            Pet pet = new Pet(readText(data, "pet.name"),
                    PetSpecies.valueOf(data.getProperty("pet.species", "")), attributes, state);
            return new Player(readText(data, "player.name"), pet, loadInventory(data, shop),
                    Integer.parseInt(data.getProperty("player.coins")));
        } catch (IllegalArgumentException exception) {
            throw new IOException("El archivo de partida contiene datos inválidos.", exception);
        }
    }

    private Inventory loadInventory(Properties data, Shop shop) throws IOException {
        Inventory inventory = new Inventory();
        int count = Integer.parseInt(data.getProperty("inventory.count"));
        if (count < 0) {
            throw new IOException("La cantidad de productos del guardado no es válida.");
        }
        for (int index = 0; index < count; index++) {
            String key = "item." + index + ".";
            Item item = shop.findItem(data.getProperty(key + "name"));
            if (item == null) {
                throw new IOException("El guardado contiene un producto que no está en el catálogo.");
            }
            inventory.addItem(item);
            if (item instanceof Accessory && Boolean.parseBoolean(data.getProperty(key + "equipped"))) {
                inventory.equip((Accessory) item);
            }
        }
        return inventory;
    }

    private PetState readAwakeState(String name) {
        switch (name) {
            case "Happy": return new HappyState();
            case "Hungry": return new HungryState();
            case "Sick": return new SickState();
            case "Depressed": return new DepressedState();
            default: throw new IllegalArgumentException("El estado de la mascota es desconocido.");
        }
    }

    private String readText(Properties data, String key) {
        String value = data.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Falta un dato de la partida.");
        }
        return value;
    }
}
