package com.example.marmm.demoFragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private EditText etdetailVar;
    private int passedNumber;
    private String passedData;
    public static final String UPDATEPOSITION = "position";
    public static final String UPDATEREMINDER = "reminder";

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail, container, false);


        etdetailVar = (EditText) view.findViewById(R.id.editText2);

       if (getArguments() != null) {
              String value = getArguments().getString(MainFragment.REMINDER);
           etdetailVar.setText(value);
       }



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();

            }
        });
    return view;
    }
}


