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


import com.blstream.stairwaytoheaven.Interfaces.ITaskInformation;
import com.blstream.stairwaytoheaven.R;
import com.blstream.stairwaytoheaven.Service.TaskManagingService;

import java.util.ArrayList;


public class TasksPreviewFragment extends Fragment {

    TasksPreviewListAdapter taskPreviewAdapter;
    TaskManagingService mService;
    ArrayList<ITaskInformation> Tasks;
    private boolean mBound;
    Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_preview_list_layout, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(taskPreviewAdapter);
        Tasks = taskPreviewAdapter.getListOfTasks();
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskPreviewAdapter = new TasksPreviewListAdapter();
        Intent intent  = new Intent(getContext(),TaskManagingService.class);
        getContext().bindService(intent,mConnection,Context.BIND_ABOVE_CLIENT);
        handler = new Handler();
        if(mBound){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    updateTasksInList();
                }
            });
        }
    }
    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            TaskManagingService.LocalBinder binder = (TaskManagingService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private void updateTasksInList(){
        Tasks = mService.getAllTasksDetails();
        taskPreviewAdapter.replaceListOfTasks(Tasks);
        taskPreviewAdapter.notifyDataSetChanged();
    }

}
