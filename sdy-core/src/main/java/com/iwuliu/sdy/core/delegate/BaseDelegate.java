package com.iwuliu.sdy.core.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by DarkSouls on 2017/9/4.
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnBinder;

    protected abstract Object setLayout();

    protected abstract void onBind(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            mUnBinder = ButterKnife.bind(this, rootView);
            Log.d(this.getClass().getName(), mUnBinder == null ? "yes" : "no");
            onBind(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null)
            mUnBinder.unbind();
    }
}
