package org.LLD1.strategies;

import org.LLD1.models.*;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getCell().getSymbol();

        for (Cell cell : board.getGrid().get(row)){
            if (cell.getCellState().equals(CellState.EMPTY))
                return false;
            if (!cell.getSymbol().equals(symbol))
                return false;
        }
        return true;
    }
}
