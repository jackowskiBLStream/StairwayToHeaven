package com.blstream.stairwaytoheaven.DetailFragment;

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
    ArrayList<TaskInformation> arrayOfTasks;
    @Before
    public void setup(){
        adapter = new TasksPreviewListAdapter();
        arrayOfTasks = new ArrayList<>();
    }
    @Test
    public void shouldReturnEmptyArray(){
        //given
        //when
        //then
        assertArrayEquals(arrayOfTasks.toArray(),
                adapter.getListOfTasks().toArray());
    }
}