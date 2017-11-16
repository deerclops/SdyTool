package sjb.iwuliu.com.sdytool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iwuliu.sdy.core.delegate.SdyDelegate;
import com.iwuliu.sdy.core.net.RestClient;
import com.iwuliu.sdy.core.net.callback.IError;
import com.iwuliu.sdy.core.net.callback.IFailure;
import com.iwuliu.sdy.core.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by DarkSouls on 2017/9/5.
 */

public class ExampleDelegate extends SdyDelegate {

    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et)
    EditText et;

    @Override
    protected void onBind(@Nullable Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRestClient();
            }
        });
    }

    private void testRestClient() {
        RestClient.builder()
                .url("/explore")
//                .params("", "")
                .loader(getActivity())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
