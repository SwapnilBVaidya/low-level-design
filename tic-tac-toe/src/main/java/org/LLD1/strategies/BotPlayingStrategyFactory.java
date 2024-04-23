package org.LLD1.strategies;

import org.LLD1.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public  static BotPlayingStrategy getBotPlayingStrategyFactoryByDifficultLevel(BotDifficultyLevel botDifficultyLevel) {
        if (botDifficultyLevel.equals(BotDifficultyLevel.EASY))
            return new EasyBotPlayingStrategy();
        return null;
    }
}
