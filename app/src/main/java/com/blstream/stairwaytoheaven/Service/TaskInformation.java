package com.blstream.stairwaytoheaven.Service;

import com.blstream.stairwaytoheaven.Interfaces.ITaskInformation;


public class TaskInformation implements ITaskInformation {
    private int id;
    private String title;
    private int progress;

    public TaskInformation(String title, int progress) {
        this.title = title;
        this.progress = progress;
    }

    @Override
    public String getTaskTitile(){
        return this.title;
    }

    @Override
    public int getTaskProgress() {
        return this.progress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
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
