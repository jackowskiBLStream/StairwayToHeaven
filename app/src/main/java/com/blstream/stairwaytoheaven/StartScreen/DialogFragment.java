package com.blstream.stairwaytoheaven.StartScreen;

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
    public static final String USER_TIME = "Zdefiniuj wlasny czas...";
    public static final String DIALOG_TITLE = "Podaj czas w sekundach";
    private static DialogFragment dialogFragment;
    private Button okButton;
    private EditText editTextNumbers;
    private Fragment fragment;
    private ArrayAdapter<String> dataAdapter;
    private List<String> list;
    private boolean isValid;
    private long time;


    public static DialogFragment getInstance() {
        if (dialogFragment == null) {
            dialogFragment = new DialogFragment();

        }
        return dialogFragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_layout, container, false);
        getDialog().setTitle(DIALOG_TITLE);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton = (Button) view.findViewById(R.id.buttonOk);
        editTextNumbers = (EditText) view.findViewById(R.id.ediTextInputTime);
        editTextNumbers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isValid = !(s.toString().isEmpty())
                        && Integer.parseInt(s.toString()) > 0
                        && s.charAt(0) != '0';

            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    list.add(editTextNumbers.getText().toString() + " sekund");
                    updateData(list, dataAdapter);
                    time = Long.parseLong(editTextNumbers.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Blad walidacji!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
    }
}
