package com.blstream.stairwaytoheaven.DetailFragment;


import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blstream.stairwaytoheaven.Service.TaskInformation;
import com.blstream.stairwaytoheaven.R;

import java.util.ArrayList;

public class TasksPreviewListAdapter extends RecyclerView.Adapter<TasksPreviewListAdapter.ViewHolder> {
    ArrayList<TaskInformation> listOfTasks;
    String title;

    public TasksPreviewListAdapter() {

        listOfTasks = new ArrayList<>();
    }

    public ArrayList<TaskInformation> getListOfTasks(){
        return listOfTasks;
    }

    public void replaceListOfTasks(ArrayList<TaskInformation> list)
    {
        this.listOfTasks = list;
    }

    @Override
    public TasksPreviewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_preview_element_layout, parent, false);

        this.title = view.getContext().getString(R.string.task_title);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksPreviewListAdapter.ViewHolder holder, int position) {
        if(listOfTasks.size() != 0) {
            String titleWithDuration = this.title + " " + this.listOfTasks.get(position).getTaskDuration();;
            holder.mTitle.setText(titleWithDuration);
            holder.progressBar.setProgress(this.listOfTasks.get(position).getTaskProgress());

        }
    }

    @Override
    public int getItemCount() {
        return this.listOfTasks.size();
    }

    /**
     * class of ViewHolder implementation.
     * Use to remember each element in list.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final ProgressBar progressBar;

        /**
         * Constructor where we set reference to layout of progressbar and TextView.
         *
         * @param view instance of view.
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.taskTitle);
            progressBar = (ProgressBar) view.findViewById(R.id.taskProgressBar);
        }

        /**
         * @return text as String from titleTextView.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }


}
