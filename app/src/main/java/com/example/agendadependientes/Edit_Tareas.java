package com.example.agendadependientes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Edit_Tareas extends AppCompatActivity {


    private ObjectAnimator animatorX;
    private ImageButton boton;
    private Button actulizar;
    private EditText dia,mes,ano,nombretarea,descripcion1;
    ArrayList<String> tarea,fecha,descrip;
    Work work1;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__tareas);
        boton = (ImageButton)findViewById(R.id.Button1);
        actulizar = (Button)findViewById(R.id.actu);
        boton = (ImageButton) findViewById(R.id.Button1);
        dia = (EditText)findViewById(R.id.dia);
        mes = (EditText)findViewById(R.id.mes);
        ano = (EditText)findViewById(R.id.ano);
        descripcion1 = (EditText)findViewById(R.id.descripcion);
        nombretarea = (EditText)findViewById(R.id.NombreTarea);
        tarea = new ArrayList<String>();
        fecha = new ArrayList<String>();
        descrip = new ArrayList<String>();
        id = getIntent().getExtras().getString("id");
        obtendatos();

    }


    public void obtendatos(){
        basededatos admin = new basededatos(this, "db", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor consulta = db.rawQuery("Select * " + "From tareas" + " where codigo = " + id + ";" ,null);

        while (consulta.moveToNext()) {
            String nombre = consulta.getString(consulta.getColumnIndex("nombre"));
            String fecha = consulta.getString(consulta.getColumnIndex("fecha"));
            String descripcion = consulta.getString(consulta.getColumnIndex("descripcion"));

            nombretarea.setText(nombre);
            descripcion1.setText(descripcion);
            dia.setText(fecha.substring(0,2));
            mes.setText(fecha.substring(3,5));
            ano.setText(fecha.substring(6));
            consulta.close();
            db.close();
        }
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


    public void Actulizar(View view){
        String fechaactulizada = this.dia.getText().toString() + "/" + mes.getText().toString()+ "/" + ano.getText().toString();
        basededatos admin = new basededatos(this, "db", null, 1);
        admin.updateworks(id , nombretarea.getText().toString(), fechaactulizada,descripcion1.getText().toString());
        Intent pasar = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(pasar);

    }
}
