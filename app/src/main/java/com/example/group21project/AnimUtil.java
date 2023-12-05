package com.example.group21project;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.AlphaAnimation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.AnticipateInterpolator;
public class AnimUtil {
    // Fade in animation
    public static void fadeInView(View view, long duration) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Fade out animation
    public static void fadeOutView(View view, long duration) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Slide in animation
    public static void slideInView(View view, long duration, int fromXDelta, int toXDelta, int fromYDelta, int toYDelta) {
        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Slide out animation
    public static void slideOutView(View view, long duration, int fromXDelta, int toXDelta, int fromYDelta, int toYDelta) {
        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Rotate animation
    public static void rotateView(View view, long duration, float fromDegrees, float toDegrees) {
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Scale animation
    public static void scaleView(View view, long duration, float fromX, float toX, float fromY, float toY) {
        ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    // Shake animation
    public static void shakeView(View view, long duration, int repeatCount) {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(duration);
        shake.setInterpolator(new CycleInterpolator(repeatCount));
        view.startAnimation(shake);
    }

    // Bounce animation
    public static void bounceView(View view, long duration, float fromY, float toY) {
        TranslateAnimation bounce = new TranslateAnimation(0, 0, fromY, toY);
        bounce.setDuration(duration);
        bounce.setInterpolator(new BounceInterpolator());
        bounce.setRepeatCount(Animation.INFINITE);
        bounce.setRepeatMode(Animation.REVERSE);
        view.startAnimation(bounce);
    }

    // Set of animations together
    public static void setAnimationsTogether(View view, long duration, Animation... animations) {
        AnimationSet set = new AnimationSet(true);
        set.setDuration(duration);
        for (Animation anim : animations) {
            set.addAnimation(anim);
        }
        view.startAnimation(set);
    }

    public static void pulseView(View view, long duration, float scale) {
        ScaleAnimation pulse = new ScaleAnimation(1.0f, scale, 1.0f, scale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        pulse.setDuration(duration);

        pulse.setInterpolator(new CycleInterpolator(1));

        pulse.setRepeatCount(Animation.INFINITE);

        pulse.setRepeatMode(Animation.REVERSE);

        view.startAnimation(pulse);
    }

    // Flip animation horizontally
    public static void flipViewHorizontally(View view, long duration) {
        RotateAnimation flipHorizontal = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        flipHorizontal.setDuration(duration);

        flipHorizontal.setFillAfter(true);

        view.startAnimation(flipHorizontal);
    }

    // Flip animation vertically
    public static void flipViewVertically(View view, long duration) {
        RotateAnimation flipVertical = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.0f);

        flipVertical.setDuration(duration);

        flipVertical.setFillAfter(true);

        view.startAnimation(flipVertical);
    }

    // Zoom in
    public static void zoomInView(View view, long duration) {
        ScaleAnimation zoomIn = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        zoomIn.setDuration(duration);

        zoomIn.setInterpolator(new DecelerateInterpolator());

        view.startAnimation(zoomIn);
    }

    // Zoom out
    public static void zoomOutView(View view, long duration) {
        ScaleAnimation zoomOut = new ScaleAnimation(1f, 0f, 1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        zoomOut.setDuration(duration);

        zoomOut.setInterpolator(new AccelerateInterpolator());

        view.startAnimation(zoomOut);
    }

    // Slide view diagonally
    public static void slideViewDiagonally(View view, long duration, int fromXDelta, int toXDelta, int fromYDelta, int toYDelta) {

        TranslateAnimation slideDiagonal = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);

        slideDiagonal.setDuration(duration);

        slideDiagonal.setFillAfter(true);

        view.startAnimation(slideDiagonal);
    }

    // Animate view with overshoot effect
    public static void overshootView(View view, long duration, float fromScale, float toScale) {

        ScaleAnimation overshoot = new ScaleAnimation(fromScale, toScale, fromScale, toScale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        overshoot.setDuration(duration);

        overshoot.setInterpolator(new OvershootInterpolator());

        view.startAnimation(overshoot);
    }

    // Animate view with anticipation effect
    public static void anticipateView(View view, long duration, float fromScale, float toScale) {
        ScaleAnimation anticipate = new ScaleAnimation(fromScale, toScale, fromScale, toScale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anticipate.setDuration(duration);

        anticipate.setInterpolator(new AnticipateInterpolator());

        view.startAnimation(anticipate);
    }


}
