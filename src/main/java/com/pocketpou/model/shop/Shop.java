package com.pocketpou.model.shop;

import com.pocketpou.model.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Responsabilidad: Mantener el catálogo de objetos disponibles
 * en la tienda.
 */
public final class Shop {

    private final List<Item> catalog;

    public Shop() {
        this.catalog = new ArrayList<>();
    }

    public void addToCatalog(Item item) {
        catalog.add(Objects.requireNonNull(item, "item must not be null"));
    }

    public boolean removeFromCatalog(Item item) {
        return catalog.remove(item);
    }

    public List<Item> getCatalog() {
        return List.copyOf(catalog);
    }
}
