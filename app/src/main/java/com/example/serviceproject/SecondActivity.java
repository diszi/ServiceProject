package com.example.serviceproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by szidonia.laszlo on 2018. 03. 28..
 */

public class SecondActivity  extends Activity {


    TextView text;
    MyService2 myService2;
    boolean isBind = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = (TextView)findViewById(R.id.text);

        Intent  intent = new Intent(this,MyService2.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

    }

    public void getFirstServiceMessage(View view){

        String message;
        message = myService2.getFirstMessage();

        text.setText(message);
    }

    public void getSecondServiceMessage(View view){

        String message;
        message = myService2.getSecondMessage();
        text.setText(message);
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {


            MyService2.LocalService localService = (MyService2.LocalService)iBinder;
            myService2 = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;

        }
    };

    @Override
    protected void onStop() {
        super.onStop();

        if (isBind){
            unbindService(mConnection);
            isBind = false;
        }
    }
}
