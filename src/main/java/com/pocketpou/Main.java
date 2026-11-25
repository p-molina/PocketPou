package com.pocketpou;

import com.pocketpou.controller.GameController;
import com.pocketpou.model.persistence.SaveGame;
import com.pocketpou.model.shop.Shop;
import com.pocketpou.view.ConsoleView;

import java.nio.file.Path;
import java.util.Random;

/* Punto de entrada de PocketPou. */
public class Main {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(System.in, System.out);
        Shop shop = new Shop();
        SaveGame saveGame = new SaveGame(Path.of("pocketpou-save.properties"));
        GameController controller = new GameController(view, shop, saveGame, new Random());
        controller.start();
    }
}
