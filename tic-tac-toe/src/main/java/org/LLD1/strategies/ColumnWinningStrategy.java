package org.LLD1.strategies;

import org.LLD1.models.*;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getSymbol();;
        for(int i=0; i< board.getSize(); i++){
            Cell cell = board.getGrid().get(i).get(col);
            if (cell.getCellState().equals(CellState.EMPTY))
                return false;
            if (!cell.getSymbol().equals(symbol))
                return false;
        }
        return true;
    }
}
