package edu.up.cs371.schmidtj.football;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dr. James Schmidt
 *
 * This class, MainActivity.java, serves as the base for the FootBall app.
 * Adds teams with custom Win/Loss stats, ability to clear stats,  and
 * allows access to the TeamBoard activity.
 *
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    //init for buttons in the Main Activity
    public Button clearStat;
    public Button addAnotherTeam;
    public ImageButton clickToTeamRoster;

    //init for the text fields used in the Main Activity
    public EditText teamWins;
    public EditText teamLoses;
    public EditText teamName;

    //init for the lists that fill the spinners in Main Activity
    public ArrayList<String> listOfTeams;
    public ArrayList<String> listImageSelector;

    //init for the spinners used to hold this list of teams in Main Activity
    public Spinner teamSpinner;
    public Spinner imageTeamSelector;

    //init for the HashMap used to hold the values in the Team objects
    public HashMap<String,Team> Teams;

    /**
     * @name onCreate
     * @param savedInstanceState
     *
     * This method extends the onCreate method from the class ActionBarActivity.
     * This method runs on the creation of the Main Activity, declares all of the
     * EditTexts, images, buttons, Teams, spinners, and HashMaps used in Main
     * Activity and calls the listener to them.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring the buttons in Main Activity
        clearStat= (Button) findViewById(R.id.clearStat);
        addAnotherTeam= (Button) findViewById(R.id.createTeam);
        clickToTeamRoster= (ImageButton) findViewById(R.id.to_new_activity);

        //setting listeners for the buttons previously declared
        clearStat.setOnClickListener(this);
        addAnotherTeam.setOnClickListener(this);
        clickToTeamRoster.setOnClickListener(this);

        //declaring the EditTexts in Main Acitivity
        teamWins= (EditText) findViewById(R.id.teamWins);
        teamLoses= (EditText) findViewById(R.id.teamLoses);
        teamName= (EditText) findViewById(R.id.teamName);

        //declaring default team "Dragons"
        Team defualtTeam1 = new Team("Dragons",0,0);
        defualtTeam1.setImageID("blue_dragons");

        //declaring default team "Butterfly"
        Team defualtTeam2 = new Team("Butterfly",0,0);
        defualtTeam2.setImageID("orange_butterfly");

        //declaring new Hashmap "Teams" to hold default teams and any added teams
        Teams=new HashMap<String,Team>();

        //putting the default teams into the HashMap "Teams"
        Teams.put("Dragons",defualtTeam1);
        Teams.put("Butterfly",defualtTeam2);

        //declaring new ArrayList to hold the names of the teams held by the "Teams" HashMap
        listOfTeams = new ArrayList<String>();
        listOfTeams.add("Dragons");
        listOfTeams.add("Butterfly");

        //declaring new ArrayList to hold the image names of the team images
        listImageSelector = new ArrayList<String>();
        listImageSelector.add("orange_butterfly");
        listImageSelector.add("pink_butterfly");
        listImageSelector.add("green_cran");
        listImageSelector.add("blue_dragons");
        listImageSelector.add("green_dragons");
        listImageSelector.add("red_dragons");
        listImageSelector.add("orange_fish");
        listImageSelector.add("blue_pegasus");

        //implementing the spinner of team names and setting the adapter, applays listOfTeams
        teamSpinner = (Spinner) findViewById(R.id.teamSpinner);
        ArrayAdapter<String> teamSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listOfTeams);
        teamSpinner.setAdapter(teamSpinnerAdapter);

        //implementing the spinner of the images associated with the teams held in the "Teams" HashMap, applies listImageSelection
        imageTeamSelector = (Spinner) findViewById(R.id.imageTeamSelector);
        ArrayAdapter<String> imageTeamSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imageTeamSelector.setAdapter(imageTeamSelectorAdapter);

        //sets listeners for the spinners
        teamSpinner.setOnItemSelectedListener(this);
        imageTeamSelector.setOnItemSelectedListener(this);

        //sets default image/team displayed upon startup of the app
        int index = listImageSelector.indexOf(Teams.get("Dragons").getImageID());
        imageTeamSelector.setSelection( index );
    }

    /**
     * @name onClick
     * @param view: a click event
     *
     * This method is generated by implementing the interface View.OnClickListener, it takes
     * a "click" event and passes it's location as a parameter to this method, and the if
     * statements prepare for every meaningful click event and execute based on if their trigger
     * is clicked. The triggers for Main Activity include buttons and spinners.
     *
     */
    @Override
    public void onClick(View view) {

        //if clearStat button is pressed, erases the Team objects attributes to "", and sets to the teamSpinner index
        //to zero
        if(view == clearStat)
        {
            teamWins.setText("");
            teamLoses.setText("");
            teamName.setText("");
            teamSpinner.setSelection(0);
        }

        //if the image of the team is clicked, moves to that teams player list in the TeamBoard activity,
        //also pushes the currently selected team info to TeamBoard.java
        if(view == clickToTeamRoster)
        {
            Intent intent = new Intent(this, TeamBoard.class);
            intent.putExtra("aTeam", Teams.get( teamSpinner.getSelectedItem().toString() ) );
            //intent.putStringArrayListExtra("Player",listOfTeams);

            //intent.putExtra("hi", Teams);

            startActivityForResult(intent,100);
            //startActivity(intent);
        }

        //if the addAnotherTeam button is clicked, cycles through the statements given
        if(view == addAnotherTeam)
        {
            //ensures no editTexts were left empty before continuing
            if(String.valueOf(teamWins.getText()).isEmpty() || String.valueOf(teamLoses.getText()).isEmpty() || String.valueOf(teamName.getText()).isEmpty())
                return;

            //if there is not a team with the given name, create a temporary Team object to fill the with the user inputted data,
            //then adds it to the listOfTeams arraylist and Teams HashMap
            if( listOfTeams.indexOf( String.valueOf(teamName.getText()) ) == -1 )
            {
                Team pTemp = new Team(String.valueOf(teamName.getText()), Integer.parseInt(String.valueOf(teamWins.getText())), Integer.parseInt(String.valueOf(teamLoses.getText())));
                pTemp.setImageID( imageTeamSelector.getSelectedItem().toString() );

                Teams.put(String.valueOf(teamName.getText()), pTemp);

                listOfTeams.add(String.valueOf(teamName.getText()));
            }
            //if there is already a team with the given name, update the values
            else
            {
                Teams.get(String.valueOf(teamName.getText())).setLoses(String.valueOf(teamLoses.getText()));
                Teams.get(String.valueOf(teamName.getText())).setWins(String.valueOf(teamWins.getText()));
                Teams.get(String.valueOf(teamName.getText())).setImageID( imageTeamSelector.getSelectedItem().toString() );
            }

            //update the spinner to match the new teams list
            teamSpinner.setSelection( listOfTeams.indexOf( String.valueOf(teamName.getText()) ));
        }

    }

    /**
     * @name onActivityResult
     * @param requestCode
     * @param resultCode
     * @param data
     *
     * This method executes at the end of the activity, receiving any changes to the Teams Hashmap from
     * intents sent to Main Activity
     *
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100)
        {
            if (resultCode == 1)
            {
                Team tTemp = (Team)  data.getSerializableExtra("returnATeam");
                Teams.put( tTemp.getTeamFullName().toString(), tTemp);
            }
        }
    }

    /**
     * @name onItemSelected
     * @param adapterView
     * @param view
     * @param i
     * @param l
     *
     * This method is for the spinners in Main Activity to match the information posted with the team
     * selected.
     *
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //if teamSpinner is selected, set the text values of Wins, Losses, and Name to correspond with
        //the given Team stored in the Teams HashMap
        if(adapterView == teamSpinner)
        {
            teamLoses.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getLoses()));
            teamName.setText(teamSpinner.getSelectedItem().toString());
            teamWins.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getWin()));

            int index = listImageSelector.indexOf(Teams.get(teamSpinner.getSelectedItem().toString()).getImageID());
            imageTeamSelector.setSelection( index );
        }
        //if imageTeamSelector is selected, image for the team is updated
        if(adapterView == imageTeamSelector)
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + imageTeamSelector.getSelectedItem().toString(), null, null);
            clickToTeamRoster.setImageResource(id);
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
} //end MainActivity.java
