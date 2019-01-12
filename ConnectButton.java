
import javafx.scene.control.Button;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANIL MENON
 * @ version 1.0
 */
public class ConnectButton extends Button {

    private int row;
    private int colunn;
    private Button label;

    /**
     * Initializes the row, column and label.
     */
    public ConnectButton(String label, int row, int column) {
        this.row = row;
        this.colunn = column;
        super.setText(label);

    }

    /**
     * Returns row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns column.
     */
    public int getColumn() {
        return colunn;
    }

    /*Returns (row, column). */
    public String toString() {
        return "( " + row + ", " + colunn + " )";
    }

}
