package com.gif.ryan.improvtoolbox;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * This is the Main and Only activity that the application runs in.
 * All tabs are controlled by SwipeViews that contain fragments
 * used to display the individual feature tabs of the app.
 *
 * @ryancphil
 */
public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. Class defined at the bottom of this file.
     */
    SectionsPagerAdapter sectionsPagerAdapter;

    // The ViewPager that will host the section contents.
    ViewPager viewPager;

    //Radio Buttons that let the user know what tab they are viewing
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the radio buttons
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);

        //Set the first true by default since this is the tab the user is on when app loads
        radio1.setChecked(true);

        // Create the adapter that will return a fragment for each of the four
        // primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        //initialize the adView by finding it in xml and making a new adRequest
        //Then build the request and load the advertisement
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //This listener updates the radio buttons every time the user swipes to different tabs
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // check the radio button corresponding to the current tab position
                if(position == 0){
                    radio1.setChecked(true);
                }else if (position == 1){
                    radio2.setChecked(true);
                }else if(position == 2){
                    radio3.setChecked(true);
                }else{
                    radio4.setChecked(true);
                }
            }

            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //UNUSED
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //UNUSED
            }
        });
    }

    /**
     * A FragmentPagerAdapter that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            if(position == 0) {
                //Suggestionator is the fragment at position 0
                return Suggestionator.newInstance(position + 1);
            }else if(position == 1){
                //Soundboard is the fragment at position 1
                return Soundboard.newInstance(position + 1);
            }else if(position == 2) {
                //HalfLife is the fragment at position 2
                return HalfLife.newInstance(position + 1);
            }else {
                //GIFTwitter is the fragment at position 3
                return GIFTwitter.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
