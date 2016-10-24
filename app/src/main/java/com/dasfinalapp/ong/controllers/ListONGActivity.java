package com.dasfinalapp.ong.controllers;

/**
 * Created by Miguel Angel Arroyo Puerto.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dasfinalapp.ong.models.DBManager;
import com.dasfinalapp.ong.models.DatabaseHelper;
import com.dasfinalapp.ong.R;

public class ListONGActivity extends ActionBarActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.TYPE, DatabaseHelper.CATEGORY, DatabaseHelper.OBJECTIVE};

    final int[] to = new int[] { R.id.id, R.id.name, R.id.type, R.id.category, R.id.objective};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();

        // Add custom (id, ON_Name, ONG_Type, ONG_Category, ONG_Objective)
        dbManager.insertWithID(1,"Fundación Beca, A.C.", "Education", "Civil Association", "Give Scholarship");
        dbManager.insertWithID(2,"Grupo Ecológico Sierra Gorda, I.A.P.", "Natural Environment", "Private Assistance Institution", "Formación de una cultura que sustente el tránsito a la sustentabilidad, la protección y la restauración de la flora y fauna silvestre. Fortalecimiento integral de la riqueza natural de las comunidades, así como el desarrollo de destrezas para lograr su autosuficiencia.");
        dbManager.insertWithID(3,"Club de Leones de Querétaro, A.C.", "Health Care", "Civil Association", "Apoyo para personas que requieran lentes. Se le sapoya de forma gratuita cuando se trata de personas a partir de los 40 años de edad.");
        dbManager.insertWithID(4,"Nuevo Mundo en Educación Especial Querétaro, I.A.P.", "Special Needs", "Private Assistance Institution", "Educación integral al niño y adulto deficiente mental (intervención individual y familiar).");
        dbManager.insertWithID(5,"Andale para Oir Padres de Niños Sordos, A.C.", "Health Care", "Civil Association", "Asistencia, rehabilitación, atención especializada y orientación a niños con problemas auditivos, asesoria para padres y detección oportuna, apoyo en materia de audición a personas de escasos recursos.");
        dbManager.insertWithID(6,"Grupo Reto Querétaro, I.A.P.", "Health Care", "Private Assistance Institution", "Prevención, curación de cancer mamario, consultas y estudios médicos (solo a mujeres).");
        dbManager.insertWithID(7,"Fundación Merced Querétaro, A.C.", "Economic Development", "Civil Association", "Otorgar recursos para organizaciones y apoyarlas en su desarrollo.");
        dbManager.insertWithID(8,"México Tierra de Amaranto, A.C.", "Economic Development", "Civil Association", "testing");
        dbManager.insertWithID(9,"Bohemios de la Casa del Faldón, A.C.", "Culture", "Civil Association", "Promoción de la cultura.");
        dbManager.insertWithID(10,"Fundación Queretana Bebé Avance, A.C.", "Special Needs", "Civil Association", "Agrupar a personas físicas o morales con la finalidad de emprender acciones a favor de menores con discapacidad física y mental.");


        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));



        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();


        //ImageView img = (ImageView) this.findViewById(R.id.imageView);
        //img.setImageResource(R.drawable.shelter);

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.name);
                TextView descTextView = (TextView) view.findViewById(R.id.type);
                TextView testTextView = (TextView) view.findViewById(R.id.category);
                TextView objectiveTextView = (TextView) view.findViewById(R.id.objective);



                String id = idTextView.getText().toString();
                String name = titleTextView.getText().toString();
                String type = descTextView.getText().toString();
                String category = testTextView.getText().toString();
                String objective = objectiveTextView.getText().toString();

                System.out.println("Imprimo title: " + name);
                System.out.println("Imprimo des: " + type);
                System.out.println("Imprimo test: " + category);

                Intent modify_intent = new Intent(getApplicationContext(), ModifyONGActivity.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("type", type);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("category", category);
                modify_intent.putExtra("objective", objective);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddONGActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}