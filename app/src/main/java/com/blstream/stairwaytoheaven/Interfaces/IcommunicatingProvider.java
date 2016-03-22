package com.blstream.stairwaytoheaven.Interfaces;

import com.blstream.stairwaytoheaven.Service.TaskInformation;

import java.util.ArrayList;

/**
 * Interfae to be implemented in TaskManagingService for communication between Service and TaskPreviewFragment
 */
public interface IcommunicatingProvider {


    /**
     * @return list of all queued task ids
     */
    ArrayList<TaskInformation> getAllTasksDetails();

}
