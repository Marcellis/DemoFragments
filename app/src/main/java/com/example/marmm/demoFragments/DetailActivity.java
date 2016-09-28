package com.example.marmm.demoFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    private EditText etdetailVar;
    private int passedNumber;
    private String passedData;
    public static final String UPDATEPOSITION = "position";
    public static final String UPDATEREMINDER = "reminder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etdetailVar = (EditText) findViewById(R.id.editText2);


        //Obtain the parameters provided by MainActivity

        passedData = getIntent().getStringExtra(MainActivity.REMINDER);
        //second argument defaultValue int: the value to be returned if no value of the desired type is stored with the given name.
        passedNumber = getIntent().getIntExtra(MainActivity.POSITION, -1);

        etdetailVar.setText(passedData);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Read the input field data, possibly modified by the end user
                String changedParameter = etdetailVar.getText().toString();

                //Prepare the return parameters
                Intent returnIntent = new Intent();
                returnIntent.putExtra(UPDATEPOSITION, passedNumber);
                returnIntent.putExtra(UPDATEREMINDER, changedParameter);
                setResult(Activity.RESULT_OK, returnIntent);
                //Terminate this activity and return to the calling activity
                finish();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
