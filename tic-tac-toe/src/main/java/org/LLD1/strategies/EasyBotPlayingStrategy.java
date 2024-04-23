package org.LLD1.strategies;

import org.LLD1.models.Board;
import org.LLD1.models.Cell;
import org.LLD1.models.CellState;
import org.LLD1.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row:board.getGrid()){
            for (Cell cell : row){
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(cell.getRow(), cell.getCol()), null);
                }
            }
        }
        return null;
    }
}
