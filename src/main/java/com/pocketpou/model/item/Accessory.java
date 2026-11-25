package com.pocketpou.model.item;

/* Representa un artículo que se equipa en la mascota o en su habitación. */
public final class Accessory implements Item {

    public enum Slot {
        HAT("Sombrero", false),
        GLASSES("Gafas", false),
        CLOTHES("Ropa", false),
        COLOR("Color", false),
        WALL("Paredes", true),
        FLOOR("Suelo", true),
        FURNITURE("Muebles", true);

        private final String displayName;
        private final boolean room;

        Slot(String displayName, boolean room) {
            this.displayName = displayName;
            this.room = room;
        }

        public String getDisplayName() {
            return displayName;
        }

        public boolean isRoom() {
            return room;
        }
    }

    private final String name;
    private final int price;
    private final Slot slot;

    public Accessory(String name, int price, Slot slot) {
        this.name = name;
        this.price = price;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Slot getSlot() {
        return slot;
    }
}
