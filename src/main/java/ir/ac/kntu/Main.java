package ir.ac.kntu;

import ir.ac.kntu.CLI.MainMenu;
import ir.ac.kntu.logic.JsonGenerator;

public class Main {
    public static void main(String[] args) {
        JsonGenerator jsonGenerator = new JsonGenerator();
        MainMenu.main(jsonGenerator.generateBuyRecipe());
    }
}
