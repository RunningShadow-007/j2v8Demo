package com.feiyang.j2v8demo;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

/**
 * Copyright:j2v8Demo
 * Author: liyang <br>
 * Date:2018/12/23 5:22 PM<br>
 * Desc: <br>
 */
public class ReadTask extends AsyncTask<String, Void, String> {
    private WeakReference<Context> context;

    public ReadTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(String... strings) {
        String fileName = strings[0];
        if (context != null && context.get() != null) {
            return getFileContent(fileName, context.get());
        } else {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        EventBus.getDefault().post(new JsData(s));
    }

    private static String getFileContent(String fileName, Context context) {
        if (context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        AssetManager  am = context.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open(fileName)));
            String         line;
            while ((line = br.readLine()) != null) {
                System.out.println();
                sb.append(line).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
