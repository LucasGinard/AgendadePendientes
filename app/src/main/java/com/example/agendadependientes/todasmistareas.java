package com.example.agendadependientes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class todasmistareas extends AppCompatActivity {



    private ListView lista;
    private ArrayAdapter<String> adapter;
    private ImageButton boton;
    private ObjectAnimator animatorX;
    private ArrayList<String> nombre_tareas;
    private FloatingActionButton botonvolador;
    private Map<String, Integer> mapadetareas;
    private  ArrayList<Work> tareasBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todasmistareas);
        lista = (ListView)findViewById(R.id.lista_tareas);
        boton = (ImageButton)findViewById(R.id.Button1);
        botonvolador = (FloatingActionButton)findViewById(R.id.editboton);



        //boton floating
        botonvolador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasar = new Intent(getApplicationContext(),nuevatarea.class);
                startActivity(pasar);
                Animation animationscale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
                botonvolador.startAnimation(animationscale);
                finish();
            }
        });
        mapadetareas = new HashMap<String, Integer>();
        tareasBD = new ArrayList<Work>();
        tareasBD = obtendatos();
        nombre_tareas = new ArrayList<String>();
        llenararreglo();
        llenarmapa();

       adapter  = new ArrayAdapter<String>(this,R.layout.lista_config,nombre_tareas);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = adapter.getItem(position);
                mapadetareas.get(nombre);

                Intent pasar = new Intent(todasmistareas.this,descripciontarea.class);
                pasar.putExtra("id_tarea",mapadetareas.get(nombre).toString());
                startActivity(pasar);

            }
        });


    }

    public void volveratras(View view){
        animatorX = ObjectAnimator.ofFloat(boton, "x", -420f);
        animatorX.setDuration(1000);
        AnimatorSet animatorSetX = new AnimatorSet();
        animatorSetX.playTogether(animatorX);
        animatorSetX.start();
        Intent pasar = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(pasar);
    }

    public ArrayList<Work> obtendatos(){
        ArrayList<Work> datos = new ArrayList<Work>();
        int id;
        basededatos admin = new basededatos(this, "db", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor consulta = db.rawQuery("SELECT * FROM tareas",null);

            while (consulta.moveToNext()){
                String nombre = consulta.getString(consulta.getColumnIndex("nombre"));
                String fecha = consulta.getString(consulta.getColumnIndex("fecha"));
                String descripcion = consulta.getString(consulta.getColumnIndex("descripcion"));
                id = consulta.getInt(consulta.getColumnIndex("codigo"));

                Work work = new Work(id,nombre,fecha,descripcion);

                datos.add(work);
            };
        consulta.close();
        db.close();
        return datos;
    }

    public void llenararreglo(){
        for (int i = 0; i<tareasBD.size(); i++){
            nombre_tareas.add(tareasBD.get(i).getNombre());
            }
    }


    public void llenarmapa(){
        for (int i = 0; i<tareasBD.size(); i++){
            mapadetareas.put(tareasBD.get(i).getNombre(), tareasBD.get(i).id_work);
        }
    }
}






