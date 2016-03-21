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
public class TaskManagingService extends Service implements IAddingInterface, IcommunicatingProvider, Runnable {
    private class TaskContainer {
        private Thread task;
        private TimeHolder timeHolder;
        private int taskId;

        public TaskContainer(long durationTime, int taskId) {
            this.timeHolder = new TimeHolder(durationTime);
            this.task = new Thread(new Task(timeHolder));
            this.taskId = taskId;
        }

    }

    private static final int MAX_PARALLEL_TASKS_RUNNING = 4;
    private Handler handler = new Handler();
    private ArrayList<TaskContainer> taskQueue;
    private final IBinder mBinder = new LocalBinder();
    private int executedCount;

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

    /**
     * @return current size of queue
     */
    @Override
    public int getQueueSize() {
        return taskQueue.size();
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
        taskQueue = new ArrayList<>();
        return mBinder;
    }

    @Override
    public void run() {
        while (true) {
            for (TaskContainer task : taskQueue) {
                if (executedCount < MAX_PARALLEL_TASKS_RUNNING && !task.task.isAlive()) {
                    task.task.start();
//                    taskPreviewFragment.returnAdapter().addItem(task.getId());

                    executedCount++;
                }
                System.out.println("task " + task.taskId + " time: " + task.timeHolder.getElapsedTime());

            }
         //   removeFinished(taskQueue);

//            taskPreviewFragment.returnAdapter().notifyDataSetChanged();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
