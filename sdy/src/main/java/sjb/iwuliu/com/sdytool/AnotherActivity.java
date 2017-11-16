package sjb.iwuliu.com.sdytool;

import com.iwuliu.sdy.core.activity.ProxyActivity;
import com.iwuliu.sdy.core.delegate.SdyDelegate;

/**
 * Created by DarkSouls on 2017/9/5.
 */

public class AnotherActivity extends ProxyActivity {
    @Override
    protected SdyDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
