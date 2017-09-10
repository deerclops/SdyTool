package com.iwuliu.sdy.core.net;

import com.iwuliu.sdy.core.app.ConfigType;
import com.iwuliu.sdy.core.app.Configurator;
import com.iwuliu.sdy.core.app.Sdy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by DarkSouls on 2017/9/10.
 */

public class RestCreator {

    public RestService getRestService() {
        return RestServiceHolder.REST_SERVICE_HOLDER;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Sdy.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpClientHolder {
        private static final int TIME_OUT_SECONDS = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE_HOLDER = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
