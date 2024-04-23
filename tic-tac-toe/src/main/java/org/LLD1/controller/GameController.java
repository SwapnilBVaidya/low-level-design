package org.LLD1.controller;

import org.LLD1.models.Game;
import org.LLD1.models.GameState;
import org.LLD1.models.Player;
import org.LLD1.strategies.WinningStrategy;

import java.util.List;

public class GameController     {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {

        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }
}
