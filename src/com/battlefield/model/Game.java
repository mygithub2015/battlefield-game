package com.battlefield.model;

import java.util.Arrays;

import com.battlefield.service.constant.GameConstants;

public class Game {

    private int size;
    private ShipIdentifier[][] grid;
    private Player playerA;
    private Player playerB;
    private int turn;
    private GameStatus status;
    private String winner;

    public Game(int size) {
        this.size = size;
        this.grid = new ShipIdentifier[size][size];
        this.playerA = new Player(GameConstants.PLAYER_A_ID, "PlayerA");
        this.playerB = new Player(GameConstants.PLAYER_B_ID, "PlayerB");
        this.status = GameStatus.INITIALIZED;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShipIdentifier[][] getGrid() {
        return grid;
    }

    public void setGrid(ShipIdentifier[][] grid) {
        this.grid = grid;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public int getTurn() {
        return turn;
    }

    public void changeTurn() {
        this.turn++;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Game [size=" + size + ", grid=" + Arrays.toString(grid) + ", playerA=" + playerA + ", playerB="
                + playerB + ", turn=" + turn + ", status=" + status + ", winner=" + winner + "]";
    }

    

}

/*
 * [[]
 * 
 * ]
 * 
 * 
 * Ship[][] = new
 */
