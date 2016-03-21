package com.blstream.stairwaytoheaven.Interfaces;

import android.content.Context;

/**
 *
 */
public interface IService {

    /**
     *
     * @return task Id
     */
    int getId();

    /**
     *
     * @return name to be displayed in Listview
     */
    String getName();

    /**
     * @return returns task duration
     */
    long getInitialTime();

    /**
     *  Returns task elapsed time.
     *  If task has started it return time beetwen 0 and initial time
     *  if task has not started it return 0
     *  if task has ended it return initialTime
     * @param context android app context
     * @return task elapsed time.
     */
    long getElapsedTime(Context context);

    /**
     * Starts service
     * @return true if service was successfully bounded
     */
    boolean startService(Context context);





}
;