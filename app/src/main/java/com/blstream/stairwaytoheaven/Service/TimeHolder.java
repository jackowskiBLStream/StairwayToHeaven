package com.blstream.stairwaytoheaven.Service;

/**
 * Created by Patryk Gwiazdowski on 21.03.2016.
 * class to hold and synchronize Task time
 */
public class TimeHolder {
    private long elapsedTime;
    private long durationTime;

    public TimeHolder(long durationTime){
        elapsedTime=0;
        this.durationTime = durationTime;
    }
    public synchronized long getElapsedTime() {
        return elapsedTime;
    }

    public synchronized void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getDuration() {
        return durationTime;
    }
}
