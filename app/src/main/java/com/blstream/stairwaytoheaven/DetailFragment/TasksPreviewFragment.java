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


public class TasksPreviewFragment extends Fragment {

    private static final int DELAYED_TIME_IN_MILLISECONDS = 500;

    TasksPreviewListAdapter taskPreviewAdapter;
    private static Handler handler = new Handler();
    TaskManagingService mService;
    ArrayList<TaskInformation> allTasks;
    ExecutorService threadPoolExecutor  = Executors.newSingleThreadExecutor();
    private Runnable mStatusChecker = new Runnable(){
        @Override
        public void run() {
            updateTasksInList();
            handler.postDelayed(mStatusChecker, DELAYED_TIME_IN_MILLISECONDS);
        }
    };
    Future longRunningTaskFuture = threadPoolExecutor.submit(mStatusChecker);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_preview_list_layout, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(taskPreviewAdapter);
        allTasks = taskPreviewAdapter.getListOfTasks();
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskPreviewAdapter = new TasksPreviewListAdapter();
    }
    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            TaskManagingService.LocalBinder binder = (TaskManagingService.LocalBinder) service;
            mService = binder.getService();
            mStatusChecker.run();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            longRunningTaskFuture.cancel(true);
        }
    };

    private void updateTasksInList(){
        allTasks = mService.getAllTasksDetails();
        taskPreviewAdapter.replaceListOfTasks(allTasks);
        taskPreviewAdapter.notifyDataSetChanged();
        Log.d("FRAGMENT UPDATE ADAPTER", "updateTasksInList: update");
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unbindService(mConnection);
    }

    @Override
    public void onResume(){
        super.onStart();
        Intent intent  = new Intent(getContext(),TaskManagingService.class);
        getContext().bindService(intent, mConnection, Context.BIND_ABOVE_CLIENT);
        Log.d("FRAGMENT BOUND", "onCreate: bound");
    }

}
