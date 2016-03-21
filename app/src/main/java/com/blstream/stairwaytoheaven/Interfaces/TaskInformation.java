package com.blstream.stairwaytoheaven.Interfaces;


public interface TaskInformation {

    int getTaskId();

    int getTaskTitile();

    /**
     *
     * @return task progress in % from 0 to 100
     */
    int getTaskProgress();

}
