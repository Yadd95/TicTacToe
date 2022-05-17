package com.tictactoe;

public class GameData {
    public int[][] board;
    public int freeSpace;
    public boolean playerOneTurn;
    private int boardWidth;
    private int boardHeight;
    private int winCondition;

    public GameData(int[][] board, int freeSpace, boolean playerOneTurn, int boardWidth, int boardHeight, int wincondition) {
        this.board = board;
        this.freeSpace = freeSpace;
        this.playerOneTurn = playerOneTurn;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.winCondition = wincondition;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getWinCondition() {
        return winCondition;
    }
}
