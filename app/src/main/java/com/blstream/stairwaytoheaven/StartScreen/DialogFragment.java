package com.blstream.stairwaytoheaven.StartScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.blstream.stairwaytoheaven.Interfaces.IDialogHelper;
import com.blstream.stairwaytoheaven.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 2016-03-21.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment {
    private Button okButton;
    private EditText editTextNumbers;
    private Fragment fragment;
    private ArrayAdapter<String> dataAdapter;
    private List<String> list;

    public void setFragment(Fragment fragment){
        this.fragment = fragment;
    }
    public static final String USER_TIME = "Zdefiniuj wlasny czas...";


    public static final String DIALOG_TITLE = "Podaj czas w sekundach";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_layout, container, false);
        getDialog().setTitle(DIALOG_TITLE);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton = (Button)view.findViewById(R.id.buttonOk);
        editTextNumbers = (EditText)view.findViewById(R.id.ediTextInputTime);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.add(editTextNumbers.getText().toString());
                updateData(list, dataAdapter);
                dismiss();
            }
        });
    }
    private void updateData(List<String> list, ArrayAdapter<String> dataAdapter) {
        List<String> tmpList = new ArrayList<>(list);

        dataAdapter.clear();

        for (String string : tmpList) {
            if(string.equals(USER_TIME)){
                continue;
            }
            dataAdapter.insert(string, dataAdapter.getCount());
        }
        dataAdapter.insert(USER_TIME, dataAdapter.getCount());

        dataAdapter.notifyDataSetChanged();

    }

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
    }
}
