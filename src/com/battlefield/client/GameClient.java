package com.battlefield.client;

import java.util.Scanner;

import com.battlefield.game.strategy.RandomGameStrategy;
import com.battlefield.service.GameService;

public class GameClient {

    private GameService gameService;

    public GameClient(GameService gameService) {
        this.gameService = gameService;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public static void main(String [] args) throws InterruptedException {

        GameClient client = new GameClient(new GameService(new RandomGameStrategy()));
        Scanner inp = new Scanner(System.in);
        System.out.println("\nEnter the battlefield size to initialize the game: ");
        int size = Integer.valueOf(inp.nextLine());
        //initializing the game
        client.getGameService().initGame(size);
        client.getGameService().viewBattleField();
        while(true) {
            System.out.print("\nEnter the ship name(exactly 2-chars desired, more than that would be truncated): ");
            String shipName = inp.nextLine();
            shipName = shipName.substring(0, 2);
            System.out.print("Enter the ship size: ");
            int shipSize = Integer.valueOf(inp.nextLine());
            int xA, yA;
            while(true) {
                System.out.printf("Enter the X-axis(abscissa) value for ship for player A[%d-%d]: ", 0, size - 1);
                xA = Integer.valueOf(inp.nextLine());
                if(xA < 0 || xA >= size) {
                    System.out.println("Invalid input, Please try again!");
                    continue;
                }
                break;
            }
            while(true) {
                System.out.printf("Enter the Y-axis(ordinate) value for ship for player A[%d-%d]: ", 0, size / 2 - 1);
                yA = Integer.valueOf(inp.nextLine());
                if(yA < 0 || yA >= size / 2) {
                    System.out.println("Invalid input, Please try again!");
                    continue;
                }
                break;
            }
            int xB, yB;
            while(true) {
                System.out.printf("Enter the X-axis(abscissa) value for ship for player B[%d-%d]: ", 0, size - 1);
                xB = Integer.valueOf(inp.nextLine());
                if(xB < 0 || xB >= size) {
                    System.out.println("Invalid input, please try again!");
                    continue;
                }
                break;
            }
            while(true) {
                System.out.printf("Enter the Y-axis(ordinate) value for ship for player B[%d-%d]: ",size / 2, size - 1);
                yB = Integer.valueOf(inp.nextLine());
                if(yB < size / 2 || yB >= size) {
                    System.out.println("Invalid input, please try again!");
                    continue;
                }
                break;
            }
            client.getGameService().addShip(shipName, shipSize, xA, yA, xB, yB);
            System.out.print("\nWant to add more ships? Please press 'Y' else 'N': ");
            String choice = inp.nextLine();
            if(choice.equalsIgnoreCase("N")) {
                break;
            }
            
        }
        System.out.println("\nPress 'Y' to start the game, else press 'N': ");
        String choice = inp.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            client.getGameService().startGame();
        }
        inp.close();
    }
    
}
