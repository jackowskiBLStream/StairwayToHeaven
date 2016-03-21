package com.blstream.stairwaytoheaven.Interfaces;

import java.util.ArrayList;

/**
 * Created by Krzysztof Antczak on 21.03.2016.
 */
public interface IcommunicatingProvider {

    /**
     *
     * @param taskId id of task
     * @return time elapsed to end of task with id given in parameter
     */
    long getElapsedTime(int taskId);

    /**
     *
     * @return list of all queued task ids
     */
    ArrayList<ITask> getAllTasksDetails();
}
