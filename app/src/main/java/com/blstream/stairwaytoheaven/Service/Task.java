package com.blstream.stairwaytoheaven.Service;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 */
public class Task implements Runnable {
    static final long updateInterval = 500;
    private TimeHolder timeHolder;

    public Task(TimeHolder timeHolder) {
        this.timeHolder=timeHolder;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        while (timeHolder.getElapsedTime() < timeHolder.getDuration()) {
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeHolder.setElapsedTime(timeHolder.getElapsedTime() + updateInterval);
        }
    }


}