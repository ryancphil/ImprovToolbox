package com.gif.ryan.improvtoolbox;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This fragment contains the soundboard which is 9 buttons
 * that play different sounds.
 *
 * @ryancphil
 */
public class Soundboard extends Fragment {

    /*******************************************
     *              VARIABLES
     ********************************************/
    //Declare a MediaPlayer for each sound on the soundboard
    MediaPlayer airhornMp;
    MediaPlayer yayMp;
    MediaPlayer knockMp;
    MediaPlayer dingMp;
    MediaPlayer buzzerMp;
    MediaPlayer countdownMp;
    MediaPlayer phone1Mp;
    MediaPlayer phone2Mp;
    MediaPlayer doorbellMp;
    MediaPlayer beat1Mp;
    MediaPlayer beat2Mp;
    MediaPlayer beat3Mp;

    //Declare an AudioManager to handle MediaPlayers above
    AudioManager audio;

    //Tag for the section number when a new instance of this fragment is replaced
    private static final String ARG_SECTION_NUMBER = "section_number";

    /*******************************************
     *              CONSTRUCTOR
     ********************************************/
    public Soundboard() {
    }

    /*******************************************
     *              METHODS
     ********************************************/
    //Returns a new instance of this fragment for the given section number.
    public static Soundboard newInstance(int sectionNumber) {
        Soundboard fragment = new Soundboard();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instantiate the AudioManager
        audio =  (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Instantiate the MediaPlayers by referencing their raw audio files by id
        airhornMp = MediaPlayer.create(getActivity(),R.raw.airhorn);
        yayMp = MediaPlayer.create(getActivity(),R.raw.yay);
        knockMp = MediaPlayer.create(getActivity(),R.raw.knock);
        dingMp = MediaPlayer.create(getActivity(),R.raw.ding);
        buzzerMp = MediaPlayer.create(getActivity(),R.raw.buzzer);
        countdownMp = MediaPlayer.create(getActivity(),R.raw.countdown);
        phone1Mp = MediaPlayer.create(getActivity(),R.raw.phone1);
        phone2Mp = MediaPlayer.create(getActivity(),R.raw.phone2);
        doorbellMp = MediaPlayer.create(getActivity(),R.raw.doorbell);
        beat1Mp = MediaPlayer.create(getActivity(),R.raw.beat1);
        beat2Mp = MediaPlayer.create(getActivity(),R.raw.beat2);
        beat3Mp = MediaPlayer.create(getActivity(),R.raw.beat3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_soundboard, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //These ensure that if a beat is playing and the user swipes away that
        //when they come back to the tab the playing buttons are still in a pressed state
        if (beat1Mp.isPlaying()){
            Button beat1 = (Button) getActivity().findViewById(R.id.beat1);
            beat1.setBackgroundResource(R.drawable.button_pressed);
        }
        if (beat2Mp.isPlaying()){
            Button beat2 = (Button) getActivity().findViewById(R.id.beat1);
            beat2.setBackgroundResource(R.drawable.button_pressed);
        }
        if (beat3Mp.isPlaying()){
            Button beat3 = (Button) getActivity().findViewById(R.id.beat1);
            beat3.setBackgroundResource(R.drawable.button_pressed);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create the seekBar used for volume control
        SeekBar seekBar = (SeekBar) getView().findViewById(R.id.volume);
        seekBar.setProgress(50);

        //Listener to handle changing the volume when seekBar changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //UNUSED
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //UNUSED
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // Max value on a seekBar is 15 which is why the equation is used as follows
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(progress/6.67)+1, 0);

                //Update the textView to display current volume
                TextView vol = (TextView) getView().findViewById(R.id.volume_textview);
                vol.setText("Volume: " + progress + "%");
            }
        });

        //Create AIRHORN button and play airhorn sound when pressed
        Button airhorn = (Button) getView().findViewById(R.id.airhorn);
        airhorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(airhornMp.isPlaying()){
                    airhornMp.reset();
                    airhornMp = MediaPlayer.create(getActivity(),R.raw.airhorn);
                }
                airhornMp.start();
            }
        });

        //Create YAY button and play yay sound when pressed
        Button yay = (Button) getView().findViewById(R.id.yay);
        yay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yayMp.isPlaying()){
                    yayMp.reset();
                    yayMp = MediaPlayer.create(getActivity(),R.raw.yay);
                }
                yayMp.start();
            }
        });

        //Create KNOCK button and play knock sound when pressed
        Button knock = (Button) getView().findViewById(R.id.knock);
        knock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(knockMp.isPlaying()){
                    knockMp.reset();
                    knockMp = MediaPlayer.create(getActivity(),R.raw.knock);
                }
                knockMp.start();
            }
        });

        //Create DING button and play ding sound when pressed
        Button ding = (Button) getView().findViewById(R.id.ding);
        ding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dingMp.isPlaying()){
                    dingMp.reset();
                    dingMp = MediaPlayer.create(getActivity(),R.raw.ding);
                }
                dingMp.start();
            }
        });

        //Create BUZZER button and play buzzer sound when pressed
        final Button buzzer = (Button) getView().findViewById(R.id.buzzer);
        buzzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buzzerMp.isPlaying()){
                    buzzerMp.reset();
                    buzzerMp = MediaPlayer.create(getActivity(),R.raw.buzzer);
                }
                buzzerMp.start();
            }
        });

        //Create COUNTDOWN button and play countdown sound when pressed
        Button countdown = (Button) getView().findViewById(R.id.countdown);
        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countdownMp.isPlaying()){
                    countdownMp.reset();
                    countdownMp = MediaPlayer.create(getActivity(),R.raw.countdown);
                }
                countdownMp.start();
            }
        });

        //Create PHONE1 button and play phone1 sound when pressed
        Button phone1 = (Button) getView().findViewById(R.id.phone1);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone1Mp.isPlaying()){
                    phone1Mp.reset();
                    phone1Mp = MediaPlayer.create(getActivity(),R.raw.phone1);
                }
                phone1Mp.start();
            }
        });

        //Create PHONE2 button and play phone2 sound when pressed
        final Button phone2 = (Button) getView().findViewById(R.id.phone2);
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone2Mp.isPlaying()){
                    phone2Mp.reset();
                    phone2Mp = MediaPlayer.create(getActivity(),R.raw.phone2);
                }
                phone2Mp.start();
            }
        });

        //Create DOORBELL button and play doorbell sound when pressed
        Button doorbell = (Button) getView().findViewById(R.id.doorbell);
        doorbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doorbellMp.isPlaying()){
                    doorbellMp.reset();
                    doorbellMp = MediaPlayer.create(getActivity(),R.raw.doorbell);
                }
                doorbellMp.start();
            }
        });

        //Create BEAT1 button and play beat1 sound when pressed
        final Button beat1 = (Button) getView().findViewById(R.id.beat1);
        beat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(beat1Mp.isPlaying()){
                    beat1Mp.reset();
                    beat1Mp = MediaPlayer.create(getActivity(),R.raw.beat1);
                    beat1.setBackgroundResource(R.drawable.button);
                }else {
                    beat1Mp.start();
                    beat1.setBackgroundResource(R.drawable.button_pressed);
                }
            }
        });

        //Create BEAT2 button and play beat2 sound when pressed
        final Button beat2 = (Button) getView().findViewById(R.id.beat2);
        beat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(beat2Mp.isPlaying()){
                    beat2Mp.reset();
                    beat2Mp = MediaPlayer.create(getActivity(),R.raw.beat2);
                    beat2.setBackgroundResource(R.drawable.button);
                }else {
                    beat2Mp.start();
                    beat2.setBackgroundResource(R.drawable.button_pressed);
                }

            }
        });

        //Create BEAT3 button and play beat3 sound when pressed
        final Button beat3 = (Button) getView().findViewById(R.id.beat3);
        beat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(beat3Mp.isPlaying()){
                    beat3Mp.reset();
                    beat3Mp = MediaPlayer.create(getActivity(),R.raw.beat3);
                    beat3.setBackgroundResource(R.drawable.button);
                }else {
                    beat3Mp.start();
                    beat3.setBackgroundResource(R.drawable.button_pressed);
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Release all resources when fragment gets destroyed.
        airhornMp.release();
        yayMp.release();
        knockMp.release();
        dingMp.release();
        buzzerMp.release();
        countdownMp.release();
        phone1Mp.release();
        phone2Mp.release();
        doorbellMp.release();
        beat1Mp.release();
        beat2Mp.release();
        beat3Mp.release();
    }
}