package com.example.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ServiceConfigurationError;

/**
 * Created by szidonia.laszlo on 2018. 03. 28..
 */

public class MyService2 extends Service {

    private final IBinder mBinder = new LocalService();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalService extends Binder {

        MyService2 getService(){
            return MyService2.this;
        }
    }


    public String getFirstMessage(){
        return "First Button";
    }

    public String getSecondMessage() {
        return "Second Button";
    }
}
