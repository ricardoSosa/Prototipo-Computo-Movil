package com.example.riki.gameapp.Fragments;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.riki.gameapp.R;

/**
 * Created by RIKI on 12/10/2017.
 */

public class AllFragment extends SectionFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.generic_section, container, false);

        String sectionName = "Todos";
        setSection(sectionName, rootView, this.videogames);

        return rootView;
    }

}
