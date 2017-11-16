package com.iwuliu.sdy.core.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.iwuliu.sdy.core.R;
import com.iwuliu.sdy.core.delegate.BaseDelegate;
import com.iwuliu.sdy.core.delegate.SdyDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by DarkSouls on 2017/9/4.
 */

public abstract class ProxyActivity extends SupportActivity {

    protected abstract SdyDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("test", "debug #1");
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        ContentFrameLayout content = new ContentFrameLayout(this);
        content.setId(R.id.delegate_content);
        setContentView(content);
        Log.v("test", "debug #2");
        if (savedInstanceState == null) {
            Log.v("test", "debug #3");
            if (findFragment(setRootDelegate().getClass()) == null)
                loadRootFragment(R.id.delegate_content, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
