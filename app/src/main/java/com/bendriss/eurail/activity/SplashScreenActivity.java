package com.bendriss.eurail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bendriss.eurail.R;
import com.bendriss.eurail.databinding.ActivitySplashScreenBinding;
import com.bendriss.eurail.utils.AnimationUtils;
import com.bendriss.eurail.utils.WindowUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashScreenBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        /**
         * I changed the color of status bar to green
         */
        WindowUtils.setStatusBarColor(this, R.color.colorPrimary);
        /**
         * I made an alpha animation for the eurail's logo
         */
        AnimationUtils.setAlphaAnimation(mBinding.logoIv, 0.0f, 1.0f, 4000, 0);

        /**
         * The redirection to the main activity will be performed after 4200ms
         */
        Handler handler = new Handler();
        Runnable redirection = new Runnable() {
            public void run() {
                try {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        handler.postDelayed(redirection, 4200);


    }
}
