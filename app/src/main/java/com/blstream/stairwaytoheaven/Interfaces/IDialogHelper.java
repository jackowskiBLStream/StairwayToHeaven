package com.blstream.stairwaytoheaven.Interfaces;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Damian on 2016-03-21.
 */
public interface IDialogHelper {
    void onListen(List<String> list, ArrayAdapter<String> dataAdapter);
}
