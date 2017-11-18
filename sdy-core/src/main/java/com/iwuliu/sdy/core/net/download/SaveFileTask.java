package com.iwuliu.sdy.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.iwuliu.sdy.core.app.Sdy;
import com.iwuliu.sdy.core.net.callback.IRequest;
import com.iwuliu.sdy.core.net.callback.ISuccess;
import com.iwuliu.sdy.core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by DarkSouls on 2017/11/18.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        String fileName = (String) params[2];
        ResponseBody body = (ResponseBody) params[3];
        InputStream is = body.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "downloads";
        }
        if (TextUtils.isEmpty(extension)) {
            extension = "";
        }
        if (TextUtils.isEmpty(fileName)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, fileName);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if ("apk".equals(FileUtil.getExtension(file.getPath()))) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Sdy.getApplication().startActivity(intent);
        }
    }
}
