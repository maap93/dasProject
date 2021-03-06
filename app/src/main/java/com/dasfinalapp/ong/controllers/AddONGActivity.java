package com.dasfinalapp.ong.controllers;

/**
 * Created by Miguel Angel Arroyo Puerto.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dasfinalapp.ong.models.DBManager;
import com.dasfinalapp.ong.R;

/**
 *
 * The porpuse of this activity is basically that to create
 * a new record of an ONG and added it to the data base that
 * is created with SQLite.
 *
 */


public class AddONGActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText nameEditText;
    private EditText typeEditText;
    private EditText categoryEditText;
    private EditText objectiveEditText;


    private Spinner typeSpineer;

    private DBManager dbManager;

    /**
     *
     * This create the activity that is going to be display in the application
     * also create the instance of the view to be render when the user starts
     * the activity.
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add ONG Record");

        setContentView(R.layout.activity_add_record);

        // Assign the values of the texts.
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        typeEditText = (EditText) findViewById(R.id.type_edittext);
        categoryEditText = (EditText) findViewById(R.id.category_edittext);
        objectiveEditText = (EditText) findViewById(R.id.objective_edittext);
        typeSpineer = (Spinner) findViewById(R.id.spinner_type);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ong_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpineer.setAdapter(adapter);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    /**
     *
     * This function creates the functionality when you click on the add bottom to add a new record to the database
     * based on the view that is actually rendering and also is reciving the information
     * from the previous view.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = nameEditText.getText().toString();
                final String type = typeEditText.getText().toString();
                final String category = categoryEditText.getText().toString();
                final String objective = objectiveEditText.getText().toString();
                final String type_spiner = typeSpineer.getSelectedItem().toString();



                dbManager.insert(name, type_spiner,category, objective);

                Intent main = new Intent(AddONGActivity.this, ListONGActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}