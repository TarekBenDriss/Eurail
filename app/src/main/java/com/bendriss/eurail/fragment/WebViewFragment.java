package com.bendriss.eurail.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bendriss.eurail.R;
import com.bendriss.eurail.utils.ConnectivityUtils;


public class WebViewFragment extends Fragment {
    private WebView eurailWebView;
    private TextView numberOfDivsTv;
    private LottieAnimationView animationView;

    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        init(view);
        final boolean[] loadingFinished = {true};
        final boolean[] redirect = {false};

        eurailWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                numberOfDivsTv.setText(getResources().getString(R.string.wait_for_loading));

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!redirect[0]) {
                    loadingFinished[0] = true;
                    countNumberOfDivs(eurailWebView);
                }

                if (loadingFinished[0] && !redirect[0]) {
                    //HIDE LOADING IT HAS FINISHED
                } else {
                    redirect[0] = false;
                }
            }
        });

        return view;
    }

    /**
     * This function will init our vars
     *
     * @param view
     */
    private void init(View view) {
        numberOfDivsTv = view.findViewById(R.id.divsTv);
        animationView = view.findViewById(R.id.noConnection);
        eurailWebView = view.findViewById(R.id.webview);
        eurailWebView.getSettings().setDomStorageEnabled(true);
        eurailWebView.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * This is a broadcast receiver which will check if the internet connection status is changed and then perform a treatment
     */
    private final BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            loadUrlIntoWebView("https://www.eurail.com/en/get-inspired");
        }
    };


    /**
     * This function will load the url into the webview if the internet connection is active, otherwise it will show an animation
     *
     * @param url
     */
    private void loadUrlIntoWebView(String url) {
        if (ConnectivityUtils.checkInternetConnection(getContext())) {
            eurailWebView.loadUrl(url);
            animationView.setVisibility(View.INVISIBLE);

        } else {
            numberOfDivsTv.setText(getResources().getString(R.string.check_connection));
            animationView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * This function will insert a javascript code which will count the number of divs in the html
     *
     * @param eurailWebView
     */
    private void countNumberOfDivs(WebView eurailWebView) {
        eurailWebView.evaluateJavascript(
                "(function() { return document.querySelectorAll('div').length; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        numberOfDivsTv.setTextColor(getResources().getColor(R.color.dark_orange));
                        numberOfDivsTv.setText(getResources().getString(R.string.number_of_divs, html));
                    }
                });
    }

    /**
     * This function is a second method to count the number of div from the html code of the loaded page
     * It returns the same result as the above function
     * @param eurailWebView
     */
    private void countNumberOfDivsTest(WebView eurailWebView)
    {
        eurailWebView.evaluateJavascript(
                "(function() { return ('<html>'+document.getElementsByTagName('body')[0].innerHTML+'</html>'); })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        String s = html;
                        s= s.replace("\\u003C","<");
                        Log.e("HTML", ""+countOccurences(s,"\\u003C/div")+" occurences of div");
                    }
                });
    }

    /**
     * This function will count the number of occrences of a word
     * @param str the string in which we will search
     * @param word the string to search
     * @return
     */
    private int countOccurences(String str,String word)
    {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i)=='<' && str.charAt(i+1)=='d' && str.charAt(i+2)=='i' && str.charAt(i+3)=='v')
            {count++;}
        }
        return count;
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        getContext().unregisterReceiver(networkStateReceiver);
        super.onPause();
    }
}
