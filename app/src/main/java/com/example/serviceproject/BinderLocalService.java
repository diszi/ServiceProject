package com.example.serviceproject;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by szidonia.laszlo on 2018. 03. 28..
 */

public class BinderLocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */

    //The LocalBinder provides the getService() method for clients to retrieve the current instance of BinderLocalService
    public class LocalBinder extends Binder {
        BinderLocalService getService() {
            System.out.println(" LocalBinder => getService method ");
            // Return this instance of LocalService so clients can call public methods
            return BinderLocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("\n onBind = "+mBinder);
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    public void startIntent(Activity activity){
        Intent intent = new Intent(activity,SecondActivity.class);
        activity.startActivity(intent);
    }
}
