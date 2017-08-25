package com.company;

import java.io.*;
import java.util.ArrayList;

import static com.company.GameEngine.readFromFile;

/**
 * @author Gonzalo Ortiz <gonzalo.ortizbianco@cs.tamk.fi>
 * @version 2016.1220
 * @since 1.8
 */

public class Main {

    public static void main(String[] args) {
        int choice = 0;
        System.out.println("*********************************");
        System.out.println("*    Welcome to Tic-Tac-Toe!    *");
        System.out.println("*    1.   Play the game         *");
        System.out.println("*    2. Check the highscore     *");
        System.out.println("*    3.        Quit             *");
        System.out.println("*********************************");
        System.out.println("Please select one of the options (1-3): ");
        boolean choices = true;
        while (choices) {
            choice = Helpers.inputInt();
            if (choice > 3 || choice < 1) {
                System.out.println("Invalid input! Choose one of the three options.");
            } else {
                choices = false;
            }
        }
        switch (choice) {

            // This is the case we go to if the user decides to play.
            case 1: {

                //This is where the actual game begins
                boolean game = true;
                char winner = ' ';
                GameBoard board = new GameBoard();
                Player player1 = new Player(false, 0, 'X');
                Player bot = new Player(true, 0, 'O');
                GameEngine.populateBoard(board);
                GameEngine.showBoard(board.getBoard());
                while (game) {
                    board.setBoard(GameEngine.playTurn(player1.isBot(), player1.getSymbol(), board.getBoard()));
                    if (GameEngine.checkWin(board.getBoard(), board.getToWin()) == 'X') {
                        winner = 'X';
                        game = false;
                        break;
                    } else if (GameEngine.checkDraw(board.getBoard())) {
                        game = false;
                        break;
                    }

                    board.setBoard(GameEngine.playTurn(bot.isBot(), bot.getSymbol(), board.getBoard()));
                    GameEngine.showBoard(board.getBoard());

                    if (GameEngine.checkWin(board.getBoard(), board.getToWin()) == 'O') {
                        winner = 'O';
                        game = false;
                        break;
                    } else if (GameEngine.checkDraw(board.getBoard())) {
                        game = false;
                        break;
                    }
                }


                //This marks the beginning of our highscore-checks.
                System.out.println("Game Over!!");
                GameEngine.showBoard(board.getBoard());
                boolean tempTest = true;
                if (winner == 'X') {
                    System.out.println(player1.getName() + " wins the game!");
                    player1.setGames(1);
                    player1.setScore(1);
                    ArrayList<Player> list = new ArrayList<>(readFromFile("Highscore"));
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getName().equals(player1.getName())) {
                            Player temp = new Player(list.get(i).getName(), list.get(i).getGames(), list.get(i).getScore());
                            temp.setGames(1);
                            temp.setScore(1);
                            list.set(i, temp);
                            tempTest = false;

                            int highestScore = 0;
                            int counter = list.size();
                            int counter2 = 0;
                            ArrayList<Player> temp2 = new ArrayList<>();
                            for (int h = 0; h < counter; h++) {
                                for (int n = 0; n < list.size(); n++) {
                                    if ((((double) list.get(n).getScore() / (double) list.get(n).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                        highestScore = n;
                                    }
                                }

                                temp2.add(h, list.get(highestScore));
                                list.remove(highestScore);
                                counter2++;
                                highestScore = 0;
                            }
                            GameEngine.saveToFile(temp2, "Highscore", false);
                        }
                    }
                    if (tempTest) {
                        list.add(player1);
                        int highestScore = 0;
                        int counter = list.size();
                        int counter2 = 0;
                        ArrayList<Player> temp = new ArrayList<>();
                        for (int h = 0; h < counter; h++) {
                            for (int i = 0; i < list.size(); i++) {
                                if ((((double) list.get(i).getScore() / (double) list.get(i).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                    highestScore = i;
                                }
                            }

                            temp.add(h, list.get(highestScore));
                            list.remove(highestScore);
                            counter2++;
                            highestScore = 0;
                        }

                        GameEngine.saveToFile(temp, "Highscore", false);
                    }
                    Helpers.inputString();
                    break;
                } else if (winner == 'O') {
                    System.out.println("the bot wins the game!");
                    System.out.println("input any key to exit..");
                    player1.setGames(1);
                    ArrayList<Player> list = new ArrayList<>(readFromFile("Highscore"));
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getName().equals(player1.getName())) {
                            Player temp = new Player(list.get(i).getName(), list.get(i).getGames(), list.get(i).getScore());
                            temp.setGames(1);
                            list.set(i, temp);
                            tempTest = false;

                            int highestScore = 0;
                            int counter = list.size();
                            int counter2 = 0;
                            ArrayList<Player> temp2 = new ArrayList<>();
                            for (int h = 0; h < counter; h++) {
                                for (int n = 0; n < list.size(); n++) {
                                    if ((((double) list.get(n).getScore() / (double) list.get(n).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                        highestScore = n;
                                    }
                                }

                                temp2.add(h, list.get(highestScore));
                                list.remove(highestScore);
                                counter2++;
                                highestScore = 0;
                            }


                            GameEngine.saveToFile(temp2, "Highscore", false);


                        }
                    }
                    if (tempTest) {
                        list.add(player1);
                        int highestScore = 0;
                        int counter = list.size();
                        int counter2 = 0;
                        ArrayList<Player> temp = new ArrayList<>();
                        for (int h = 0; h < counter; h++) {
                            for (int i = 0; i < list.size(); i++) {
                                if ((((double) list.get(i).getScore() / (double) list.get(i).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                    highestScore = i;
                                }
                            }

                            temp.add(h, list.get(highestScore));
                            list.remove(highestScore);
                            counter2++;
                            highestScore = 0;
                        }

                        GameEngine.saveToFile(temp, "Highscore", false);
                    }
                    Helpers.inputString();
                    break;
                } else {
                    System.out.println("The game is a draw!");
                    System.out.println("input any key to exit..");
                    player1.setGames(1);
                    ArrayList<Player> list = new ArrayList<>(readFromFile("Highscore"));
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getName().equals(player1.getName())) {
                            Player temp = new Player(list.get(i).getName(), list.get(i).getGames(), list.get(i).getScore());
                            temp.setGames(1);
                            list.set(i, temp);
                            tempTest = false;

                            int highestScore = 0;
                            int counter = list.size();
                            int counter2 = 0;
                            ArrayList<Player> temp2 = new ArrayList<>();
                            for (int h = 0; h < counter; h++) {
                                for (int n = 0; n < list.size(); n++) {
                                    if ((((double) list.get(n).getScore() / (double) list.get(n).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                        highestScore = n;
                                    }
                                }

                                temp2.add(h, list.get(highestScore));
                                list.remove(highestScore);
                                counter2++;
                                highestScore = 0;
                            }


                            GameEngine.saveToFile(temp2, "Highscore", false);


                        }
                    }
                    if (tempTest) {
                        list.add(player1);
                        int highestScore = 0;
                        int counter = list.size();
                        int counter2 = 0;
                        ArrayList<Player> temp = new ArrayList<>();
                        for (int h = 0; h < counter; h++) {
                            for (int i = 0; i < list.size(); i++) {
                                if ((((double) list.get(i).getScore() / (double) list.get(i).getGames()) > (double) list.get(highestScore).getScore() / (double) list.get(highestScore).getGames()) && !list.isEmpty()) {
                                    highestScore = i;
                                }
                            }

                            temp.add(h, list.get(highestScore));
                            list.remove(highestScore);
                            counter2++;
                            highestScore = 0;
                        }

                        GameEngine.saveToFile(temp, "Highscore", false);
                    }
                    Helpers.inputString();
                    break;
                }
            }
            case 2: {
                ArrayList<Player> list = new ArrayList<>(readFromFile("Highscore"));
                System.out.println("Highscores:");
                for (Player p : list) {
                    System.out.println(p.highScore());
                }
                Helpers.inputString();

            }
            case 3: {
                System.exit(0);
            }
        }

    }
}