package com.company;

/**
 * @author Gonzalo Ortiz <gonzalo.ortizbianco@cs.tamk.fi>
 * @version 2016.1220
 * @since 1.8
 */
public class Player {

    /**
     * Used to determine if player is a bot
     */
    boolean bot;
    /**
     * Stores the current score of the player
     */
    private int score;
    /**
     * Stores which symbol the current player is using.
     */
    private char symbol;
    /**
     * If a player, stores the name of player.
     */
    private String name;
    /**
     * Stores the ammount of games player.
     * <p>
     * This attribute is used by the game engine more than anything, when reading and rewriting highscores.
     */
    private int games;
    /**
     * This holds the percentage of wins of the current player.
     * <p>
     * This is mostly used by the game engine, when reading and rewriting highscores.
     */
    double percentage;

    /**
     * Method used to get ammount of games
     *
     * @return the ammount of games the object has played.
     */
    public int getGames() {
        return games;
    }

    /**
     * Method used to set player score
     *
     * @param score the ammount of score we want to set.
     */

    public void setScore(int score) {
        this.score = this.score + score;
    }

    /**
     * Sets the ammount of games the current player has played.
     *
     * @param games the ammount of games to be set.
     */
    public void setGames(int games) {
        this.games = this.games + games;
    }

    /**
     * Displays information in highscore format
     *
     * @return a String formatted as highscore information
     */
    public String highScore() {
        percentage = (double) score / (double) games;
        return (name + "::" + (percentage * 100) + "%" + "::" + "(" + score + "/" + games + ")");
    }

    /**
     * Displays information in String format
     * <p>
     * Used by the game engine when dealing with Highscores.
     *
     * @return String of player-related information.
     */
    public String toString() {
        return (name + "::" + games + "::" + score);
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Determined whether player is a bot
     *
     * @return true if player is a bot, otherwise false.
     */
    public boolean isBot() {
        return bot;
    }

    /**
     * Gets the current score
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * gets the current symbol of the player
     *
     * @return the symbol currently used.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Overload constructor used for highscores
     *
     * @param name  the current name of the player
     * @param games the ammount of games player has played
     * @param score the ammount of victories player has had
     */
    public Player(String name, int games, int score) {
        this.name = name;
        this.games = games;
        this.score = score;
    }

    /**
     * Constructor used to create new players and bots
     *
     * @param bot    true if the player is a bot, false otherwise.
     * @param score  starting score of the player.
     * @param symbol symbol used by the player.
     */
    public Player(boolean bot, int score, char symbol) {
        String playerName;
        if (bot == false) {
            System.out.println("What is your name?");
            playerName = Helpers.inputString();
        } else {
            playerName = "Bot";
        }

        name = playerName;
        this.bot = bot;
        this.score = score;
        this.symbol = symbol;
    }
}