package com.example.agendadependientes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class descripciontarea extends AppCompatActivity {

    private ObjectAnimator animatorX;
    private ImageButton boton,trash;
    private TextView titulo,fecha1,descripcion1;
    private  String id;
    private FloatingActionButton botonedit;
    private SoundPool sp;
    private  int sonido_reproduccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciontarea);
        titulo = (TextView)findViewById(R.id.titulo_nombre);
        fecha1 = (TextView)findViewById(R.id.fecha_limite);
        descripcion1 = (TextView)findViewById(R.id.descripcion);
        botonedit = (FloatingActionButton)findViewById(R.id.editboton);
        id = getIntent().getExtras().getString("id_tarea");
        trash = (ImageButton)findViewById(R.id.trashbutton);
        llenardatos();

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_reproduccion = sp.load(this,R.raw.trash_sound_corted, 1);//


    }

    public void  llenardatos(){
        basededatos admin = new basededatos(this, "db", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor consulta = db.rawQuery("Select * " + "From tareas" + " where codigo = " + id + ";" ,null);

            while (consulta.moveToNext()) {
                String nombre = consulta.getString(consulta.getColumnIndex("nombre"));
                String fecha = consulta.getString(consulta.getColumnIndex("fecha"));
                String descripcion = consulta.getString(consulta.getColumnIndex("descripcion"));

                titulo.setText(nombre);
                fecha1.setText(fecha);
                descripcion1.setText(descripcion);
                consulta.close();
                db.close();
            }


            botonedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pasar = new Intent(descripciontarea.this,Edit_Tareas.class);
                    pasar.putExtra("id",id);
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
        onBackPressed();
    }

    public void  Eliminar_delete(View view){
        basededatos admin = new basededatos(this, "db", null, 1);
        admin.Deletework(id);
        admin.close();
        Intent pasar = new Intent(descripciontarea.this,todasmistareas.class);
        startActivity(pasar);

        //Sonido trash
        sp.play(sonido_reproduccion, 1,1,1,0,0);

        //animacion
        animatorX = ObjectAnimator.ofFloat(trash, "y", 180f);
        animatorX.setDuration(1000);
        AnimatorSet animatorSetX = new AnimatorSet();
        animatorSetX.playTogether(animatorX);
        animatorSetX.start();


    }
}
