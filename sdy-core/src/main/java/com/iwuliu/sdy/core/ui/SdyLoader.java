package com.iwuliu.sdy.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.iwuliu.sdy.core.R;
import com.iwuliu.sdy.core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by DarkSouls on 2017/11/16.
 */

public class SdyLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreater.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int screenWidth = DimenUtil.getScreenWidth();
        int screenHeight = DimenUtil.getScreenHeight();

        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = screenWidth / LOADER_SIZE_SCALE;
            lp.height = screenHeight / LOADER_SIZE_SCALE;
            lp.height += screenHeight / LOADER_OFFSET_SCALE;

            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
