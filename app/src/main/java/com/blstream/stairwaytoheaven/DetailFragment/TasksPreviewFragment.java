package com.blstream.stairwaytoheaven.DetailFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stairwaytoheaven.R;

public class TasksPreviewFragment extends Fragment {

    TasksPreviewListAdapter taskPreviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_preview_list_layout, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(taskPreviewAdapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskPreviewAdapter = new TasksPreviewListAdapter();

//        Intent intent  = new Intent(getContext(),)


    }

}
