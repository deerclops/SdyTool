package com.iwuliu.sdy.core.net.download;

import android.os.AsyncTask;

import com.iwuliu.sdy.core.net.RestCreator;
import com.iwuliu.sdy.core.net.callback.IError;
import com.iwuliu.sdy.core.net.callback.IFailure;
import com.iwuliu.sdy.core.net.callback.IRequest;
import com.iwuliu.sdy.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DarkSouls on 2017/11/18.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILE_NAME;

    public DownloadHandler(String url,
                           IRequest request, ISuccess success, IFailure failure, IError error,
                           String downloadDir,
                           String extension,
                           String fileName) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILE_NAME = fileName;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            SaveFileTask saveFileTask = new SaveFileTask(REQUEST, SUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, FILE_NAME, body);

                            if (saveFileTask.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
