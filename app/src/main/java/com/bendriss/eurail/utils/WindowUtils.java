package com.bendriss.eurail.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

/**
 * This class is used to declare some functions for our windows
 */
public class WindowUtils {
    /**
     * This function will set the status bar color to a new color
     *
     * @param activity the activity in which the status bar color is changed
     * @param color    is the color to set
     */
    public static void setStatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(activity, color));
        }
    }
}