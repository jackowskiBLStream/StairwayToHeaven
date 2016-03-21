package com.blstream.stairwaytoheaven.Interfaces;


public interface ITaskInformation {

    int getTaskId();

    long getTaskDuration();

    /**
     *
     * @return task progress in % from 0 to 100
     */
    int getTaskProgress();

}
