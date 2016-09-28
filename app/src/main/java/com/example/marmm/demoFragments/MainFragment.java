package com.example.marmm.demoFragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText editText;
    private ReminderAdapter adapter;
    public static final String POSITION = "position";
    public static final String REMINDER = "reminder";
    public static final int  REQUESTCODE = 2;
    private DBhelper dbHelper;
    private SQLiteDatabase database;
    private Cursor cursor;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBhelper(this);
        database = dbHelper.getWritableDatabase();


        recyclerView = (RecyclerView) findViewById(R.id.idReclycerView);
        editText = (EditText) findViewById(R.id.editText);




        // recycler view


        // LinearLayoutManager mLayoutManager
        //              = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        getAllReminders();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(DBhelper.REMINDER_NAME, editText.getText().toString());
                database.insert(DBhelper.TABLE_REMINDERS, null, values);


                getAllReminders();

            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }


    public void getAllReminders() {


        cursor = database.query(DBhelper.TABLE_REMINDERS, new String[]{DBhelper.REMINDER_ID, DBhelper.REMINDER_NAME}, null, null, null, null, null);


        adapter = new ReminderAdapter(cursor);
        recyclerView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
