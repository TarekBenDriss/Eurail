package com.bendriss.eurail.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bendriss.eurail.R;

public class CountdownFragment extends Fragment {

    private TextView loadingTv;
    private ProgressBar progressBar;


    public CountdownFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);


        init(view);
        startCounting();
        return view;
    }

    /**
     * This function will init our vars
     *
     * @param view
     */
    private void init(View view) {
        loadingTv = view.findViewById(R.id.loadingTv);
        progressBar = view.findViewById(R.id.progressBar);
    }

    /**
     * This function will count from 10 to 0 with half a second delay between each number.
     * I added 500ms so we can see the first number which is 10
     */
    private void startCounting() {
        new CountDownTimer(5500, 500) {
            public void onTick(long millisUntilFinished) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress((int) millisUntilFinished / 500, true);
                } else
                    progressBar.setProgress((int) millisUntilFinished / 500);
                loadingTv.setText("Seconds remaining: " + millisUntilFinished / 500);
            }

            public void onFinish() {
                loadingTv.setText("Click on countdown icon to restart it");
            }
        }.start();
    }


}
