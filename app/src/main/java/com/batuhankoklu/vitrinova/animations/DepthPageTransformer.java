package com.batuhankoklu.vitrinova.animations;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class DepthPageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)
        } else if (position <= 0) { // [-1,0]
            view.setTranslationX(0);
        } else if (position <= 1) { // (0,1]
            //Altından gelmesi için
            view.setTranslationX(pageWidth * -position);

        } else { // (1,+Infinity]

        }
    }
}