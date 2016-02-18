package co.ogury.ferreolgodebarge.testapk.utils;

import android.view.animation.TranslateAnimation;

/**
 * Created by bdufau on 1/22/2016.
 */
public class Translations {
    /**
     *
     * @param xDest : destination
     * @param side : -1 for left & 1 for right
     *             Create side translation
     */
    public static TranslateAnimation toTheSides(int xDest, int side) {
    //Zizou to the left
        TranslateAnimation mAnimation = new TranslateAnimation((-xDest*side)/2, (xDest*side)/2,
                0.0f, 0.0f);
        mAnimation.setDuration(5000);
        mAnimation.setRepeatCount(5000);
        mAnimation.setRepeatMode(2);
        mAnimation.setFillAfter(true);

        return mAnimation;

    }

}
