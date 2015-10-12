package edu.up.cs371.schmidtj.football;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Dr. James Schmidt
 *
 * Class TeamBoard.java is the second activity in the Football app. Implements the addition of players
 * to teams and updating player stats. Also can scroll through multiple teams to update multiple teams
 * rosters.
 *
 */

public class TeamBoard extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    //intent init for passing/sending extras/info between activities
    Intent intent;
    //player image init
    ImageView playerImage;

    //edit text inits
    public EditText playerGoals;
    public EditText playerAssists;
    public EditText playerName;

    //add player button init
    public Button addAnotherPlayer;

    //Team object init
    public Team theTeam;

    //spinner inits
    public Spinner playerSpinner;
    public Spinner imagePlayerSelector;

    //arraylist init for image selection
    public ArrayList<String> listImageSelector;

    /**
     * @name onCreate
     * @param savedInstanceState
     *
     * This method extends the onCreate method from the class ActionBarActivity.
     * This method runs on the creation of the TeamBoard Activity, declares all of the
     * EditTexts, images, buttons, Teams, spinners, and HashMaps used in the TeamBoard
     * Activity and calls the listener to them.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_board);

        //add player button declaration and listener set
        addAnotherPlayer= (Button) findViewById(R.id.createPlayer);
        addAnotherPlayer.setOnClickListener(this);

        //editText declarations
        playerGoals= (EditText) findViewById(R.id.playerGoals);
        playerAssists= (EditText) findViewById(R.id.playerAssists);
        playerName= (EditText) findViewById(R.id.playerName);

        //get intent to recieve the extras from Main Activity
        intent = getIntent();

        //player image declaration
        playerImage = (ImageView) findViewById( R.id.playerImage );

        //Team object being received from Main Activity
        theTeam = (Team)  intent.getSerializableExtra("aTeam");

        //Player object being received from Main Activity
        playerGoals.setText( theTeam.getPlayer("TheBigFish").getPlayersFullName() );
        playerAssists.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getGoals()) );
        playerName.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getAssistsStat()) );

        //id declaration for the Player objects image
        int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + theTeam.getPlayer("TheBigFish").getImageID(), null, null);
        playerImage.setImageResource(id);

        //default image delcarations stored in listImageSelector
        listImageSelector = new ArrayList<String>();
        listImageSelector.add("orange_butterfly");
        listImageSelector.add("pink_butterfly");
        listImageSelector.add("green_cran");
        listImageSelector.add("blue_dragons");
        listImageSelector.add("green_dragons");
        listImageSelector.add("red_dragons");
        listImageSelector.add("orange_fish");
        listImageSelector.add("blue_pegasus");

        //playerSpinner declaration and adapter implementation
        playerSpinner = (Spinner) findViewById(R.id.playerSpinner);
        ArrayAdapter<String> playerSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, theTeam.playerList);
        playerSpinner.setAdapter(playerSpinnerAdapter);

        //imagePlayerSelector declaration and adapter implementation
        imagePlayerSelector = (Spinner) findViewById(R.id.imagePlayerSelector);
        ArrayAdapter<String> imagePlayerSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imagePlayerSelector.setAdapter(imagePlayerSelectorAdapter);

        //listener set for both spinners
        playerSpinner.setOnItemSelectedListener(this);
        imagePlayerSelector.setOnItemSelectedListener(this);
    }

    /**
     * @name return_back_click
     * @param view: event passed into the method
     *
     * Returns back to the Main Activity, sending the updated Team object
     *
     */
    public void return_back_click(View view)
    {
        intent.putExtra("returnATeam",theTeam);
        setResult(1, intent);
        finish();
    }

    /**
     * @name onClick
     * @param view: event passed into the method
     *
     * This method is generated by implementing the interface View.OnClickListener, it takes
     * a "click" event and passes it's location as a parameter to this method, and the if
     * statements prepare for every meaningful click event and execute based on if their trigger
     * is clicked. The triggers for TeamBoard Activity include buttons and spinners.
     *
     */
    @Override
    public void onClick(View view) {

        //if the addAnotherPlayer button is clicked
        if(view == addAnotherPlayer)
        {

            //paranoid (good thing) case for if the user leaves a field empty
            if(String.valueOf(playerGoals.getText()).isEmpty() || String.valueOf(playerAssists.getText()).isEmpty() || String.valueOf(playerName.getText()).isEmpty())
                return;

            //if there is not a player with the given name on the team, create a temporary Player object
            // to fill the with the user inputted data, then adds it to the imagePlayerSelector arraylist and
            //adds the player to the team
            if(theTeam.playerList.indexOf(String.valueOf(playerName.getText())) == -1 )
            {
                Player pTemp = new Player(String.valueOf(playerName.getText()), Integer.parseInt(String.valueOf(playerGoals.getText())), Integer.parseInt(String.valueOf(playerAssists.getText())));
                pTemp.setImageID( imagePlayerSelector.getSelectedItem().toString() );

                theTeam.addAPlayer(pTemp);

            }
            //if there is already a player with that name on the team, update the attributes
            else
            {
                theTeam.getPlayer(String.valueOf(playerName.getText())).setGoals( Integer.parseInt(String.valueOf(playerGoals.getText())) );
                theTeam.getPlayer(String.valueOf(playerName.getText())).setAssists(Integer.parseInt(String.valueOf(playerAssists.getText())));
                theTeam.getPlayer(String.valueOf(playerName.getText())).setImageID(imagePlayerSelector.getSelectedItem().toString());
            }

            //update the spinner to match the new player list on the team
            playerSpinner.setSelection( theTeam.playerList.indexOf(String.valueOf(playerName.getText())) );
        }
    }

    /**
     * @name onItemSelected
     * @param adapterView
     * @param view
     * @param i
     * @param l
     *
     * This method is for the spinners in TeamBoard Activity to match the information posted with the team
     * selected.
     *
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //if playerSpinner is selected, update the editTexts to display the player attributes
        if(adapterView == playerSpinner)
        {
            playerGoals.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getGoals()));
            playerName.setText(playerSpinner.getSelectedItem().toString());
            playerAssists.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getAssistsStat()));

            int index = listImageSelector.indexOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID());
            imagePlayerSelector.setSelection( index );

            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID(), null, null);
            playerImage.setImageResource(id);
        }
        //if the imagePlayerSelector is selected, update the image to reflect the selected value
        if(adapterView == imagePlayerSelector)
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  imagePlayerSelector.getSelectedItem().toString(), null, null);
            playerImage.setImageResource(id);

        }
    }

    /**
     * @name onNothingSelected
     * @param adapterView: passes in an adapter
     *
     * This method passes in an adapter, and if nothing is selected executes the statements given.
     * Since there are default values filling the spinners, this method won't be needed, thus no statements
     * are made.
     *
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
} //end TeamBoard.java
