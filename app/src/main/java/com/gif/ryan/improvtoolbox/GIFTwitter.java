package com.gif.ryan.improvtoolbox;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * This fragment displays the Gainesville Improv Festival's Twitter feed
 * inside of a WebView.
 *
 * @ryancphil
 */
public class GIFTwitter extends Fragment {

    /*******************************************
     *              VARIABLES
     ********************************************/
    //Tag for the section number when a new instance of this fragment is replaced
    private static final String ARG_SECTION_NUMBER = "section_number";

    private WebView webView;

    /*******************************************
     *              CONSTRUCTOR
     ********************************************/
    public GIFTwitter() {
    }

    /*******************************************
     *              METHODS
     ********************************************/
    //Returns a new instance of this fragment for the given section number.
    public static GIFTwitter newInstance(int sectionNumber) {
        GIFTwitter fragment = new GIFTwitter();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_giftwitter, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Find the webView using id
        webView = (WebView) getActivity().findViewById(R.id.webView1);
        //Enable javascript
        webView.getSettings().setJavaScriptEnabled(true);
        //Set view client so that page loads in the window of the application
        webView.setWebViewClient(new WebViewClient());
        //Set the url you want loaded into the webView
        webView.loadUrl("https://twitter.com/gvilleimprov");
    }
}
