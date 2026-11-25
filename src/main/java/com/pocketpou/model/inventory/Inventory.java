package com.pocketpou.model.inventory;

import com.pocketpou.model.item.Accessory;
import com.pocketpou.model.item.Food;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.item.Medicine;
import com.pocketpou.model.pet.Pet;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/*
 * Responsabilidad: Mantener los objetos comprados, usar consumibles
 * y equipar los accesorios que posee el jugador.
 */
public final class Inventory {

    private final List<Item> items = new ArrayList<>();
    private final EnumMap<Accessory.Slot, Accessory> equipped = new EnumMap<>(Accessory.Slot.class);

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(Item item) {
        if (!items.remove(item)) {
            return false;
        }
        if (item instanceof Accessory) {
            Accessory accessory = (Accessory) item;
            if (equipped.get(accessory.getSlot()) == accessory && !items.contains(accessory)) {
                unequip(accessory.getSlot());
            }
        }
        return true;
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public boolean useItem(Item item, Pet pet) {
        if (!items.contains(item)) {
            return false;
        }

        boolean used = false;
        if (item instanceof Food && pet.getAttributes().getHunger() > 0) {
            Food food = (Food) item;
            used = pet.feed(food.getHungerReduction());
        } else if (item instanceof Medicine && pet.getAttributes().getHealth() < 100) {
            Medicine medicine = (Medicine) item;
            used = pet.heal(medicine.getHealthRecovery());
        }
        if (used) {
            removeItem(item);
        }
        return used;
    }

    public boolean equip(Accessory accessory) {
        if (!items.contains(accessory)) {
            return false;
        }
        equipped.put(accessory.getSlot(), accessory);
        return true;
    }

    public void unequip(Accessory.Slot slot) {
        equipped.remove(slot);
    }

    public Accessory getEquipped(Accessory.Slot slot) {
        return equipped.get(slot);
    }
}
