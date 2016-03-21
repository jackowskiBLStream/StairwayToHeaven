package com.blstream.stairwaytoheaven.StartScreen;

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
import android.widget.Toast;

import com.blstream.stairwaytoheaven.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 2016-03-21.
 */
public class StartScreenFragment extends Fragment {
    public static final String USER_TIME = "Zdefiniuj wlasny czas...";
    public static final String DIALOG_TAG = "Define time Dialog Fragment";

    private Spinner spinner;
    private Button startButton;
    private ArrayAdapter<String> dataAdapter;
    private  List<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.start_screen_fragment_layout, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        startButton = (Button)view.findViewById(R.id.buttonStart);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        list = new ArrayList<>();
        list.add("10 sekund");
        list.add("15 sekund");
        list.add("20 sekund");
        list.add("25 sekund");
        list.add(USER_TIME);

        dataAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
        addListenerOnButton();


    }

    private void addListenerOnSpinnerItemSelection() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == list.size() - 1){
                    FragmentManager fm;
                    fm = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                    DialogFragment dialogFragment = new DialogFragment();
                    dialogFragment.show(fm, DIALOG_TAG);
                    dialogFragment.setFragment(StartScreenFragment.this);
                    dialogFragment.setDataAdapter(dataAdapter);
                    dialogFragment.setList(list);

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

                Toast.makeText(getActivity(),
                        "On Button Click : " +
                                "\n" + String.valueOf(spinner.getSelectedItem()),
                        Toast.LENGTH_LONG).show();
            }

        });
    }




}
