package com.battlefield.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.battlefield.game.strategy.GameStrategy;
import com.battlefield.model.Game;
import com.battlefield.model.GameStatus;
import com.battlefield.model.Pair;
import com.battlefield.model.Player;
import com.battlefield.model.Ship;
import com.battlefield.model.ShipIdentifier;
import com.battlefield.model.ShipStatus;
import com.battlefield.service.constant.GameConstants;



public class GameService {

    private Game game;
    private int idIncrementor;
    private Map<Pair, Ship> shipsMap;
    private GameStrategy gameStrategy;

    public GameService(GameStrategy gameStrategy) {
        this.idIncrementor = 1;
        this.shipsMap = new HashMap<>();
        this.gameStrategy = gameStrategy;
    }

    private void printStartLine(String methodName) {
        System.out.println("\n***********************"+" START - "+methodName+" **************************");
    }

    private void printEndLine(String methodName) {
        System.out.println("\n$$$$$$$$$$$$$$$$$$$$"+" END - "+methodName+" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    public void initGame(int size) {
        String methodName = "initGame";
        printStartLine(methodName);
        System.out.println("Game is being setup...");
        this.game = new Game(size);
        System.out.println("Players getting created...");
        System.out.println("Player-1: "+this.game.getPlayerA());
        System.out.println("Player-2: "+this.game.getPlayerB());
        System.out.printf("\nBattlefield of size %d is created", this.game.getSize());
        System.out.println("\nGame setup is ready now...");
        printEndLine(methodName);

    }

    public void startGame() throws InterruptedException {
        String methodName = "startGame";
        printStartLine(methodName);
        System.out.println("Game started...");
        this.game.setStatus(GameStatus.IN_PROGRESS);
        while(isGameOn()) {
            Player current = getNextPlayer();
            Player opponent = getOpponentPlayer(current);
            Pair attackLoc;
            if(current.getId() != GameConstants.PLAYER_A_ID) {
                attackLoc = getNextAttackLocation(0);
            } else {
                attackLoc = getNextAttackLocation(this.game.getSize() / 2);
            }
            Ship destoryedShip = destoryPlayerShip(opponent, attackLoc);
            Thread.sleep(1000);
            if(destoryedShip != null) {
                System.out.printf("\n%s's turn: Missile fired at (%d, %d). \"Hit\". %s's ship with id \"%s\" destroyed.\n", 
                    current.getName(), attackLoc.getX(), attackLoc.getY(), opponent.getName(), destoryedShip.getIdentifier().getName().split("-")[1]);
            } else {
                System.out.printf("\n%s's turn: Missile fired at (%d, %d). \"Miss\".\n", 
                    current.getName(), attackLoc.getX(), attackLoc.getY());
            }
            updateGameIfWonAgainstPlayer(opponent);
            viewBattleField();
            this.game.changeTurn();
        }
        System.out.println("\n...And the winner of the game is, \""+this.game.getWinner()+"\"");
        System.out.println("\nPlayer-1 ships: "+this.game.getPlayerA().getShips());
        System.out.println("Player-2 ships: "+this.game.getPlayerB().getShips());
        printEndLine(methodName);
    }

    public void addShip(String id, int size, int xA, int yA, int xB, int yB) {
        String methodName = "addShip";
        printStartLine(methodName);
        System.out.printf("\nShip with id \"%s\" and size %d being created for both the players...", id, size);
        System.out.printf("\nShip co-ordinates for Player-1: (%d, %d)", xA, yA);
        System.out.printf("\nShip co-ordinates for Player-2: (%d, %d)", xB, yB);
        ShipIdentifier[][] grid = this.game.getGrid();
        ShipIdentifier shipIdA = new ShipIdentifier(xA, yA, size, "A-"+id);
        Ship shipA = new Ship(idIncrementor++, this.game.getPlayerA(), shipIdA);
        ShipIdentifier shipIdB = new ShipIdentifier(xB, yB, size, "B-"+id);
        Ship shipB = new Ship(idIncrementor++, this.game.getPlayerB(), shipIdB);
        List<Pair> shipACells = new ArrayList<>();
        for(int i = xA; i < xA + size; i++) {
            for(int j = yA; j < yA + size; j++) {
                Pair p = new Pair(i, j);
                shipACells.add(p);
                this.shipsMap.put(p, shipA);
                grid[i][j] = shipIdA;
            }
        }
        shipA.setCells(shipACells);
        List<Pair> shipBCells = new ArrayList<>();
        for(int i = xB; i < xB + size; i++) {
            for(int j = yB; j < yB + size; j++) {
                Pair p = new Pair(i, j);
                shipBCells.add(p);
                this.shipsMap.put(p, shipB);
                grid[i][j] = shipIdB;
            }
        }
        shipB.setCells(shipBCells);
        this.game.getPlayerA().addShip(shipA);
        this.game.getPlayerB().addShip(shipB);
        System.out.println("Ships are created...");
        System.out.println("Player-1 ships: "+this.game.getPlayerA().getShips());
        System.out.println("Player-2 ships: "+this.game.getPlayerB().getShips());
        viewBattleField();
        printEndLine(methodName);
    }

    public void viewBattleField() {
        String methodName = "viewBattleField";
        printStartLine(methodName);
        System.out.println("\nBattlefield currently looks like as...");
        int size = this.game.getSize();
        ShipIdentifier[][] grid = this.game.getGrid();
        for(int i = 0; i < size; i++) {
            System.out.print("||");
            for(int j = 0; j < size; j++) {
                System.out.print("|");
                if(grid[i][j] != null) {
                    System.out.print(grid[i][j].getName());
                } else {
                    System.out.print("____");
                }
                System.out.print("|");
            }
            System.out.println("||");
        }
        printEndLine(methodName);
    }

    private boolean isGameOn() {
        return this.game.getStatus() == GameStatus.IN_PROGRESS || this.game.getStatus() == GameStatus.INITIALIZED;
    }

    private Pair getNextAttackLocation(int M) {
        return this.gameStrategy.getNextCoordinates(this.game.getSize(), M);
    }

    private Ship destoryPlayerShip(Player opponent, Pair co) {
        if(!this.shipsMap.containsKey(co)) {
            return null;
        }
        Ship ship = this.shipsMap.get(co);
        ShipIdentifier[][] grid = this.game.getGrid();
        for(Pair p : ship.getCells()) {
            grid[p.getX()][p.getY()] = null;
            this.shipsMap.remove(p);
        }
        ship.setStatus(ShipStatus.DESTRYOED);
        opponent.getShips().remove(ship);
        return ship;
    }

    private void updateGameIfWonAgainstPlayer(Player opponent) {
        int opponentLeftShips = opponent.getShips().size();
        if(opponentLeftShips == 0) {
            this.game.setStatus(GameStatus.WON);
            this.game.setWinner(getOpponentPlayer(opponent).getName());
        }
    }

    private Player getNextPlayer() {
        return this.game.getTurn() % 2 == 0 ? this.game.getPlayerA() : this.game.getPlayerB();
    }

    private Player getOpponentPlayer(Player player) {
        if(player.getId() == GameConstants.PLAYER_A_ID) {
            return this.game.getPlayerB();
        }
        return this.game.getPlayerA();
    }
    
}
