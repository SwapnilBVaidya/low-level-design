package org.LLD1.strategies;

import org.LLD1.models.*;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Move move, Board board) {
        int diagonal = this.diagonalCheck(move.getCell().getRow(), move.getCell().getCol(), board.getSize());

        if (diagonal == 1){
            return checkMainDiagonal(move.getCell().getSymbol(), board);
        } else if (diagonal == 2) {
            return checkSecondaryDiagonal(move.getCell().getSymbol(), board);
        } else if (diagonal == 3){
            return checkMainDiagonal(move.getCell().getSymbol(), board) ||  checkSecondaryDiagonal(move.getCell().getSymbol(), board);
        }
        return false;
    }

    /*
    return 0 if element is not on diagonal
    return 1 if element is on main diagonal
    return 2 if element is on secondary diagonal
    return 3 if element is center element
     */
    private int diagonalCheck(int row, int col, int size){

        if (size % 2 == 1){
            int centerIdx = size/2;
            if (centerIdx == row && centerIdx == col)
                return 3;
        }

        if(row == col) return 1;
        if (Math.abs(row-col) == size-1) return 2;
        return 0;
    }

    private boolean checkMainDiagonal(Symbol symbol, Board board){
        for (int i=0; i< board.getSize(); i++){
            Cell cell = board.getGrid().get(i).get(i);
            if (cell.getCellState().equals(CellState.EMPTY)) return false;
            if (!cell.getSymbol().equals(symbol)) return false;
        }
        return true;
    }

    private boolean checkSecondaryDiagonal(Symbol symbol, Board board){
        for (int i=0, j= board.getSize()-1; i< board.getSize() && j>=0; i++, j--){
                Cell cell = board.getGrid().get(i).get(j);
                if (cell.getCellState().equals(CellState.EMPTY)) return false;
                if (!cell.getSymbol().equals(symbol)) return false;
        }
        return true;
    }
}
