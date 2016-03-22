package com.blstream.stairwaytoheaven.StartScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blstream.stairwaytoheaven.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 2016-03-21.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment {
    public static final String VALIDATION_ERROR = "Blad walidacji!"; //FIXME: resource me maybe
    public static final String USER_TIME = "Zdefiniuj wlasny czas...";
    public static final String SEKUND = "sekund";
    public static final String DIALOG_TITLE = "Podaj czas w " + SEKUND + "ach"; //ach, och :D
    private EditText editTextNumbers;
    private ArrayAdapter<String> dataAdapter;
    private List<String> list; //FIXME: do ot modify adaper values in dialog, use okclicklistener
    private boolean isValid;
    private long time;

    //TODO: single responsibility -> do not modify SSF adapter

    public static DialogFragment newInstance() {
        return new DialogFragment();
    }


    public void setList(List<String> list) {
        this.list = list;
    }

    public void setDataAdapter(ArrayAdapter<String> dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: read args here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_layout, container, false);
        getDialog().setTitle(DIALOG_TITLE); //FIXME: resources, move to onViewCreated
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button okButton = (Button) view.findViewById(R.id.buttonOk);
        editTextNumbers = (EditText) view.findViewById(R.id.ediTextInputTime);
        editTextNumbers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (isInputValid(s)) {
                    isValid = true;
                    editTextNumbers.setError(null);
                } else {
                    isValid = false;
                    editTextNumbers.setError("Dupa!");
                }

                okButton.setEnabled(isValid);
            }



            private boolean isInputValid(CharSequence s) {
                return s.length() < 1; //TODO: something wiser
            }

            @Override
            public void afterTextChanged(Editable s) {
//                isValid = !(s.toString().isEmpty())
//                        && s.length() <= 9 //FIXME: a ja chce 1356789
//                        && Integer.parseInt(s.toString()) > 0
//                        && s.charAt(0) != '0';


            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    //TODO: handle listener properly
//                    okClikcListerner.onOkClicked(Integer.parseInt(editTextNumbers.getText().toString()));
                    list.add(editTextNumbers.getText().toString() + " " + SEKUND);
                    updateData(list, dataAdapter);
                    time = Long.parseLong(editTextNumbers.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getContext(), VALIDATION_ERROR, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void updateData(List<String> list, ArrayAdapter<String> dataAdapter) {
        List<String> tmpList = new ArrayList<>(list);

        dataAdapter.clear();

        for (String string : tmpList) {
            if (string.equals(USER_TIME)) {
                continue;
            }
            dataAdapter.insert(string, dataAdapter.getCount());
        }
        dataAdapter.insert(USER_TIME, dataAdapter.getCount());

        dataAdapter.notifyDataSetChanged();

    }

}
