package com.blstream.stairwaytoheaven.Interfaces;

/**
 * interface to be implemented in TaskInformation class
 */
public interface ITaskInformation {


    /**
     *
     * @return task duration in milis
     */
    long getTaskDuration();

    /**
     *
     * @return task progress in % from 0 to 100
     */
    int getTaskProgress();

}
