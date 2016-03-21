package com.blstream.stairwaytoheaven.Service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blstream.stairwaytoheaven.Interfaces.IAddingInterface;
import com.blstream.stairwaytoheaven.Interfaces.ITask;
import com.blstream.stairwaytoheaven.Interfaces.IcommunicatingProvider;

import java.util.ArrayList;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public class TaskManagingService extends Service implements IAddingInterface,IcommunicatingProvider {
    private Handler handler = new Handler();

    private final IBinder mBinder = new LocalBinder();

    /**
     * adds new task to queue in Service
     *
     * @param taskId       id of task to add
     * @param timeDuration duration of task
     */
    @Override
    public void addTask(int taskId, long timeDuration) {

    }

    /**
     * @param taskId id of task
     * @return time elapsed to end of task with id given in parameter
     */
    @Override
    public long getElapsedTime(int taskId) {
        return 0;
    }

    /**
     * @return list of all queued task ids
     */
    @Override
    public ArrayList<ITask> getAllTasksDetails() {
        return null;
    }

    public class LocalBinder extends Binder {
        public TaskManagingService getService() {
            return TaskManagingService.this;
        }
    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
