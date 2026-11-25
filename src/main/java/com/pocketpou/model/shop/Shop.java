package com.pocketpou.model.shop;

import com.pocketpou.model.item.Accessory;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.item.factory.FoodCreator;
import com.pocketpou.model.item.factory.ItemCreator;
import com.pocketpou.model.item.factory.MedicineCreator;
import com.pocketpou.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/*
 * Responsabilidad: Mantener el catálogo y gestionar las compras.
 */
public final class Shop {

    private final List<Item> catalog = new ArrayList<>();

    public Shop() {
        ItemCreator foodCreator = new FoodCreator();
        ItemCreator medicineCreator = new MedicineCreator();

        catalog.add(foodCreator.createItem("Manzana", 10, 20));
        catalog.add(foodCreator.createItem("Sushi", 30, 50));
        catalog.add(foodCreator.createItem("Galleta", 5, 10));
        catalog.add(medicineCreator.createItem("Medicamento básico", 20, 30));
        catalog.add(medicineCreator.createItem("Medicamento avanzado", 50, 80));
        catalog.add(new Accessory("Gorra azul", 15, Accessory.Slot.HAT));
        catalog.add(new Accessory("Sombrero de fiesta", 20, Accessory.Slot.HAT));
        catalog.add(new Accessory("Gafas de sol", 15, Accessory.Slot.GLASSES));
        catalog.add(new Accessory("Bufanda roja", 20, Accessory.Slot.CLOTHES));
        catalog.add(new Accessory("Verde menta", 15, Accessory.Slot.COLOR));
        catalog.add(new Accessory("Paredes de estrellas", 30, Accessory.Slot.WALL));
        catalog.add(new Accessory("Suelo de madera", 25, Accessory.Slot.FLOOR));
        catalog.add(new Accessory("Sillón naranja", 35, Accessory.Slot.FURNITURE));
    }

    public List<Item> getCatalog() {
        return List.copyOf(catalog);
    }

    public Item findItem(String name) {
        for (Item item : catalog) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean buyItem(Player player, int index) {
        Item item = catalog.get(index);
        if (!player.spendCoins(item.getPrice())) {
            return false;
        }
        player.getInventory().addItem(item);
        return true;
    }
}
