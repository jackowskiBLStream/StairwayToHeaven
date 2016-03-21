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

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public class TaskManagingService extends Service implements IAddingInterface, IcommunicatingProvider, Runnable {
    private class TaskContainer {
        private Thread task;
        private TimeHolder timeHolder;
        private int taskId;
        private boolean running;


        public TaskContainer(long durationTime, int taskId) {
            this.timeHolder = new TimeHolder(durationTime);
            this.task = new Thread(new Task(timeHolder));
            this.taskId = taskId;
        }

    }

    private static final int MAX_PARALLEL_TASKS_RUNNING = 4;
    private ArrayList<TaskContainer> taskQueue;
    private final IBinder mBinder = new LocalBinder();
    private Thread servicethread;

    /**
     * adds new task to queue in Service
     *
     * @param taskId       id of task to add
     * @param timeDuration duration of task
     */
    @Override
    public void addTask(int taskId, long timeDuration) {
        taskQueue.add(new TaskContainer(timeDuration, taskId));
    }


    /**
     * @return list of all queued task ids
     */
    @Override
    public ArrayList<TaskInformation> getAllTasksDetails() {
        ArrayList<TaskInformation> list = new ArrayList<>();

        for (TaskContainer container : taskQueue) {
            //FIXME: string resources & not in service
            list.add(new TaskInformation("Operacja przewidziana na " + container.timeHolder.getDuration() / 1000 + " sekund",
                    calculateProgress(container.timeHolder),
                    container.taskId));
        }
        return list;
    }

    private int calculateProgress(TimeHolder time) {
        return (int) ((time.getElapsedTime() * 100) / time.getDuration());
    }


    public class LocalBinder extends Binder {
        public TaskManagingService getService() {
            taskQueue = new ArrayList<>(); //FIXME: why?
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
        //FIXME: why?
        taskQueue = new ArrayList<>();
        servicethread = new Thread(this);
        servicethread.start();
        Log.d("SERVICE", "started");
        return mBinder;
    }

    @Override
    public void run() {

        //TODO: maybe ThreadPoolExecutor ?
        //TODO: maybe BlockingQueue ?

        while (true) {
            for (TaskContainer taskContainer : taskQueue) {
                //FIXME: too complicated if, move to method
                //FIXME: too many nested brackets
                if (
                        getExecutedCount() < MAX_PARALLEL_TASKS_RUNNING
                        && taskContainer.timeHolder.getElapsedTime() < taskContainer.timeHolder.getDuration()
                        && !taskContainer.task.isAlive()
                   ) {
                    taskContainer.task.start();
                    taskContainer.running = true;
                }

                //FIXME: use logger
                System.out.println("task " + taskContainer.taskId + " time: " + taskContainer.timeHolder.getElapsedTime() + " running:" + taskContainer.running);

            }
            try {
                //FIXME: hmmm :)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getExecutedCount() {
        int count = 0;
        for (TaskContainer taskContainer : taskQueue) {
            if (taskContainer.running) {
                count++;
            }
        }
        return count;
    }
}
