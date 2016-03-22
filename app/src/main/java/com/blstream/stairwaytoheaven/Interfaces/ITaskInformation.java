package com.blstream.stairwaytoheaven.Interfaces;


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
