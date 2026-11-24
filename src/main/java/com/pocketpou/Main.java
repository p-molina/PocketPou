package com.pocketpou;

import com.pocketpou.controller.GameController;
import com.pocketpou.view.ConsoleView;

/*
 * Responsabilidad: Iniciar la aplicación y conectar el controlador
 * con la vista de consola.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(System.in, System.out);
        GameController controller = new GameController(view);
        controller.start();
    }
}
