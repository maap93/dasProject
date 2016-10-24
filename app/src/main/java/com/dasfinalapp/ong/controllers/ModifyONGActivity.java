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
import android.widget.ImageView;
import android.widget.Spinner;

import com.dasfinalapp.ong.models.DBManager;
import com.dasfinalapp.ong.R;

public class ModifyONGActivity extends Activity implements OnClickListener {


    private Button updateBtn, deleteBtn;

    // Modified Fields
    private EditText nameText;
    private EditText typeText;
    private EditText categoryText;
    private EditText objectiveText;


    private Spinner typeSpineer;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add ONG Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        nameText = (EditText) findViewById(R.id.name_edittext);
        typeText = (EditText) findViewById(R.id.type_edittext);
        categoryText = (EditText) findViewById(R.id.category_edittext);
        objectiveText = (EditText) findViewById(R.id.objective_edittext);

        typeSpineer = (Spinner) findViewById(R.id.spinner_type);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ong_type, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpineer.setAdapter(adapter);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String category = intent.getStringExtra("category");
        String objective = intent.getStringExtra("objective");

        _id = Long.parseLong(id);

        nameText.setText(name);
        typeText.setText(type);
        categoryText.setText(category);
        objectiveText.setText(objective);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        // Dont Delete records from 1 to 10
        if(_id == 1 || _id == 2 || _id == 3 )
        {
            deleteBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = nameText.getText().toString();
                String type = typeText.getText().toString();
                String category = categoryText.getText().toString();
                String objective = objectiveText.getText().toString();
                String spinner_type = typeSpineer.getSelectedItem().toString();

                dbManager.update(_id, name, spinner_type,category, objective);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListONGActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
