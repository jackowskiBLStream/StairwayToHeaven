package com.blstream.stairwaytoheaven.DetailFragment;

import com.blstream.stairwaytoheaven.Interfaces.ITaskInformation;
import com.blstream.stairwaytoheaven.Tasks.TaskInformation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 *
 * Created by Krzysztof Antczak on 21.03.2016.
 */
public class TasksPreviewListAdapterTest {
    TasksPreviewListAdapter adapter;
    ArrayList<ITaskInformation> Tasks;
    @Before
    public void setup(){
        adapter = new TasksPreviewListAdapter();
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
        Tasks.add(new TaskInformation("ABC", 5));
        Tasks.add(new TaskInformation("ABCD", 6));
        Object[] arrayofTasks = Tasks.toArray();
        //when
        adapter.replaceListOfTasks(Tasks);
        Object[] arrayOfTaskFromAdapter = adapter.getListOfTasks().toArray();
        //then
        assertArrayEquals(arrayofTasks,arrayOfTaskFromAdapter);
    }
}