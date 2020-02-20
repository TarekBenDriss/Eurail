package com.bendriss.eurail.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bendriss.eurail.R;


public class WebViewFragment extends Fragment {
    private WebView eurailWebView;
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
        final WebView eurailWebView = view.findViewById(R.id.webview);
        eurailWebView.loadUrl("https://www.eurail.com/en/get-inspired");
        WebSettings webSettings = eurailWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        eurailWebView.getSettings().setDomStorageEnabled(true);
        eurailWebView.getSettings().setJavaScriptEnabled(true);

/*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                test(eurailWebView);
            }
        }, 2000);


 */
        eurailWebView.evaluateJavascript(
                /*

                function myFunction() {
                var list = document.getElementsByTagName("div");
                alert(list.length);
                document.getElementsByTagName("div").length
                }

                 */
                //"(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                "(function() { return document.getElementsByTagName('body').length; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        Log.d("HTML", html);
                        // code here
                    }
                });

        eurailWebView.findAllAsync("to");
        eurailWebView.setFindListener(new WebView.FindListener() {

            @Override
            public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
                /*
                if(isDoneCounting)
                    Toast.makeText(getContext(), "Matches: " + numberOfMatches, Toast.LENGTH_LONG).show();
                 */
            }
        });


        boolean loadingFinished = true;
        boolean redirect = false;

        eurailWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                /*
                if (!loadingFinished) {
                    redirect = true;
                }

                //loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;

                 */
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                //loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                test(eurailWebView);
                /*
                if(!redirect){
                    loadingFinished = true;
                }

                if(loadingFinished && !redirect){
                    //HIDE LOADING IT HAS FINISHED
                } else{
                    redirect = false;
                }

                 */

            }
        });



        return view;
    }

    private void test(WebView eurailWebView)
    {
        eurailWebView.evaluateJavascript(
                "(function() { return document.getElementsByTagName('div').length; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        Log.d("HTML", html);
                    }
                });
    }


}
