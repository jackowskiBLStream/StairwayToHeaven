package com.blstream.stairwaytoheaven;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.blstream.stairwaytoheaven.Service.TaskManagingService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ServiceTest {
    TaskManagingService service;
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Before
    public void setUp() throws Exception{

        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        TaskManagingService.class);
        IBinder binder = mServiceRule.bindService(serviceIntent);

        service = ((TaskManagingService.LocalBinder) binder).getService();
    }

    @Test
    public void testSizeOfAllTaskDetailsAfterAddingOne() throws Exception {
        //Given
        service.addTask(1,500);

        //When
        int size = service.getAllTasksDetails().size();

        //Then
        assertEquals("test msg",1,size);
    }

    @Test
    public void testAfterAddingFiveTasksLastShouldHaveDurationTimeAtZero() throws Exception {
        //Given
        service.addTask(1,500);
        service.addTask(1,500);
        service.addTask(1,500);
        service.addTask(1,500);
        service.addTask(1,500);

        //When
        long progress = service.getAllTasksDetails().size();

        //Then
      //  assertEquals("test msg",1,size);
    }
}