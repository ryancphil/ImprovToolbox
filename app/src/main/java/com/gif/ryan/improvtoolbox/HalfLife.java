package com.gif.ryan.improvtoolbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This fragment displays the Half Life timer.
 *
 * @ryancphil
 */
public class HalfLife extends Fragment {
    /*******************************************
     *              VARIABLES
     ********************************************/
    //Tag for the section number when a new instance of this fragment is replaced
    private static final String ARG_SECTION_NUMBER = "section_number";

    //Variables used for the timer methods
    private Timer timer;
    private long secs = 600;  // Seconds to set the Countdown from
    int i = 0;
    int[] roundCount = {600, 300, 150, 75, 30, 15};
    private boolean isRunning = false;

    /*******************************************
     *              CONSTRUCTOR
     ********************************************/
    public HalfLife() {
    }

    /*******************************************
     *              METHODS
     ********************************************/
    //Returns a new instance of this fragment for the given section number.
    public static HalfLife newInstance(int sectionNumber) {
        HalfLife fragment = new HalfLife();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_halflife, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create start button and start timer if pressed
        Button start = (Button) getView().findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountDown();
            }
        });

        //Create half button and half the value of the current time
        Button half = (Button) getView().findViewById(R.id.half);
        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHalf();
            }
        });

        //Create reset button and reset timer to 60 seconds when pressed
        Button reset = (Button) getView().findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        ImageButton help = (ImageButton) getView().findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an instance of the Help fragment and then show the
                //Half Life help dialogue when the question mark button is clicked
                DialogFragment help = new Help();
                help.show(getActivity().getFragmentManager(), "help");
            }
        });

    }

    //This dialogue fragment is used to display the hint for how to play Half Life
    public static class Help extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            // Single text dialogue with one button that closes the box
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.howto_halflife)
                    .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    //Method called when START button is pressed
    public void startCountDown() {
        //Do nothing if already running
        if (isRunning) {
            return;
        }

        //set isRunning to true
        isRunning = true;

        //Create the timer that is to repeat every 1/10 second
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (secs <= 0) {
                    //When timer reaches 0 seconds, cancel the timer object
                    timer.cancel();

                    //timer equals 0 AND it's on the last round
                    if (i == 5) {
                        resetTimer();
                        //Toast only on the very last round, must be run on the UI thread
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Method with toast in it
                                takeItDown();
                            }
                        });
                    } else {//Timer equals 0 but NOT last round
                        //Increment round counter
                        i += 1;
                        //Update the number of seconds to reflect current round
                        secs = roundCount[i];
                        //Set isRunning to false
                        isRunning = false;
                        //UPDATE DISPLAY, must be run on the UI thread
                        updateTimer();
                    }
                    return;
                }
                //Timer is greater than 0 so decrement secs
                secs--;

                //update display on the UI thread
                updateTimer();

            }//end of timerTask run() method
        }, 0, 100); //end of timerTask schedule

        // 0 is the time in second from when this code is to be executed
        // 100 is time in millisecond after which it has to repeat
    }

    //helper method to display toast when last round ends
    public void takeItDown(){
        Toast.makeText(this.getActivity(), "Take it down!",
                Toast.LENGTH_SHORT).show();
    }

    //Method executed when HALF button is pressed
    public void onHalf() {
        //If the timer is running
        if (isRunning) {
            //cancel the timer
            timer.cancel();
        }

        //Update the round counter
        if(i == 5){
            //If last round, reset counter i to 0
            i = 0;
        }else{
            //else increment round counter i
            i++;
        }

        //Update secs to reflect current round value
        secs = roundCount[i];

        //Set isRunning to false
        isRunning = false;

        updateTimer();
    }

    //Method called when RESET button is pressed
    public void resetTimer() {
        if (isRunning) {
            //If running, cancel timer and set isRunning false
            timer.cancel();
            isRunning = false;
        }

        //Reset counter i to 0 and update secs to reflect round value
        i = 0;
        secs = roundCount[i];

        updateTimer();
    }

    //Update the display of the timer
    public void updateTimer(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Get the textView where time is displayed
                TextView timerView = (TextView) getActivity().findViewById(R.id.timer_editText);
                //Ensure that the textView is not null.
                //This way the user can swipe to any other view while the timer is running.
                if(timerView != null) {
                    //Display time in 60.0 format to show tenths of a second
                    timerView.setText(String.valueOf(secs / 10 + "." + secs % 10));
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cancel the timer when fragment is destroyed
        if(isRunning){
            timer.cancel();
        }
    }
}