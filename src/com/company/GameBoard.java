package com.company;

import java.util.Scanner;

/**
 * @author Gonzalo Ortiz <gonzalo.ortizbianco@cs.tamk.fi>
 * @version 2016.1220
 * @since 1.8
 */
public class GameBoard {

    /**
     * Stores the vertical size of the board.
     */
    private int xsize;
    /**
     * Stores the horizontal size of the board.
     */
    private int ysize;
    /**
     * Stores the required characters in a row to win.
     */
    private int toWin;
    /**
     * board used to play the game, populates itself upon start of game.
     */
    private String[][] board;

    /**
     * Setter for the board
     *
     * @param board input board to be set
     */
    public void setBoard(String[][] board) {
        this.board = board;
    }

    /**
     * gets board-variable from our object.
     *
     * @return currently stored board-variable
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * gets vertical size of the object
     *
     * @return vertical size of object
     */
    public int getXsize() {
        return xsize;
    }

    /**
     * gets horizontal size of the object
     *
     * @return horizontal size of object
     */
    public int getYsize() {
        return ysize;
    }

    /**
     * gets ammount needed to win a game
     *
     * @return the integer ammount of consecutive characters to win
     */
    public int getToWin() {
        return toWin;
    }

    /**
     * Creates a GameBoard-type object.
     * <p>
     * Asks the user for different inputs such as x-axis size, y-axis size and
     * consecutive symbols to win, then creates a string[][] based on the given
     * sizes and populates it using GameEngine.populateBoard
     */

    public GameBoard() {
        System.out.println("Please input board x-axis size: ");
        this.xsize = Helpers.inputInt();
        System.out.println("Please input board y-axis size: ");
        this.ysize = Helpers.inputInt();
        System.out.println("Please input ammount of consecutive symbols to win: ");
        boolean test = true;
        while (test == true) {
            this.toWin = Helpers.inputInt();
            if (toWin > xsize || toWin > ysize) {
                System.out.println("Victory condition is too high compared to board size! Please input a new condition: ");
            } else {
                test = false;
            }
        }
        board = new String[this.xsize][this.ysize];
        board = GameEngine.populateBoard(this);
    }
}

