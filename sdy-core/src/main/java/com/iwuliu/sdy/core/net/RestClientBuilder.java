package com.iwuliu.sdy.core.net;

import android.content.Context;

import com.iwuliu.sdy.core.net.callback.IError;
import com.iwuliu.sdy.core.net.callback.IFailure;
import com.iwuliu.sdy.core.net.callback.IRequest;
import com.iwuliu.sdy.core.net.callback.ISuccess;
import com.iwuliu.sdy.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by DarkSouls on 2017/11/16.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;

    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    private String mDownloadDir = null;
    private String mExtension = null;
    private String mFileName = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder downloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder fileName(String fileName) {
        this.mFileName = fileName;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSucess) {
        this.mISuccess = iSucess;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

//    private Map<String, Object> checkParams() {
//        if (PARAMS == null) {
//            return new WeakHashMap<>();
//        }
//        return PARAMS;
//    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        loader(context, LoaderStyle.BallSpinFadeLoaderIndicator);
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mFileName, mIRequest, mISuccess, mIFailure, mIError, mBody, mLoaderStyle, mFile, mContext);
    }
}
