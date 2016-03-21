package com.blstream.stairwaytoheaven.Interfaces;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public interface IAddingInterface {
    /**
     * adds new task to queue in Service
     * @param taskId id of task to add
     * @param timeDuration duration of task
     */
    public void addTask(int taskId, long timeDuration);
}
