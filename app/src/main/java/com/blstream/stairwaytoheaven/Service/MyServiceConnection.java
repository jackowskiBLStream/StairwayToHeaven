package com.blstream.stairwaytoheaven.Service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Class for monitoring state of service connection
 */
public class MyServiceConnection implements ServiceConnection {
    private TaskManagingService service;
    private boolean bound = false;

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
        this.service = binder.getService();
        bound = true;
    }

    /**
     * Called when a connection to the Service has been lost.  This typically
     * happens when the process hosting the service has crashed or been killed.
     * This does <em>not</em> remove the ServiceConnection itself -- this
     * binding to the service will remain active, and you will receive a call
     * to {@link #onServiceConnected} when the Service is next running.
     *
     * @param arg0 concrete component name of the service whose
     *            connection has been lost.
     */
    @Override
    public void onServiceDisconnected(ComponentName arg0) {
        bound = false;
    }

    /**
     *
     * @return bound
     */
    public boolean isBound() {
        return bound;
    }

    /**
     *
     * @return service
     */
    public TaskManagingService getService() {
        return service;
    }

    /**
     *  set service to value given as parameter
     * @param service to be set
     */
    public void setService(TaskManagingService service) {
        this.service = service;
    }
}
