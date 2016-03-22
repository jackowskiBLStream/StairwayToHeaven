package com.blstream.stairwaytoheaven.Service;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 *
 */
public class Task implements Runnable {
    static final long UPDATE_INTERVAL = 500;
    private TimeHolder timeHolder;
    private long expectedElapsedTime;
    private long now;

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
        now = System.currentTimeMillis();
        expectedElapsedTime = now + UPDATE_INTERVAL;
        while (timeHolder.getElapsedTime() < timeHolder.getDuration()) {
            long now = System.currentTimeMillis();
            while(now < expectedElapsedTime){
                now = System.currentTimeMillis();
            }
            expectedElapsedTime = now + UPDATE_INTERVAL;
            timeHolder.setElapsedTime(timeHolder.getElapsedTime() + UPDATE_INTERVAL);
        }
    }


}