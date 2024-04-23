package org.LLD1.models;


import org.LLD1.strategies.BotPlayingStrategy;
import org.LLD1.strategies.BotPlayingStrategyFactory;


public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY; // default
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(int id, String name, Symbol playerSymbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, PlayerType.BOT, playerSymbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyFactoryByDifficultLevel(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
