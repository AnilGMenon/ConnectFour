
import java.util.Observable;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANIL MENON
 * @version 1.0
 */
public class ConnectFourGame extends Observable {

    private int nColumns;
    private int nRows;
    private int numToWin;
    private ConnectFourEnum[][] grid;
    private ConnectFourEnum gameState;
    private ConnectFourEnum turn;
    private int count;

    /**
     * user provides intialTurn however the board is set to be a 8x8.
     */
    public ConnectFourGame(ConnectFourEnum initialTurn) {
        this(8, 8, 4, initialTurn);
    }

    /**
     * sets the rows, columns, numToWin and turn.
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn) {
        if (nRows < 0 || nColumns < 0 || numToWin < 0) {
            throw new IllegalArgumentException("The nRows or nColumns or numToWin is smaller than threshold");
        }

        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.turn = initialTurn;
        grid = new ConnectFourEnum[this.nRows][this.nColumns];
        gameState = gameState.IN_PROGRESS;
        reset(initialTurn);

    }

    /**
     * Resets the board to an empty board.
     */
    public void reset(ConnectFourEnum initialTurn) {

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                grid[i][j] = turn.EMPTY;
            }
        }
    }

    public ConnectFourEnum takeTurn(int row, int column) {
        if (row < 0 || row > this.nRows) {
            throw new IllegalArgumentException("Grid is " + this.nRows + " by " + this.nColumns);
        }
        if (column < 0 || column > this.nColumns) {
            throw new IllegalArgumentException("Grid is " + this.nRows + " by " + this.nColumns);
        }
        if (this.grid[row][column] != ConnectFourEnum.EMPTY) {
            throw new IllegalArgumentException("Location is already full");
        }
        if (row > 0) {
            if (grid[row - 1][column] == ConnectFourEnum.EMPTY) {
                throw new IllegalArgumentException("Column below is empty");
            }
        }

        count += 1;
        int countB = 0;
        int countR = 0;

        if (count > 63) {
            gameState = ConnectFourEnum.DRAW;
            setChanged();
            ConnectMove temp = new ConnectMove(row, column, turn);
            notifyObservers(temp);
            return ConnectFourEnum.DRAW;
        }

        grid[row][column] = turn;
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nColumns; j++) {

                if (this.grid[i][j] == ConnectFourEnum.BLACK) {
                    countB++;
                    countR = 0;
                } else if (this.grid[i][j] == ConnectFourEnum.RED) {
                    countR++;
                    countB = 0;
                }
                if (countB == this.numToWin) {

                    gameState = ConnectFourEnum.BLACK;
                    setChanged();
                    ConnectMove temp = new ConnectMove(row, column, turn);
                    notifyObservers(temp);
                    return ConnectFourEnum.BLACK;
                } else if (countR == this.numToWin) {
                    gameState = ConnectFourEnum.RED;
                    setChanged();
                    ConnectMove temp = new ConnectMove(row, column, turn);
                    notifyObservers(temp);
                    return ConnectFourEnum.RED;
                }
            }
            countB = countR = 0;
        }

        for (int j = 0; j < this.nRows; j++) {

            for (int i = 0; i < this.nColumns; i++) {

                if (this.grid[i][j] == ConnectFourEnum.BLACK) {
                    countB++;
                    countR = 0;
                } else if (this.grid[i][j] == ConnectFourEnum.RED) {
                    countR++;
                    countB = 0;
                }
                if (countB == this.numToWin) {
                    gameState = ConnectFourEnum.BLACK;
                    setChanged();
                    ConnectMove temp = new ConnectMove(row, column, turn);
                    notifyObservers(temp);
                    return ConnectFourEnum.BLACK;
                } else if (countR == this.numToWin) {
                    gameState = ConnectFourEnum.RED;
                    setChanged();
                    ConnectMove temp = new ConnectMove(row, column, turn);
                    notifyObservers(temp);
                    return ConnectFourEnum.RED;
                }
            }
            countB = countR = 0;
        }

        if (turn == ConnectFourEnum.BLACK) {
            turn = ConnectFourEnum.RED;
        } else {
            turn = ConnectFourEnum.BLACK;
        }
        setChanged();
        ConnectMove temp = new ConnectMove(row, column, turn);
        notifyObservers(temp);
        return gameState;
    }

    /**
     * Finds game state by calling findWinner() method.
     */
    public ConnectFourEnum getGameState() {
        return gameState;
    }

    /**
     * Returns the turn.
     */
    public ConnectFourEnum getTurn() {
        return this.turn;
    }

    /**
     * Sets the gameState to IN_PROGRESS when resetting the board.
     */
    public void set(ConnectFourEnum s) {
        gameState = s;

    }

    /**
     * Returns a string implementation of the Connect Four board.
     */
    public String toString() {
        String newGrid = "";

        for (int i = nRows - 1; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {
                newGrid += grid[i][j] + " | ";
            }
            newGrid += "\n";
        }

        return newGrid;
    }

}
