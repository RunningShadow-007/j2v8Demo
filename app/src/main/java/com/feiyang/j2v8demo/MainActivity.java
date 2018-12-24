package com.feiyang.j2v8demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.V8Object;
import com.feiyang.j2v8demo.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;


    private V8 v8 = null;


    private int index = 0;

    private boolean available = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        v8 = V8.createV8Runtime();
        ReadTask readTask = new ReadTask(this);
        readTask.execute("rq-exam.js");

        mBinding.button.setOnClickListener(v -> {
            if (!available) {
                return;
            }
            index++;
            switch (index) {
                case 1:
                    String pre = v8.getString("pre");
                    mBinding.textView.append(pre);
                    mBinding.textView.append("\n");
                    break;
                case 2:
                    mBinding.textView.append("\n\n");
                    String preLogin = v8.getString("pre_autologin");
                    mBinding.textView.append(preLogin);
                    mBinding.textView.append("\n");
                    break;
                case 3:
                    mBinding.textView.append("\n\n");
                    V8Object object = v8.getObject("ApiUrl");

                    for (String key : object.getKeys()) {
                        mBinding.textView.append(object.getString(key));
                        mBinding.textView.append("\n");
                    }
                    object.release();
                    break;
                case 4:
                    v8.registerJavaMethod(this, "toast", "printData", new Class<?>[]{String.class});
                    v8.executeJSFunction("fillData", "李阳", 29);
                    break;
                case 5:
                    V8Function function = (V8Function) v8.get("fillData");
                    V8Array params = new V8Array(v8);
                    params.push("李朋");
                    params.push(26);
                    function.call(null, params);
                    params.release();
                    function.release();
                    break;
            }

        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onGetJsString(JsData data) {
        if (!TextUtils.isEmpty(data.getContent())) {
            available = true;
            v8.executeObjectScript(data.getContent());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (v8 != null && !v8.isReleased()) {
            v8.release();
        }
        super.onDestroy();
    }

    public void toast(String details) {
        Toast.makeText(this, details, Toast.LENGTH_SHORT).show();
    }
}
