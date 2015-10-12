package edu.up.cs371.schmidtj.football;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by schmidtj on 9/23/2015.
 *
 * Team class creates a Team object to hold attributes for teams and hold a list of players
 *
 */

public class Team implements Serializable{

    //string to hold teams name
    private String teamsName;
    //int to hold teams wins
    private int gamesWins;
    //int to hold team games played
    private int gamesPlayed;
    //int to hold team losses
    private int gamesLost;
    //string to hold teams image id
    private String imageID;
    //array list of strings to hold the player names on the team
    public ArrayList<String> playerList;
    //HashMap of players associated with the team to access player attributes
    private HashMap<String,Player> teamRoster;

    /**
     * @name Team
     * @param name: team name string attribute
     * @param games_Wins: team wins int attribute
     * @param games_Lost: team losses int attribute
     *
     * Team object constructor using a string and two ints as params, but two strings and three
     * ints as attributes.
     *
     */
    protected Team(String name, int games_Wins, int games_Lost)
    {
        //set the teams name
        this.teamsName=name;

        //if games_Wins is currently less than zero, set it to zero to avoid errors
        if(games_Wins < 0)
            games_Wins = 0;
        this.gamesWins=games_Wins;

        //if games_Lost is currently less than zero, set it to zero to avoid errors
        if(games_Lost < 0)
            games_Lost = 0;
        this.gamesLost=games_Lost;

        //gamesPlayed definition
        this.gamesPlayed = this.gamesLost+this.gamesWins;

        //default team image id
        this.setImageID("green_cran");

        //new player list and HashMap declaration
        playerList = new ArrayList<String>();
        teamRoster = new HashMap<String,Player>();

        //set a default player
        Player teamManager = new Player("TheBigFish",0,0);
        teamManager.setImageID("orange_fish");

        //add default player to player list
        this.addAPlayer(teamManager);
    }

    /**
     * @name addAPlayer
     * @param instPlayer: player object to add a player to the existing lists/HashMaps
     * @return int
     *
     * An adder method that takes an instance of a player object and saves the values in that instance,
     * and puts them into a player object in the lists and HashMap associated a certain team.
     * Success returns 1, failure 0.
     *
     */
    public int addAPlayer(Player instPlayer)
    {
        try
        {
            //puts the player into the hashmap and arraylist
            teamRoster.put(instPlayer.getPlayersFullName(), instPlayer);
            if( playerList.indexOf(instPlayer.getPlayersFullName()) == -1)
                playerList.add(instPlayer.getPlayersFullName());
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getPlayer
     * @param playerName: name of player string attribute
     * @return Player object
     *
     * Getter method that returns a Player object in a team based on an input of the players name.
     *
     */
    public Player getPlayer(String playerName)
    {
        return teamRoster.get(playerName);
    }

    /**
     * @name setImageID
     * @param imageName: image id name string attribute
     * @return int
     *
     * Setter method to set the imageID for a Team object based on a try-finally clause. Success returns
     * 1, failure 0.
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
     * @return String
     *
     * Getter method that returns a Team objects image id (string).
     *
     */
    public String getImageID()
    {
        return this.imageID;
    }

    /**
     * @name updateWin
     *
     * Method that adds a win to a Team objects Wins stat. Returns void and calls updatePlayed().
     *
     */
    public void updateWin()
    {
        this.gamesWins++;
        this.updatePlayed();
    }

    /**
     * @name getWin
     * @return int
     *
     * Getter method that returns a Team objects wins stat.
     *
     */
    public int getWin()
    {
        return this.gamesWins;
    }

    /**
     * @name updatePlayed
     *
     * Method that updated the number of games played stat upon calling. No params and returns void.
     *
     */
    private void updatePlayed()
    {
        this.gamesPlayed++;
    }

    /**
     * @name setWins
     * @param games_Wins: number of wins (int) desired to be set
     * @return int
     *
     * Setter method to set a Team objects wins stat to the desired amount passed in through the param.
     * Returns 1 on success, 0 on failure.
     *
     */
    public int setWins(int games_Wins)
    {
        try
        {
            this.gamesWins=games_Wins;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name setWins
     * @param games_Wins: number of wins (string) desired to be set
     * @return int
     *
     * Same as the above setter method but parses and string to an int in the case the user passes in
     * a string. Uses try-finally in case the parsing fails, returns 1 on success, 0 on failure.
     *
     */
    public int setWins(String games_Wins)
    {
        try
        {
            this.gamesWins=Integer.parseInt(games_Wins);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getPlayed
     * @return int
     *
     * Getter method that returns a Team object attribute games played.
     *
     */
    public int getPlayed()
    {
        return this.gamesPlayed;
    }

    /**
     * @name updateLoses
     *
     * Method that adds one to the gamesLost stat in a Team object and calls updatePlayed(). Returns
     * void and no params.
     *
     */
    public void updateLoses()
    {
        this.gamesLost++;
        this.updatePlayed();
    }

    /**
     * @name getLoses
     * @return int
     *
     * Getter method that returns a Team objects gamesLost attribute.
     *
     */
    public int getLoses()
    {
        return this.gamesLost;
    }

    /**
     * @name setLoses
     * @param games_lost: (int) number of games lost desired to be set
     * @return int
     *
     * Setter method that uses try-finally (in case games_lost is negative) to set the Team object
     * attribute gamesLost. Returns 1 on success, 0 on failure.
     *
     */
    public int setLoses(int games_lost)
    {
        try
        {
            this.gamesLost=games_lost;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name setLoses
     * @param games_lost: (string) number of games lost desired to be set
     * @return int
     *
     * Same as the above setter method but is for the case that the user inputs a string for games lost.
     * Trys to parse to an int, returns 1 on success, 0 on failure.
     *
     */
    public int setLoses(String games_lost)
    {
        try
        {
            this.gamesLost=Integer.parseInt(games_lost);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name setTeamFullName
     * @param Name: string for a Team objects teamsName attribute
     * @return int
     *
     * Setter method that trys to set a Team objects teamsName, returns 1 on success, 0 on failure.
     *
     */
    public int setTeamFullName(String Name)
    {
        try
        {
            this.teamsName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * @name getTeamFullName
     * @return string
     *
     * Getter method that returns a Team objects teamsName.
     *
     */
    public String getTeamFullName()
    {
        return this.teamsName;
    }

    /**
     * @name updatePlayers
     * @param playerName: string corresponding to a Player objects name attribute
     * @param Assists: int corresponding to a Player objects assists attribute
     * @param Goals: int corresponding to a Player objects goals attribute0
     * @return int
     *
     * Method that updates a Player object (that is on the team), calling the setAssists and setGoals methods.
     * Returns 1 on successful player update, 0 on a player name that doesn't exist (get(playerName) returning
     * null).
     *
     * ----****BUG: SHOULD BE setGoals(Goals) FOR THE STATMENT WITH THE BUG COMMENT FOLLOWING IT****----
     *
     */
    public int updatePlayers(String playerName, int Assists, int Goals)
    {
        if( teamRoster.get(playerName) == null)
            return 0;

        teamRoster.get(playerName).setAssists(Assists);
        teamRoster.get(playerName).setAssists(Goals); //----****BUG PRESENT****----

        return 1;
    }
} //end Team.java
