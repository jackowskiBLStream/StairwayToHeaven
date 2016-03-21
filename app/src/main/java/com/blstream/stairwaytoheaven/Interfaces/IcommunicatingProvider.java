package com.blstream.stairwaytoheaven.Interfaces;

import java.util.ArrayList;

/**
 * Created by Krzysztof Antczak on 21.03.2016.
 */
public interface IcommunicatingProvider {


    /**
     * @return list of all queued task ids
     */
    ArrayList<ITaskInformation> getAllTasksDetails();

}
