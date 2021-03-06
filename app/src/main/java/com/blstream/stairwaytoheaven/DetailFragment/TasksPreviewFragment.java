package com.blstream.stairwaytoheaven.DetailFragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stairwaytoheaven.R;
import com.blstream.stairwaytoheaven.Service.TaskInformation;
import com.blstream.stairwaytoheaven.Service.TaskManagingService;

import java.util.ArrayList;

/**
 * Implementation of TaskPreviewFragment
 */
public class TasksPreviewFragment extends Fragment {
    /**
     * Delay of handler.postDelay(value)
     */
    private static final int DELAYED_TIME_IN_MILLISECONDS = 500;

    TasksPreviewListAdapter taskPreviewAdapter;
    private static Handler handler = new Handler();
    TaskManagingService mService;
    private Runnable mStatusChecker = new Runnable(){
        @Override
        public void run() {
            updateTasksInList();
            handler.postDelayed(this, DELAYED_TIME_IN_MILLISECONDS);
        }
    };

    /**
     * Creating new instance of TaskPreviewAdapter
     * @param savedInstanceState Bundle of savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskPreviewAdapter = new TasksPreviewListAdapter(getContext());
    }

    /**
     *Creates and returns the view hierarchy associated with the fragment.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container  If non-null, this is the parent view that the fragment's UI should be attached to.
     *                   The fragment should not add the view itself,
     *                   but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState  If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_preview_list_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskPreviewAdapter);
        return view;
    }

    /**
     * Unbind service;
     */
    @Override
    public void onPause() {
        getContext().unbindService(mConnection);
        super.onPause();

    }
    /**
     * Binding to service
     */
    @Override
    public void onResume(){
        super.onResume();
        Intent intent  = new Intent(getContext(),TaskManagingService.class);
        getContext().bindService(intent, mConnection, Context.BIND_ABOVE_CLIENT);

    }
    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            TaskManagingService.LocalBinder binder = (TaskManagingService.LocalBinder) service;
            mService = binder.getService();
            handler.post(mStatusChecker);
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            handler.removeCallbacks(mStatusChecker);
        }
    };

    /**
     * Methods used to update all tasks in list in Adapter.
     */
    private void updateTasksInList(){
        ArrayList<TaskInformation> allTasks = mService.getAllTasksDetails();
        taskPreviewAdapter.replaceListOfTasks(allTasks);
        taskPreviewAdapter.notifyDataSetChanged();
    }
}
