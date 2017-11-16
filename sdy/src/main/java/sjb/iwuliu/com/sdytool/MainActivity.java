package sjb.iwuliu.com.sdytool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.iwuliu.sdy.core.activity.ProxyActivity;
import com.iwuliu.sdy.core.delegate.BaseDelegate;
import com.iwuliu.sdy.core.delegate.SdyDelegate;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_act);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnotherActivity.class));
            }
        });
    }
}
