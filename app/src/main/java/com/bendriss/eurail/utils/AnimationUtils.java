package com.bendriss.eurail.utils;

import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * This class is used to declare some functions for the animation of views
 */
public class AnimationUtils {

    /**
     * This function will make an animation using the alpha.
     *
     * @param img           the imageView which will be animated
     * @param start         the start value of alpha
     * @param end           the final value of alpha
     * @param duration      the duration of the animation
     * @param startDuration start after some time
     */
    public static void setAlphaAnimation(ImageView img, float start, float end, int duration, int startDuration) {
        AlphaAnimation animation = new AlphaAnimation(start, end);
        animation.setDuration(duration);
        animation.setStartOffset(startDuration);
        animation.setFillAfter(true);
        img.startAnimation(animation);
    }
}