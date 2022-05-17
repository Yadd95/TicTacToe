package com.tictactoe;

import java.util.Scanner;

public class Game {
    private GameData gameData;
    private final Scanner scn = new Scanner(System.in);

    public void newGame(int width, int height, int winCondition) {
        System.out.println("Új játék!");
        this.gameData = new GameData(new int[height][width], height*width, true, width, height, winCondition);
        runGame();
    }

    public void loadGame(GameData loadedData) {
        gameData = loadedData;
        runGame();
    }

    private void runGame() {
        while(!checkWin()){
            nextTurn();
            gameData.playerOneTurn = !gameData.playerOneTurn;
            gameData.freeSpace--;
            if(gameData.freeSpace ==0) {
                System.out.println("A meccs döntetlennel zárult!");
                break;
            }
            drawBoard();
        }
    }

    private void drawBoard() {
        System.out.println("Győzelemhez szükséges: " + gameData.getWinCondition());
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i <= gameData.getBoardWidth(); i++){
            if(i==0){
                builder.append("    ");
            } else {
                if(i<10){
                    builder.append(" ");
                }
                builder.append(i).append(". ");
            }
            builder.append("|");
        }
        builder.append(System.getProperty("line.separator"));
        for (int k = 0; k<=gameData.getBoardWidth(); k++){
            builder.append("----+");
        }
        builder.append(System.getProperty("line.separator"));
        for(int i = 0; i < gameData.getBoardHeight(); i++){
            builder.append(i+1).append(". ");
            if(i+1<10){
                builder.append(" ");
            }
            builder.append("|");
            for(int j = 0; j < gameData.getBoardWidth(); j++){
                switch (gameData.board[i][j]){
                    case 0: builder.append("    "); break;
                    case 1: builder.append(" X  "); break;
                    case 2: builder.append(" O  "); break;
                }
                if(j!=gameData.getBoardWidth()-1){
                    builder.append("|");
                }
            }
            builder.append("|").append(System.getProperty("line.separator"));

            for (int k = 0; k<= gameData.getBoardWidth(); k++){
                builder.append("----+");
            }
            builder.append(System.getProperty("line.separator"));
        }

        System.out.println(builder);
    }

    private void nextTurn() {
        if(gameData.playerOneTurn){
            takeAction("X játékos köre", 1);
        }else{
            takeAction("O játékos köre", 2);
        }
    }

    private void takeAction(String playerPrompt, int playerNumber) {
        System.out.println(playerPrompt);
        int x = getInput("Adja meg a sor számát!", "A sorszám szám kell legyen!", gameData.getBoardHeight()) -1;
        int y = getInput("Adja meg az oszlop számát!", "Az oszlopszám szám kell legyen!", gameData.getBoardWidth()) -1;

        while(gameData.board[x][y] != 0){
            System.out.println("A megadott mező már foglalt! Adjon meg egy új mezőt!");
            x = getInput("Adja meg a sor számát!", "A sorszám szám kell legyen!", gameData.getBoardHeight()) -1;
            y = getInput("Adja meg az oszlop számát!", "Az oszlopszám szám kell legyen!", gameData.getBoardWidth()) -1;
        }
        gameData.board[x][y] = playerNumber;
    }

    private int getInput(String prompt, String warning, int limit) {
        System.out.println(prompt);
        while (true) {
            try {
                String next = scn.nextLine();
                if(next.startsWith("ment ")){
                    SaveFileHandler.save(next.split(" ")[1], getGameData());
                    System.out.println(prompt);
                    continue;
                }
                int input = Integer.parseInt(next);
                if(input>0 && input<=limit){
                    return input;
                } else {
                    System.out.println("A megadott számnak 1 és " + limit + " között kell lennie!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println(warning);
            }
        }
    }


    private String getGameData() {
        StringBuilder builder = new StringBuilder();
        builder.append(gameData.getBoardHeight()).append(System.getProperty("line.separator"));
        builder.append(gameData.getBoardWidth()).append(System.getProperty("line.separator"));
        builder.append(gameData.getWinCondition()).append(System.getProperty("line.separator"));
        builder.append(gameData.freeSpace).append(System.getProperty("line.separator"));
        builder.append(gameData.playerOneTurn).append(System.getProperty("line.separator"));
        for(int i = 0; i < gameData.getBoardHeight(); i++){
            for(int j = 0; j < gameData.getBoardWidth(); j++){
                builder.append(gameData.board[i][j]);
                if(j!=gameData.getBoardWidth()-1){
                    builder.append(",");
                }
            }
            builder.append(System.getProperty("line.separator"));
        }


        return builder.toString();
    }

    private boolean checkWin(){
        return checkHorizontal() || checkVertical() || checkDiagonal() || checkReverseDiagonal();
    }

    private boolean checkVertical() {
        for(int j=0;j<gameData.getBoardWidth();j++){
            int xCount=0;
            int oCount=0;
            for(int i=0;i<gameData.getBoardHeight();i++){
                if(gameData.board[i][j]==1)
                    xCount++;
                else if(gameData.board[i][j]==2)
                    oCount++;
            }
            if (xCount == gameData.getWinCondition()) {
                System.out.println("X Nyert!");
                return true;
            } else if (oCount == gameData.getWinCondition()) {
                System.out.println("O Nyert!");
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        for(int i=0;i<gameData.getBoardHeight();i++){
            int xCount=0;
            int oCount=0;
            for(int j=0;j<gameData.getBoardWidth();j++){
                if(gameData.board[i][j]==1)
                    xCount++;
                else if(gameData.board[i][j]==2)
                    oCount++;
            }
            if (xCount == gameData.getWinCondition()) {
                System.out.println("X Nyert!");
                return true;
            } else if (oCount == gameData.getWinCondition()) {
                System.out.println("O Nyert!");
                return true;
            }
        }
        return false;
    }

    private boolean checkReverseDiagonal() {
        int j;
        int i;
        int xCount;
        int oCount;

        for(int base = gameData.getBoardWidth()-1; base>=0; base--) {
            j = base;
            i = 0;
            xCount = 0;
            oCount = 0;
            while (i < gameData.getBoardHeight() && j >= 0) {
                if (gameData.board[i][j] == 1)
                    xCount++;
                else if (gameData.board[i][j] == 2)
                    oCount++;
                i++;
                j--;
            }
            if (xCount == gameData.getWinCondition()) {
                System.out.println("X Nyert!");
                return true;
            } else if (oCount == gameData.getWinCondition()) {
                System.out.println("O Nyert!");
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        for(int base=0;base<gameData.getBoardHeight();base++) {
            int i = base;
            int j = 0;
            int xCount = 0;
            int oCount = 0;

            // check diagonals
            while (i < gameData.getBoardHeight() && j < gameData.getBoardWidth()) {
                if (gameData.board[i][j] == 1)
                    xCount++;
                else if (gameData.board[i][j] == 2)
                    oCount++;
                i++;
                j++;
            }
            if (xCount == gameData.getWinCondition()) {
                System.out.println("X Nyert!");
                return true;
            } else if (oCount == gameData.getWinCondition()) {
                System.out.println("O Nyert!");
                return true;
            }
        }
        return false;
    }
}
