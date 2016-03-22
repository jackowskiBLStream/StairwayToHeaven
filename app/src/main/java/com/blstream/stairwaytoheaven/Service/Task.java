package com.blstream.stairwaytoheaven.Service;

import com.blstream.stairwaytoheaven.EratostenesSieve.EratosthenesSieve;

//import sun.rmi.runtime.Log;

/**
 * Single task class. Service runs it when user add task to list and parallel running tasks are less than 4
 * Thread will calculate Primes in loop until given time runs out.
 */
public class Task implements Runnable {
    static final int ERATOSTHENES_SHIEVE_LIMIT = 1000;
    private static final String TAG = "Task";
    EratosthenesSieve eratosthenesSieve;
    private TimeHolder timeHolder;

    public Task(TimeHolder timeHolder) {
        this.timeHolder = timeHolder;
        eratosthenesSieve = new EratosthenesSieve(ERATOSTHENES_SHIEVE_LIMIT);
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        while (timeHolder.getElapsedTime() < timeHolder.getDuration()) {
            long time = System.currentTimeMillis();
            eratosthenesSieve = new EratosthenesSieve(ERATOSTHENES_SHIEVE_LIMIT);
            eratosthenesSieve.getPrimeNumbers();
         //   Log.d(TAG, "run: calculating taken " + (System.currentTimeMillis() - time));
            timeHolder.setElapsedTime(timeHolder.getElapsedTime() + (System.currentTimeMillis() - time));
        }
        timeHolder.setElapsedTime(timeHolder.getDuration());
    }


}