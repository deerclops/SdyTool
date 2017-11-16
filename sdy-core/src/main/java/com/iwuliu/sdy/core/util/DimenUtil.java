package com.iwuliu.sdy.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.iwuliu.sdy.core.app.Sdy;

/**
 * Created by DarkSouls on 2017/11/16.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        Resources resources = Sdy.getApplication().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        Resources resources = Sdy.getApplication().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
