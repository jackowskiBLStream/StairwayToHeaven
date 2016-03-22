package com.blstream.stairwaytoheaven.StartScreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.blstream.stairwaytoheaven.R;
import com.blstream.stairwaytoheaven.Service.MyServiceConnection;
import com.blstream.stairwaytoheaven.Service.TaskManagingService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 2016-03-21.
 */
public class StartScreenFragment extends Fragment {
    public static final String USER_TIME = "Zdefiniuj wlasny czas...";
    public static final String DIALOG_TAG = "Define time Dialog Fragment";
    int taskIdGenerator;
    private Spinner spinner;
    private Button startButton;
    private ArrayAdapter<String> dataAdapter;
    private List<String> list;
    private MyServiceConnection myServiceConnection;
    private long time;
    private DialogFragment dialogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_screen_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        startButton = (Button) view.findViewById(R.id.buttonStart);

        spinner = (Spinner) view.findViewById(R.id.spinner);

        list = new ArrayList<>();
        list.add("10 sekund");
        list.add("15 sekund");
        list.add("20 sekund");
        list.add("25 sekund");
        list.add(USER_TIME);

        dialogFragment = new DialogFragment();

        /*dataAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_spinner_item, list);*/
        dataAdapter = new ArrayAdapter<>
                (getActivity(), R.layout.spinner_layout, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
        addListenerOnButton();


    }



    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to  of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        taskIdGenerator = 0;
        myServiceConnection = new MyServiceConnection();
        Intent intent = new Intent(getContext(), TaskManagingService.class);
        getContext().bindService(intent, myServiceConnection, getActivity().BIND_AUTO_CREATE);
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link () Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (myServiceConnection.isBound()) {
            getContext().unbindService(myServiceConnection);
        }
    }

    private void addListenerOnSpinnerItemSelection() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == list.size() - 1) {

                    dialogFragment.show(getFragmentManager(), DIALOG_TAG);
                    dialogFragment.setCancelable(false);
                    dialogFragment.setFragment(StartScreenFragment.this);
                    dialogFragment.setList(list);
                    dialogFragment.setDataAdapter(dataAdapter);
                } else {
                    String[] parts = ((String) parent.getItemAtPosition(position)).split(" ");
                    time = Long.parseLong(parts[0]);
                    dialogFragment.setTime(0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void addListenerOnButton() {

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialogFragment != null && dialogFragment.getTime() > 0) {
                    time = dialogFragment.getTime();
                    myServiceConnection.getService().addTask(taskIdGenerator, time * 1000);
                    taskIdGenerator++;
                    dialogFragment.setTime(0);
                } else {
                    myServiceConnection.getService().addTask(taskIdGenerator, time * 1000);
                    taskIdGenerator++;
                }

            }

        });
    }


}
