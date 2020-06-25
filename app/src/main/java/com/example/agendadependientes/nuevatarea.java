package com.example.agendadependientes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class nuevatarea extends AppCompatActivity {

    private ImageButton boton;
    private ObjectAnimator animatorX;
    private EditText dia,mes,ano,nombretarea,descripcion;
    ArrayList<String> tarea,fecha,descrip;
    private Button boton3;
    Work work1;

    private  int dias,meses,anos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevatarea);
        boton = (ImageButton) findViewById(R.id.Button1);
        boton3 = (Button)findViewById(R.id.actu);
        dia = (EditText)findViewById(R.id.dia);
        mes = (EditText)findViewById(R.id.mes);
        ano = (EditText)findViewById(R.id.ano);
        descripcion = (EditText)findViewById(R.id.descripcion);
        nombretarea = (EditText)findViewById(R.id.NombreTarea);
        tarea = new ArrayList<String>();
        fecha = new ArrayList<String>();
        descrip = new ArrayList<String>();

    }






    public void volveratras(View view){

        animatorX = ObjectAnimator.ofFloat(boton, "x", -420f);
        animatorX.setDuration(1000);
        AnimatorSet animatorSetX = new AnimatorSet();
        animatorSetX.playTogether(animatorX);
        animatorSetX.start();

        onBackPressed();
    }


    public void guardar(View view){
        //Guardado de tarea

        String nombre = this.nombretarea.getText().toString();
        String descripcion1 = this.descripcion.getText().toString();
        String fecha = this.dia.getText().toString() + "/" + mes.getText().toString()+ "/" + ano.getText().toString();



        basededatos admin = new basededatos(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!nombre.isEmpty() && !descripcion1.isEmpty() && !fecha.isEmpty()){
            ContentValues registro = new ContentValues();

                registro.put("nombre", nombre);
                registro.put("fecha", fecha);
                registro.put("descripcion", descripcion1);
                db.insert("tareas", null, registro);
                db.close();

            Work work = new Work();
            work.nombre = nombre;
            work.fecha = fecha;
            work.desc = descripcion1;

            nombretarea.setText("");
            descripcion.setText("");
            dia.setText("");
            mes.setText("");
            ano.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();

            Intent pasar = new Intent(getApplicationContext(),todasmistareas.class);
            startActivity(pasar);
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

}
