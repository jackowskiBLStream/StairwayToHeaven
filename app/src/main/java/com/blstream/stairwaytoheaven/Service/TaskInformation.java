package com.blstream.stairwaytoheaven.Service;

import com.blstream.stairwaytoheaven.Interfaces.ITaskInformation;

/**
 * class contains information about task. It is used to update list in TaskPreviewFragment
 */
public class TaskInformation implements ITaskInformation {
    private long title;
    private int progress;

    public TaskInformation(long title, int progress) {
        this.title = title;
        this.progress = progress;
    }

    /**
     *
     * @return task duration in milis
     */
    @Override
    public long getTaskDuration() {
        return this.title;
    }

    /**
     *
     * @return task progress in % from 0 to 100
     */
    @Override
    public int getTaskProgress() {
        return this.progress;
    }

}
