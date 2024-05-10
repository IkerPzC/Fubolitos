package com.example.futbolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class PreguntasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        // Obtener la lista de arrays de String del Intent
        ArrayList<String[]> opcionesPreguntas = (ArrayList<String[]>) getIntent().getSerializableExtra("preguntasEquipo");
        String categoria = getIntent().getStringExtra("categoria");

        // Reemplazar el fragmento con las preguntas
        if (opcionesPreguntas != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, PreguntasFragment.newInstance(opcionesPreguntas,categoria))
                    .commit();
        }
    }
}
