package com.blstream.stairwaytoheaven.Service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public class MyServiceConnection implements ServiceConnection {
    private TaskManagingService mService;
    private boolean mBound = false;

    public MyServiceConnection() {
    }

    /**
     * Called when a connection to the Service has been established, with
     * the {@link IBinder} of the communication channel to the
     * Service.
     *
     * @param className The concrete component name of the service that has
     *                  been connected.
     * @param service   The IBinder of the Service's communication channel,
     */
    @Override
    public void onServiceConnected(ComponentName className,
                                   IBinder service) {

        TaskManagingService.LocalBinder binder = (TaskManagingService.LocalBinder) service;
        mService = binder.getService();
        mBound = true;
    }

    /**
     * Called when a connection to the Service has been lost.  This typically
     * happens when the process hosting the service has crashed or been killed.
     * This does <em>not</em> remove the ServiceConnection itself -- this
     * binding to the service will remain active, and you will receive a call
     * to {@link #onServiceConnected} when the Service is next running.
     *
     * @param The concrete component name of the service whose
     *            connection has been lost.
     */


    @Override
    public void onServiceDisconnected(ComponentName arg0) {
        mBound = false;
    }

    public boolean ismBound() {
        return mBound;
    }

    public void setmBound(boolean mBound) {
        this.mBound = mBound;
    }

    public TaskManagingService getmService() {
        return mService;
    }

    public void setmService(TaskManagingService mService) {
        this.mService = mService;
    }
}
