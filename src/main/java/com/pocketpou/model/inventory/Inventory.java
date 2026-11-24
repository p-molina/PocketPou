package com.pocketpou.model.inventory;

import com.pocketpou.model.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Responsabilidad: Mantener los objetos del jugador y ofrecer
 * operaciones controladas para gestionarlos.
 */
public final class Inventory {

    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(Objects.requireNonNull(item, "item must not be null"));
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }
}
