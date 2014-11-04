package com.gif.ryan.improvtoolbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is the fragment that handles the functionality of the Suggestionator
 * A text view is updated with a suggestion that is returned by a button tap.
 * This fragment uses the Helper.java class to get random suggestions.
 *
 * @ryancphil
 */
public class Suggestionator extends Fragment {

    /*******************************************
     *              VARIABLES
     ********************************************/
    //Tag for the section number when a new instance of this fragment is replaced
    private static final String ARG_SECTION_NUMBER = "section_number";

    //Result string that will be used to update the TextView
    private String sugg = "";

    //Create Helper object to get access to data and methods for suggestions
    private Helper helper = new Helper();

    /*******************************************
     *              CONSTRUCTOR
     ********************************************/
    public Suggestionator() {
    }

    /*******************************************
     *              METHODS
     ********************************************/
    //Returns a new instance of this fragment for the given section number.
    public static Suggestionator newInstance(int sectionNumber) {
        Suggestionator fragment = new Suggestionator();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_suggestionator, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create OBJECT button and get a random object when pressed
        Button object = (Button) getView().findViewById(R.id.object);
        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.object();
                update(sugg);
            }
        });

        //Create RELATIONSHIP button and get a random relationship when pressed
        Button relationship = (Button) getView().findViewById(R.id.relationship);
        relationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.relationship();
                update(sugg);
            }
        });

        //Create LOCATION button and get a random location when pressed
        Button location = (Button) getView().findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.location();
                update(sugg);
            }
        });

        //Create OCCUPATION button and get a random occupation when pressed
        Button occupation = (Button) getView().findViewById(R.id.occupation);
        occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.occupation();
                update(sugg);
            }
        });

        //Create EVENT button and get a random event when pressed
        Button event = (Button) getView().findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.event();
                update(sugg);
            }
        });

        //Create GENRE button and get a random genre when pressed
        Button genre = (Button) getView().findViewById(R.id.genre);
        genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.genre();
                update(sugg);
            }
        });

        //Create PERSON button and get a random person when pressed
        Button person = (Button) getView().findViewById(R.id.person);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.person();
                update(sugg);
            }
        });

        //Create EMOTION button and get a random emotion when pressed
        Button emotion = (Button) getView().findViewById(R.id.emotion);
        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.emotion();
                update(sugg);
            }
        });

        //Create ODDBALL button and get a random oddball when pressed
        Button oddball = (Button) getView().findViewById(R.id.oddball);
        oddball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugg = helper.oddball();
                update(sugg);
            }
        });
    } //END OF onActivityCreated() METHOD

    //This method updates the TextView with a suggestion triggered by a buttonClick event
    public void update(String suggestion){
        //Get the text view
        TextView textView = (TextView)getView().findViewById(R.id.textView);

        //Update the text view
        textView.setText(suggestion);
    }
}