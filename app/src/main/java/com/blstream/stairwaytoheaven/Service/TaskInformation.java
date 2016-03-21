package com.blstream.stairwaytoheaven.Service;

import com.blstream.stairwaytoheaven.Interfaces.ITaskInformation;


public class TaskInformation implements ITaskInformation {
    private int id;
    private long title;
    private int progress;

    public TaskInformation(long title, int progress, int id) {
        this.title = title;
        this.progress = progress;
        this.id = id;
    }

    @Override
    public long getTaskDuration() {
        return this.title;
    }

    @Override
    public int getTaskProgress() {
        return this.progress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(long title) {
        this.title = title;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int getTaskId() {
        return id;

    }
}
