package com.blstream.stairwaytoheaven.Service;

import com.blstream.stairwaytoheaven.Interfaces.ISingleTask;
import com.blstream.stairwaytoheaven.Interfaces.ITaskManagingService;

import java.util.ArrayList;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public class TaskManagingService implements ITaskManagingService {
    /**
     * @param taskId Id of task
     * @returns task's progress in percent. Returns 0 if is already waiting in queue
     */
    @Override
    public float getTaskProgress(int taskId) {
        return 0;
    }

    /**
     * @param taskId Id of task
     * @returns task's title
     */
    @Override
    public String getTaskTitle(int taskId) {
        return null;
    }

    /**
     * Add task to queue
     *
     * @param task
     * @returns true if properly added, false if not
     */
    @Override
    public boolean addTask(ISingleTask task) {
        return false;
    }

    /**
     * @returns taks's number being already executed
     */
    @Override
    public int getExecutedTasksNumber() {
        return 0;
    }

    /**
     * @returns all tasks' number
     */
    @Override
    public int getAllTasksNumber() {
        return 0;
    }

    /**
     * @return all ids of task in queue;
     */
    @Override
    public ArrayList<Integer> getAllTasksId() {
        return null;
    }
}
