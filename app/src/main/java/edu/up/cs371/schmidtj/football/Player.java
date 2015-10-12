package edu.up.cs371.schmidtj.football;

import java.io.Serializable;

/**
 * Created by schmidtj on 9/23/2015.
 *
 * Player class to create Player objects to hold attributes shown in TeamBoard for players in teams.
 *
 */

public class Player implements Serializable{

    //string to hold players name
    private String playersFullName;
    //int to hold players goals stat
    private int Goals;
    //int to hold players assists stat
    private int Assists;
    //string to hold the id for the player image
    private String imageID;

    /**
     * @name Player
     * @param name: player name attribute
     * @param GoalsStat: player goals scored attribute
     * @param AssistsStat: player assists made attribute
     *
     * This method is a constructor for the object Player, who's attributes include a string and
     * two ints, and the image id is set to a default value.
     *
     */
    protected Player(String name, int GoalsStat, int AssistsStat)
    {
        this.playersFullName=name;
        this.Goals=GoalsStat;
        this.Assists=AssistsStat;

        this.setImageID("green_cran");
    }

    /**
     * @name setImageID
     * @param imageName
     * @return int
     *
     * This method utilizes a try-finally clause, so even if try doesn't exit in a given return statement,
     * the finally clause will always execute.  In this case, imageID, an attribute of Player is attempted to be updated.
     * If it succeeds, it returns 1, if it fails, the finally clause returns 0 (with 0 and 1 indicating true or false.
     *
     */
    public int setImageID(String imageName)
    {
        try
        {
            this.imageID= imageName;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getImageID
     * @return string
     *
     * This method is a basic getter method for the Player object to return an image ID string.
     *
     */
    public String getImageID()
    {
        return this.imageID;
    }

    /**
     * @name setGoals
     * @param GoalsStat: (INT) goals to add to the current goals of the selected Player object
     * @return int
     *
     * This method is a basic setter method to set the goals scored for a Player object.
     * Uses a try-finally clause to try to add the param's int to the current number of
     * goals the Player object possesses. Returning 1 denotes success, returning 0 denotes
     * failure.
     *
     */
    public int setGoals(int GoalsStat)
    {
        try
        {
            this.Goals+=GoalsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name setGoals
     * @param GoalsStat: (STRING) goals to add to the current goals of the selected Player object
     * @return int
     *
     * Same as the previous setGoals, but instead takes a string in case the user inputs a
     * string and tries to parse it as an int. Success is denoted by returning a 1, and failure
     * by a 0.
     *
     */
    public int setGoals(String GoalsStat)
    {
        try
        {
            this.Goals+=Integer.parseInt( GoalsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getGoals
     * @return int
     *
     * Basic getter method for a Player objects goals attribute, returns the number of goals
     * stored in the specified Player object.
     *
     */
    public int getGoals()
    {
        return this.Goals;
    }

    /**
     * @name setAssists
     * @param AssistsStat: (INT) assists to add to the current assists attribute of selected Player object
     * @return int
     *
     * Basic setter method using a try-finally clause. Adds AssistsStat param to current assists attribute
     * of the Player object, success returns 1, failure 0.
     *
     */
    public int setAssists(int AssistsStat)
    {
        try
        {
            this.Assists+=AssistsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name setAssists
     * @param AssistsStat: (String) assists to add to the current assists attribute of selected Player object
     * @return int
     *
     * Same as the above AssistsStat method, but instead takes a string as a param and trys to parse it as
     * an int. Success returns 1, failure 0.
     *
     */
    public int setAssists(String AssistsStat)
    {
        try
        {
            this.Assists+=Integer.parseInt( AssistsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getAssistsStat
     * @return int
     *
     * Basic getter method that returns the assists attribute of a specified Player object.
     *
     */
    public int getAssistsStat()
    {
        return this.Assists;
    }

    /**
     * @name setPlayersFullName
     * @param Name: name attribute to give to the specified player object
     * @return int
     *
     * Basic setter method that uses try-finally to try to set the playersFullName attribute,
     * success returns 1, failure 0.
     *
     */
    public int setPlayersFullName(String Name)
    {
        try
        {
            this.playersFullName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getPlayersFullName
     * @return string
     *
     * Basic getter method that returns the specified Player objects playersFullName attribure.
     *
     */
    public String getPlayersFullName()
    {
        return this.playersFullName;
    }
} //end Player.java
