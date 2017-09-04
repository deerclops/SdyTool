package com.iwuliu.sdy.core.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.iwuliu.sdy.core.R;
import com.iwuliu.sdy.core.delegate.SdyDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by DarkSouls on 2017/9/4.
 */

public abstract class ProxyActivity extends SupportActivity {

    protected abstract SdyDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        ContentFrameLayout content = new ContentFrameLayout(this);
        content.setId(R.id.delegate_content);
        setContentView(content);
        if (savedInstanceState == null) {
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
