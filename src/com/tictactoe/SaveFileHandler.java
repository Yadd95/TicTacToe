package com.tictactoe;

import java.io.*;
import java.util.Scanner;

public class SaveFileHandler {
    private final static Scanner scn = new Scanner(System.in);

    public static GameData load(String fileName){
        File file = new File("saves/" + fileName + ".sav");
        if (!file.exists()) {
            return null;
        }
        return loadData(file);
    }

    public static void save(String fileName, String content)  {
        File file = new File("saves/" + fileName + ".sav");
        if (!file.exists()) {
            try {
                file.createNewFile();
                saveData(file, content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ilyen nevű fájl már létezik! Felül szeretné írni? (Igen/Nem)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("igen")){
                saveData(file, content);
            } else if(input.equalsIgnoreCase("nem")){
                System.out.println("Adjon meg egy másik fájlnevet!");
                input = scn.nextLine();
                save(input, content);
            } else {
                return;
            }
        }
    }

    private static void saveData(File file, String content) {
        try (
                FileOutputStream fileStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(fileStream);
                BufferedWriter bufferWriter = new BufferedWriter(writer)
        ) {
            bufferWriter.write(content);
            bufferWriter.flush();
            System.out.println("Mentve!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GameData loadData(File file) {
        try (
                FileInputStream fileStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fileStream);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            int boardHeight = Integer.parseInt(bufferedReader.readLine());
            int boardWidth = Integer.parseInt(bufferedReader.readLine());
            int winCondition = Integer.parseInt(bufferedReader.readLine());
            int freeSpace = Integer.parseInt(bufferedReader.readLine());
            boolean playerOneTurn = Boolean.parseBoolean(bufferedReader.readLine());

            int[][] board = new int[boardHeight][boardWidth];

            for(int i = 0; i < boardHeight; i++){
                String line = bufferedReader.readLine();
                String[] splitLine = line.split(",");
                for(int j = 0; j < boardWidth; j++){
                    board[i][j] = Integer.parseInt(splitLine[j]);
                }
            }

            System.out.println("Betöltve!");
            return new GameData(board, freeSpace, playerOneTurn, boardWidth, boardHeight, winCondition);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
