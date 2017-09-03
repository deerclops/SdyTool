package com.iwuliu.sdy.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public final class Sdy {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getConfigs();
    }
}
