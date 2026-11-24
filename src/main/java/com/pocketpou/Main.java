package com.pocketpou;

import com.pocketpou.controller.GameController;
import com.pocketpou.model.item.Item;
import com.pocketpou.model.item.factory.FoodCreator;
import com.pocketpou.model.item.factory.ItemCreator;
import com.pocketpou.model.item.factory.MedicineCreator;
import com.pocketpou.model.pet.Pet;
import com.pocketpou.model.pet.PetAttributes;
import com.pocketpou.model.pet.PetSpecies;
import com.pocketpou.model.pet.state.HungryState;
import com.pocketpou.model.pet.state.SickState;
import com.pocketpou.view.ConsoleNotifier;
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

        PetAttributes attributes = new PetAttributes(80, 70, 60, 50, 1);
        Pet pet = new Pet("Pou", PetSpecies.CLASSIC_POU, attributes, new HungryState());

        view.showMessage("\nEstado inicial:");
        controller.showPet(pet);

        view.showMessage("\nIntenta jugar mientras tiene hambre:");
        pet.play();
        controller.showPet(pet);

        view.showMessage("\nCome dos veces y vuelve a estar feliz:");
        pet.feed();
        pet.feed();
        controller.showPet(pet);

        view.showMessage("\nJuega mientras está feliz:");
        pet.play();
        controller.showPet(pet);

        view.showMessage("\nEmpieza a dormir:");
        pet.sleep();
        controller.showPet(pet);

        view.showMessage("\nIntenta jugar mientras duerme:");
        pet.play();
        controller.showPet(pet);

        view.showMessage("\nDescansa y vuelve a estar feliz:");
        pet.sleep();
        controller.showPet(pet);

        view.showMessage("\nSe enferma y después se cura:");
        pet.updateAttributes(new PetAttributes(35, 30, 70, 70, 1));
        pet.changeState(new SickState());
        pet.heal();
        controller.showPet(pet);

        view.showMessage("\nPrueba del patrón Observer:");
        ConsoleNotifier notifier = new ConsoleNotifier(view);
        pet.addObserver(notifier);
        pet.updateAttributes(new PetAttributes(90, 15, 15, 15, 1));

        view.showMessage("\nObservador eliminado. La siguiente actualización no debe mostrar avisos:");
        pet.removeObserver(notifier);
        pet.updateAttributes(new PetAttributes(95, 10, 10, 10, 1));
        view.showMessage("Actualización finalizada sin notificaciones.");

        view.showMessage("\nPrueba del patrón Factory Method:");
        ItemCreator foodCreator = new FoodCreator();
        ItemCreator medicineCreator = new MedicineCreator();

        Item apple = foodCreator.createItem("Apple", 10, 20);
        Item sushi = foodCreator.createItem("Sushi", 30, 50);
        Item cookie = foodCreator.createItem("Cookie", 5, 10);
        Item basicMedicine = medicineCreator.createItem("Basic Medicine", 20, 30);
        Item advancedMedicine = medicineCreator.createItem("Advanced Medicine", 50, 80);

        view.showMessage(apple.getName() + " - Precio: " + apple.getPrice() + " monedas");
        view.showMessage(sushi.getName() + " - Precio: " + sushi.getPrice() + " monedas");
        view.showMessage(cookie.getName() + " - Precio: " + cookie.getPrice() + " monedas");
        view.showMessage(basicMedicine.getName() + " - Precio: "
                + basicMedicine.getPrice() + " monedas");
        view.showMessage(advancedMedicine.getName() + " - Precio: "
                + advancedMedicine.getPrice() + " monedas");
    }
}
