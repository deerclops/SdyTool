package sjb.iwuliu.com.sdytool;

import android.app.Application;

import com.iwuliu.sdy.bl.icon.FontModule;
import com.iwuliu.sdy.core.app.Sdy;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Sdy.init(getApplicationContext())
                .addIcon(new FontAwesomeModule())
                .addIcon(new FontModule())
                .withApiHost("Your Api Host")
                .configure();
    }
}
