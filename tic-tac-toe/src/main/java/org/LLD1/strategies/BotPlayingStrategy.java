package org.LLD1.strategies;

import org.LLD1.models.Board;
import org.LLD1.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
