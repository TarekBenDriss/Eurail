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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


        eurailWebView.evaluateJavascript(
                /*

                function myFunction() {
                var list = document.getElementsByTagName("div");
                alert(list.length);
                document.getElementsByTagName("div").length
                }

                 */
                //"(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                "(function() { return ('<html>'+document.getElementsByTagName('body')[0].innerHTML+'</html>'); })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        Log.d("HTML", html.toString());
                        // code here
                    }
                });

        /*
        eurailWebView.findAllAsync("to");
        eurailWebView.setFindListener(new WebView.FindListener() {

            @Override
            public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {

            }
        });
         */

        final boolean[] loadingFinished = {true};
        final boolean[] redirect = {false};

        String s = "\u003Chtml>\u003C/html>\u003Chtml>\u003C/html>";
        s= s.replace("\u003C","<");
        Log.e("HTML","hedha 11  "+s);
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

                //test(eurailWebView);

                if(!redirect[0]){
                    loadingFinished[0] = true;
                    test(eurailWebView);
                }

                if(loadingFinished[0] && !redirect[0]){
                    //HIDE LOADING IT HAS FINISHED
                } else{
                    redirect[0] = false;
                }



            }
        });



        return view;
    }

    private void test(WebView eurailWebView)
    {
        eurailWebView.evaluateJavascript(
                "(function() { return ('<html>'+document.getElementsByTagName('body')[0].innerHTML+'</html>'); })();",
                //"(function() { return document.querySelectorAll('div').length; })();",
                // \u003C
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        String s = html;
                        //s = s.replace("\u003C","<");

                        s= s.replace("\\u003C","<");
                        s= s.replace("\\u003C","<");
                        //Log.e("HTML","hedha   "+s);

                        //html = html.replace("\u003C/","/");

                        //Log.d("HTML",html);
                        Log.e("HTML", "1#"+countOccurences2(s,"\\u003C/div")+"");
                        Log.e("HTML", "2#"+countOccurences(html,"\\u003Cdiv")+"");
                    }
                });
        eurailWebView.evaluateJavascript(
                //"(function() { return ('<html>'+document.getElementsByTagName('body')[0].innerHTML+'</html>'); })();",
                "(function() { return document.querySelectorAll('div').length; })();",
                // \u003C
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        Log.e("HTML","3#"+html);
                    }
                });
    }

    private int countOccurences(String str, String word)
    {
        // split the string by spaces in a
        String a[] = str.split(" ");
        // search for pattern in a
        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            // if match found increase count
            if (word.equals(a[i]))
            {
                count=count+1;
                //appendLog(word);
                //writeToFile(a[i],getContext());
                //Log.e("HTML",word);
                //Log.e("HTML",a[i]);
            }
        }

        return count;
    }

    private int countOccurences2(String str,String word)
    {
        // split the string by spaces in a
        // search for pattern in a
        int count = 0;
        //Log.e("HTML","xxxx");
        System.out.println("xxxxxx "+str);
        //Log.wtf("xxxxxx ",str);
        for (int i = 0; i < str.length(); i++)
        {
            // if match found increase count
            if(str.charAt(i)=='<' && str.charAt(i+1)=='d' && str.charAt(i+2)=='i' && str.charAt(i+3)=='v')
            {count++;
            //Log.e("HTML",str.charAt(i)+str.charAt(i+1)+str.charAt(i+2)+str.charAt(+3)+"div");
            }
        }

        return count;
    }

}
