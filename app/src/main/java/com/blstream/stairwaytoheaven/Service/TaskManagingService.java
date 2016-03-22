package com.blstream.stairwaytoheaven.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blstream.stairwaytoheaven.Interfaces.IAddingInterface;
import com.blstream.stairwaytoheaven.Interfaces.IcommunicatingProvider;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016. //you are simply the best ;)
 *
 */
public class TaskManagingService extends Service implements IAddingInterface, IcommunicatingProvider {

    /**
     * class that contains Task and some information about it:
     * taskId - id od task
     * timeHolder - Class that contains information about task duration and elapsed time
     * task - task thread that will be executed
     */
    private class TaskContainer {
        private Thread task;
        private TimeHolder timeHolder;


        public TaskContainer(long durationTime) {
            this.timeHolder = new TimeHolder(durationTime);
            this.task = new Thread(new Task(timeHolder));
        }

    }

    private static final int MAX_PARALLEL_TASKS_RUNNING = 4;
    private ArrayList<TaskContainer> taskQueue;
    private final IBinder mBinder = new LocalBinder();
    ThreadPoolExecutor executor;

    /**
     * adds new task to queue in Service
     *
     * @param taskId       id of task to add
     * @param timeDuration duration of task
     */
    @Override
    public void addTask(int taskId, long timeDuration) {
        TaskContainer taskContainer = new TaskContainer(timeDuration);
        taskQueue.add(taskContainer);
        executor.execute(taskContainer.task);
    }


    /**
     * @return list with information of all tasks in queue
     */
    @Override
    public ArrayList<TaskInformation> getAllTasksDetails() {
        ArrayList<TaskInformation> list = new ArrayList<>();
        for (TaskContainer container : taskQueue) {
            list.add(new TaskInformation(container.timeHolder.getDuration(),
                    calculateProgress(container.timeHolder)));
        }
        return list;
    }

    private int calculateProgress(TimeHolder time) {
        return (int) ((time.getElapsedTime() * 100) / time.getDuration());
    }


    public class LocalBinder extends Binder {
        public TaskManagingService getService() {
            return TaskManagingService.this;
        }
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        taskQueue = new ArrayList<>();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_PARALLEL_TASKS_RUNNING);
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

        Log.d("SERVICE", "bound");
        return mBinder;
    }

}
