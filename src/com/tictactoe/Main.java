package com.tictactoe;

import java.util.Properties;
import java.util.Scanner;

public class Main {
    private final static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        Properties properties = PropertyReader.readPropertiesFile("tictactoe.properties");

        int width = Integer.parseInt(properties.getProperty("width"));
        int height = Integer.parseInt(properties.getProperty("height"));
        int wincondition = Integer.parseInt(properties.getProperty("wincondition"));

        if(!validateSize(width) || !validateSize(height)){
            System.out.println("A játéktér paraméterei 5-nél nagyobb vagy egyenlő és 100-nál kisebb szám kell legyenek!");
            return;
        }

        boolean exit = false;
        Game game = new Game();
        while(!exit){
            System.out.println();
            int input = getInput();
            switch (input) {
                case 1: game.newGame(width, height, wincondition); break;
                case 2: loadGame(game); break;
                case 3: exit = true; break;
            }
        }

    }

    private static boolean validateSize(int param) {
        return param >= 5 && param < 100;
    }

    private static void loadGame(Game game) {
        System.out.println("Melyik mentést szeretné betölteni?");
        String filename = scn.nextLine();
        if(filename.endsWith(".sav")){
            System.out.println("A megadott fájlnév a kiterjesztést is tartalmazza!");
            loadGame(game);
        }
        GameData gameData = SaveFileHandler.load(filename);
        if(gameData == null){
            System.out.println("Ez a mentés hibás vagy nem létezik!");
            loadGame(game);
        }

        game.loadGame(gameData);
    }

    private static int getInput() {
        System.out.println("1. Új játék indítása");
        System.out.println("2. Játék betöltése");
        System.out.println("3. Kilépés");
        while (true) {
            try {
                String next = scn.nextLine();
                int input = Integer.parseInt(next);
                if(input>0 && input<=3){
                    return input;
                } else {
                    System.out.println("A megadott számnak 1 és 3 között kell lennie!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Adja meg a menüpont számát!");
            }
        }
    }


}
