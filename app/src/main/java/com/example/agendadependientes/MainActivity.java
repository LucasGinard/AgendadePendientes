package com.example.agendadependientes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator animatorX;
    private ObjectAnimator animatorY;
    private ObjectAnimator animatorAlpha;
    private ObjectAnimator animatorRotation;
    private ObjectAnimator animatorall;
    private Button newtarea;
    private Button pendientes;
    private ImageView imagen;
    private ConstraintLayout fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newtarea = findViewById(R.id.newtarea);
        pendientes = findViewById(R.id.pendientes);
        imagen = findViewById(R.id.imagenagenda);



        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    public void Pendientes(View view){
        //Pasar de activty
        Intent pasar = new Intent(this,todasmistareas.class);
        startActivity(pasar);


        //Animacion
        Animation animationscale = AnimationUtils.loadAnimation(this,R.anim.scale);
        pendientes.startAnimation(animationscale);
    }

    public void Nuevatarea(View view){

        //Animacion
        Animation animationscale = AnimationUtils.loadAnimation(this,R.anim.scale);
        newtarea.startAnimation(animationscale);

        //Pasar de activty
        Intent pasar = new Intent(this,nuevatarea.class);
        startActivity(pasar);

    }



}
