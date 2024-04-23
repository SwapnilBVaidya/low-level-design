package org.LLD1.models;

import org.LLD1.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerIndex;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies)
    {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void displayBoard(){
        board.display();
    }

    public void makeMove(){
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn! Please make your move.");
        Move move = currentPlayer.makeMove(board);
        if (!this.validateMove(move))
        {
            System.out.println("Invalid Move! Please try again.");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToChange = this.board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getPlayerSymbol());
        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);
        this.moves.add(move);
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

        if (checkWinner(board, move)){
            gameState = GameState.SUCCESS;
            winner = currentPlayer;
        } else if(moves.size() == board.getSize()* board.getSize())
            gameState = GameState.DRAW;
    }

    private boolean checkWinner(Board board, Move move) {
        for (WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move, board))
                return true;
        }
        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize())
            return false;
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void undo() {
        if (moves.size() == 0){
            System.out.println("Nothing to Undo!");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(moves.size()-1);

        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setSymbol(null);
        nextPlayerIndex -= 1;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();
    }

    public static class Builder {
        int dimension;
        List<Player> players;
        List<WinningStrategy> winningStrategies;
        Builder(){
            this.dimension = 3;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy){
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        private void validateBotCount(){
            int botCount = 0;
            for (Player player : this.players)
                if(player.getPlayerType().equals(PlayerType.BOT))
                    botCount++;
            if(botCount > 1)
                throw new RuntimeException("Only one bot is allowed.");
        }
        private void validateNoOfPlayers(){
            if (players.size() != this.dimension-1)
                throw new RuntimeException("Number of player is Invalid.");
        }
        private void validateSymbolCount(){
            HashSet<Symbol> symbols = new HashSet<>();
            for (Player player : players){
                if(!symbols.add(player.getPlayerSymbol())){
                    throw new RuntimeException("No two players can have same symbol");
                }
            }

        }

        private void validate(){
            this.validateBotCount();
            this.validateNoOfPlayers();
            this.validateSymbolCount();
        }

        public Game build(){
            validate();
            return new Game(this.dimension, this.players, this.winningStrategies);
        }

    }

    public static Builder getBuilder(){
        return new Builder();
    }
}
