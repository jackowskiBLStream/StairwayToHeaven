package com.blstream.stairwaytoheaven.DetailFragment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blstream.stairwaytoheaven.CustomProgressBar.TextProgressBar;
import com.blstream.stairwaytoheaven.Service.TaskInformation;
import com.blstream.stairwaytoheaven.R;

import java.util.ArrayList;

/**
 * Class implementation of TaskPreview adapter.
 */
public class TasksPreviewListAdapter extends RecyclerView.Adapter<TasksPreviewListAdapter.ViewHolder> {
    ArrayList<TaskInformation> listOfTasks = new ArrayList<>();;
    Context context;

    /**
     * Constructor where we initialize ArrayList of tasks;
     */
    public TasksPreviewListAdapter(Context context) {

        this.context = context;
    }

    /**
     * Method to get all task on list;
     * @return ArrayList of tasks.
     */
    public ArrayList<TaskInformation> getListOfTasks(){
        return listOfTasks;
    }

    /**
     * Method to replace ArrayList of Tasks.
     * @param list ArrayList of Tasks.
     */
    public void replaceListOfTasks(ArrayList<TaskInformation> list)
    {
        this.listOfTasks = list;
    }

    /**
     * This method calls onCreateViewHolder(ViewGroup, int) to create a new RecyclerView.ViewHolder
     * and initializes some private fields to be used by RecyclerView.
     *
     * @return instance of ViewHolder;
     */
    @Override
    public TasksPreviewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_preview_element_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of TextView and ProgressBar.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(TasksPreviewListAdapter.ViewHolder holder, int position) {
        if(listOfTasks.size() != 0) {

            long seconds =  (this.listOfTasks.get(position).getTaskDuration() / 1000);
            int progress = this.listOfTasks.get(position).getTaskProgress();
            String titleWithDuration = context
                    .getString(R.string.task_title, seconds);
            String progressInPercent = context
                    .getString(R.string.progressInPercent, Integer.toString(progress));

            holder.mTitle.setText(titleWithDuration);
            holder.progressBar.setProgress(progress);
            holder.progressBar.setText(progressInPercent);
        }
    }

    /**
     *
     * @return number of Tasks in List.
     */
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
        public final TextProgressBar progressBar;

        /**
         * Constructor where we set reference to layout of progressbar and TextView.
         *
         * @param view instance of view.
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.taskTitle);
            progressBar = (TextProgressBar) view.findViewById(R.id.taskProgressBar);
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
