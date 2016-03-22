package com.blstream.stairwaytoheaven.DetailFragment;

import com.blstream.stairwaytoheaven.Service.TaskInformation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TasksPreviewListAdapterTest {
    TasksPreviewListAdapter adapter;
    ArrayList<TaskInformation> Tasks;
    @Before
    public void setup(){
        adapter = new TasksPreviewListAdapter("Title");
        Tasks = new ArrayList<>();
    }
    @Test
    public void shouldReturnEmptyArray(){
        //given
        Object[] arrayOfTaskFromList = Tasks.toArray();
        //when
        Object[] arrayOfTaskFromAdapter = adapter.getListOfTasks().toArray();
        //then
        assertArrayEquals(arrayOfTaskFromAdapter,arrayOfTaskFromList);
    }

    @Test
    public void shouldAddArrayToAdapter(){
        //given
        Tasks.add(new com.blstream.stairwaytoheaven.Service.TaskInformation(10,1000,0));
        Tasks.add(new com.blstream.stairwaytoheaven.Service.TaskInformation(10,1000,1));
        Object[] arrayofTasks = Tasks.toArray();
        //when
        adapter.replaceListOfTasks(Tasks);
        Object[] arrayOfTaskFromAdapter = adapter.getListOfTasks().toArray();
        //then
        assertArrayEquals(arrayofTasks,arrayOfTaskFromAdapter);
    }
}