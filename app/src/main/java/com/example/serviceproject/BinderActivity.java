package com.example.serviceproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

/**
 * Created by szidonia.laszlo on 2018. 03. 28..
 */

public class BinderActivity extends Activity {
    BinderLocalService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(" BinderActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(" ----> onStart => "+mConnection.toString());
        // Bind to LocalService
        Intent intent = new Intent(this, BinderLocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mBound = false;
        System.out.println(" ------> onStop => "+mBound);
    }

    /**
     * Called when a button is clicked (the button in the layout file attaches to
     * this method with the android:onClick attribute)
     */
    public void onButtonClick(View v) {
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.


           /* int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();*/


           mService.startIntent(this);
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BinderLocalService.LocalBinder binder = (BinderLocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            System.out.println(" ServiceConnection => mBound = "+mBound);

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            System.out.println(" onServiceDisconnected");
            mBound = false;
        }
    };
}
