package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Gonzalo Ortiz <gonzalo.ortizbianco@cs.tamk.fi>
 * @version 2016.1220
 * @since 1.8
 */

public class GameEngine {

    /**
     * Populates the gameboard with numbers.
     *
     * @param board board that will be populated.
     * @return returns a String[][] populated with numbers
     */

    public static String[][] populateBoard(GameBoard board) {
        String[][] gameBoard = new String[board.getXsize()][board.getYsize()];
        int continuation = 1;
        int continuation2 = 1;
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                int temp = x + continuation;
                gameBoard[y][x] = Integer.toString(temp);
                continuation2++;
            }
            continuation = continuation2;
        }
        return gameBoard;
    }

    /**
     * Displays the current state of the board.
     *
     * @param board board that the method displays
     */
    public static void showBoard(String[][] board) {
        for (int y = 0; y < board.length; y++) {
            System.out.print("|");
            for (int x = 0; x < board[y].length; x++) {
                System.out.print(" " + board[y][x] + " |");
            }
            System.out.println("");
        }
    }

    /**
     * used to play a turn for either the bot or the player.
     *
     * @param bot    checks if the current player whose turn it is is a bot or not
     * @param symbol saves which symbol the current player is using
     * @param board  is the board that we're going to modify by placing a character
     * @return returns modified board after turn has been played
     */
    public static String[][] playTurn(boolean bot, char symbol, String[][] board) {
        // the logic that plays a turn.
        boolean test = true;
        if (!bot) {
            while (test) {
                System.out.println("Write a number to play, or ''save'', ''load'': ");
                String input = Helpers.inputString();
                if (input.toLowerCase().equals("save")) {
                    GameEngine.saveGame(board, "save");
                    System.out.println("Game saved succesfully!");
                    return board;
                } else if (input.toLowerCase().equals("load")) {
                    return GameEngine.loadGame();
                }
                for (int x = 0; x < board.length; x++) {
                    for (int y = 0; y < board.length; y++) {
                        if (board[x][y].equals(input)) {

                            try {
                                if (!board[x][y].equals("O") && !board[x][y].equals("X")) {
                                    board[x][y] = "";
                                    board[x][y] = Character.toString(symbol);
                                    test = false;
                                } else {
                                    System.out.println("That spot is already occupied!");
                                }
                            } catch (Exception e) {
                                System.out.println("Your input was out of bounds!");
                            }

                        }
                    }
                }
            }
        }
        //else is for bot logic.
        // for now, the bot does not think at all and just plays stupidly.
        else {
            while (test) {
                //System.out.println("Playing bot turn..");
                int x = (int) (Math.random() * board.length);
                int y = (int) (Math.random() * board[0].length);
                if (!board[x][y].equals("O") && !board[x][y].equals("X")) {
                    board[x][y] = "";
                    board[x][y] = Character.toString(symbol);
                    test = false;
                }
            }
        }
        return board;
    }

    /**
     * Method used to check if either player has won.
     *
     * @param board   board that the method is going to check.
     * @param victory the ammount of consecutive characters needed to win.
     * @return returns a player-belonging character if a character has won, otherwise returns character '-'
     */
    public static char checkWin(String[][] board, int victory) {
        int playerX = 0;
        int playerY = 0;
        char winner = '-';
        //Vaakatsekkaus
        // Pystyy tekemään foreach-loopilla.
        for (int x = 0; x < board.length; x++) {
            playerX = 0;
            playerY = 0;
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y].equals("X")) {
                    playerX++;
                    playerY = 0;
                } else if (board[x][y].equals("O")) {
                    playerY++;
                    playerX = 0;
                } else {
                    playerX = 0;
                    playerY = 0;
                }
                if (playerX == victory) {
                    winner = 'X';
                    return winner;
                } else if (playerY == victory) {
                    winner = 'O';
                    return winner;
                }

            }
        }
        //Pystyteskkaus
        playerX = 0;
        playerY = 0;
        int counter = 0;
        boolean countTest = true;
        while (countTest && counter < board.length) {
            playerX = 0;
            playerY = 0;
            for (int x = 0; x < board.length; x++) {
                if (board[x][counter].equals("X")) {
                    playerX++;
                    playerY = 0;
                } else if (board[x][counter].equals("O")) {
                    playerY++;
                    playerX = 0;
                } else {
                    playerX = 0;
                    playerY = 0;
                }

                if (playerX == victory) {
                    winner = 'X';
                    return winner;
                    //countTest = false;
                } else if (playerY == victory) {
                    winner = 'O';
                    return winner;
                    //countTest = false;
                }
            }
            counter++;
        }

        // Checking the board diagonally.
        playerX = 0;
        playerY = 0;
        int counter1 = 0;
        int counter2 = 0;
        int finalCounter = 0;
        boolean startTest = true;
        boolean test1 = true;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        while (startTest && finalCounter <= board.length) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    //testing diagonally right and down for playerX
                    while (test1) {
                        try {
                            if (board[x + counter1][y + counter2].equals("X")) {
                                playerX++;
                                counter1++;
                                counter2++;
                            } else {
                                test1 = false;
                                test2 = true;
                            }
                            if (playerX == victory) {
                                winner = 'X';
                                return winner;
                            }
                        } catch (Exception e) {
                            test1 = false;
                            test2 = true;
                        }
                    }
                    playerX = 0;
                    counter1 = 0;
                    counter2 = 0;
                    //testing diagonally right and down for playerY
                    while (test2) {
                        try {
                            if (board[x + counter1][y + counter2].equals("O")) {
                                playerY++;
                                counter1++;
                                counter2++;
                            } else {
                                test2 = false;
                                test3 = true;
                            }
                            if (playerX == victory) {
                                winner = 'O';
                                return winner;
                            }
                        } catch (Exception e) {
                            test2 = false;
                            test3 = true;
                        }
                    }
                    playerY = 0;
                    counter1 = 0;
                    counter2 = 0;
                    //testing diagnoally right and up for playerX
                    while (test3) {
                        try {
                            if (board[x - counter1][y + counter2].equals("X")) {
                                playerX++;
                                counter1++;
                                counter2++;
                            } else {
                                test3 = false;
                                test4 = true;
                            }
                            if (playerX == victory) {
                                winner = 'X';
                                return winner;
                            }
                        } catch (Exception e) {
                            test3 = false;
                            test4 = true;
                        }
                    }
                    playerX = 0;
                    counter1 = 0;
                    counter2 = 0;
                    //testing diagonally right and up for playerY
                    while (test4) {
                        try {
                            if (board[x - counter1][y + counter2].equals("O")) {
                                playerY++;
                                counter1++;
                                counter2++;
                            } else {
                                test4 = false;
                            }
                            if (playerY == victory) {
                                winner = 'O';
                                return winner;
                            }
                        } catch (Exception e) {
                            test4 = false;
                        }
                    }
                    playerY = 0;
                    counter1 = 0;
                    counter2 = 0;
                    test1 = true;
                }
                finalCounter++;
            }
        }
        return winner;
    }

    /**
     * Checks whether the game is a draw
     *
     * @param board board that the method is going to check
     * @return returns false when there is no draw, otherwise returns true.
     */
    public static boolean checkDraw(String[][] board) {
        boolean draw = true;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (!board[x][y].equals("X") && !board[x][y].equals("O")) {
                    draw = false;
                    return draw;
                }
            }
        }
        return draw;
    }

    /**
     * Saves current player's status to a highscore file.
     * <p>
     * After a game is played, stores the information of the current player on Highscore.txt.
     * Doesn't do duplicate entries, instead if it finds a player with the same name already
     * on the list, it modifies that entry. Ordered by winning percentage.
     *
     * @param playerList   An ArrayList containing all data of players in the Highscore.txt
     * @param fileName     Contains the name of the highscore-file
     * @param notOverwrite should be false if user wants to overwrite the current file, when true
     *                     it appends all that it writes with the current data.
     */
    public static void saveToFile(ArrayList<Player> playerList, String fileName, boolean notOverwrite) {
        try (FileWriter out = new FileWriter(fileName + ".txt", notOverwrite)) {
            for (Player p : playerList) {
                out.write(p.toString() + System.lineSeparator());
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Error..");
        }
    }

    /**
     * Method used to save the current gamestate.
     *
     * @param board    board that we want to save.
     * @param fileName name of the file that we want to save the state as.
     */
    public static void saveGame(String[][] board, String fileName) {
        try (FileWriter out = new FileWriter(fileName + ".txt")) {
            for (int x = 0; x < board.length; x++) {
                out.write(System.lineSeparator());
                for (int y = 0; y < board.length; y++) {
                    out.write(board[x][y] + "::");
                }
            }
            out.write(System.lineSeparator());
            out.close();
        } catch (IOException e) {
            System.out.println("Error saving game");
        }
    }

    /**
     * Method used to determine sizes
     * <p>
     * This method determines what size the string[][]
     * in method loadGame() should be.
     *
     * @return the determined size as an integer.
     */
    public static int checkSize() {
        int count = 0;
        int count2 = 0;
        try (FileReader read = new FileReader("save.txt")) {
            int oneChar;
            String tempString = "";
            while ((oneChar = read.read()) != -1 && count2 < 2) {
                if (oneChar != '\r' && oneChar != '\n') {
                    tempString = tempString + (char) oneChar;
                } else if (oneChar == '\n') {
                    String[] split = tempString.split("::");
                    for (String s : split) {
                        count++;
                    }
                    tempString = "";
                    count2++;
                }
            }
            read.close();

        } catch (IOException e) {
            System.out.println("Error!");
        }

        return count - 1;


    }

    /**
     * Method used to load a saved gamestate from save.txt
     *
     * @return loaded board from savefile
     */
    public static String[][] loadGame() {
        int size = GameEngine.checkSize();
        String[][] board = new String[size][size];
        try (FileReader read = new FileReader("save.txt")) {
            int oneChar;
            int counter1 = 0;
            int counter2 = 0;
            int uniqueCount = 0;
            String tempString = "";

            while ((oneChar = read.read()) != -1) {
                if (oneChar != '\r' && oneChar != '\n') {
                    tempString = tempString + (char) oneChar;
                } else if (oneChar == '\n') {
                    String[] split = tempString.split("::");
                    for (String s : split) {
                        if (uniqueCount > 0) {
                            board[counter2][counter1] = s;
                            counter1++;
                        }
                    }
                    if (uniqueCount > 0) {
                        counter2++;
                    }
                    uniqueCount++;
                    counter1 = 0;

                    tempString = "";
                }
            }
            read.close();

        } catch (IOException e) {
            System.out.println("Error!");
        }
        return board;
    }

    /**
     * Used to read the current highscore list
     *
     * @param fileName name of the file where the highscore is located
     * @return returns an arraylist of player-objects with information on the different highscores.
     */
    public static ArrayList<Player> readFromFile(String fileName) {
        ArrayList<Player> p = new ArrayList<>();
        try (FileReader read = new FileReader(fileName + ".txt")) {
            int oneChar;
            String tempString = "";

            while ((oneChar = read.read()) != -1) {
                if (oneChar != '\r' && oneChar != '\n') {
                    tempString = tempString + (char) oneChar;
                } else if (oneChar == '\n') {
                    String[] split = tempString.split("::");
                    p.add(new Player(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
                    tempString = "";
                }
            }
            read.close();

        } catch (IOException e) {
            System.out.println("Error!");
        }

        return p;

    }
}
