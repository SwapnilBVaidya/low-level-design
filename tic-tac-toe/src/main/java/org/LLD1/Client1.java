package org.LLD1;

import org.LLD1.controller.GameController;
import org.LLD1.models.*;
import org.LLD1.strategies.ColumnWinningStrategy;
import org.LLD1.strategies.DiagonalWinningStrategy;
import org.LLD1.strategies.RowWinningStrategy;
import org.LLD1.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {

        System.out.println("Welcome to the game of Tic-Tac-Toe!");

        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Game is Starting!!!");

        try {
            int dimension = 3;
            List<Player> players = new ArrayList<>();
            players.add(new Player(1, "Mohit", PlayerType.HUMAN, new Symbol('X', "Blue")));
            players.add(new Player(2, "Rohit", PlayerType.HUMAN, new Symbol('0', "Blue")));
            List<WinningStrategy> winningStrategies
                    = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            Game game = gameController.startGame(dimension, players, winningStrategies);
            gameController.displayBoard(game);
            while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
                gameController.makeMove(game);
                System.out.println("Do you want to undo? Give me Y/N");
                String x = sc.next();
                if (x.equals("Y")){
                    gameController.undo(game);
                }
                gameController.displayBoard(game);
            }

            if (gameController.getGameState(game).equals(GameState.SUCCESS)){
                System.out.println("*****GAME OVER*****");
                System.out.println("Winner is " + game.getWinner().getName());
            } else {
                System.out.println("*****GAME OVER*****");
                System.out.println("Game ends with a Draw");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Something wen wrong! Please start again.");
        }
    }
}