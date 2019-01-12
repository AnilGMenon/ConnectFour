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
public class ConnectMove {

    private int row;
    private int column;
    private ConnectFourEnum colour;

    /**
     * Initializes the instance variables row, column and color.
     */
    public ConnectMove(int row, int column, ConnectFourEnum colour) {
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

    /**
     * returns the row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * returns the column.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * returns the color of the pressed button.
     */
    public ConnectFourEnum getColour() {
        return this.colour;
    }
}
