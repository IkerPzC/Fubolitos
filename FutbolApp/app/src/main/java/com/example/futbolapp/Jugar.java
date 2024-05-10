package com.example.futbolapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Jugar extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    public static LinkedHashMap<String, String[]> mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        //Leer información de los equipos
        int resourceId = this.getResources().getIdentifier("info_equipos", "raw", getPackageName());
        mapa = leerFicheroEquipo(resourceId);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("estadisticas", Context.MODE_PRIVATE);

        // Verificar si es la primera vez que se inicia la aplicación
        if (!sharedPreferences.contains("Getafe_aciertos")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for(String s:mapa.keySet()){
                editor.putInt(s+"_aciertos",0);
                editor.putInt(s+"_contador",0);
            }
            editor.apply();
        }
    }

    // Método para manejar el clic del botón "primera"
    public void primera(View view) {
        // Crear un Intent para abrir la nueva actividad
        Intent intent = new Intent(this, ListaDivision.class);

        // Pasar el parámetro preguntas_primera_es a la nueva actividad
        int resourceId = R.raw.preguntas_primera_es;
        intent.putExtra("archivo", "preguntas_primera_es");

        // Iniciar la nueva actividad
        startActivity(intent);
    }

    // Método para manejar el clic del botón "segunda"
    public void segunda(View view) {
        // Crear un Intent para abrir la nueva actividad
        Intent intent = new Intent(this, ListaDivision.class);

        // Pasar el parámetro preguntas_segunda_es a la nueva actividad
        intent.putExtra("archivo", "preguntas_segunda_es");

        // Iniciar la nueva actividad
        startActivity(intent);
    }

    private LinkedHashMap<String, String[]> leerFicheroEquipo(int resourceId) {
        // Creando un nuevo LinkedHashMap llamado 'mapa' que mapea cadenas de texto a arreglos de cadenas de texto.
        LinkedHashMap<String, String[]> mapa = new LinkedHashMap<String, String[]>();

        try {
            // Obtener el InputStream del recurso raw
            InputStream inputStream = this.getResources().openRawResource(resourceId);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String linea = br.readLine();

            // Recorrer cada linea
            while (linea != null) {
                // Split para dividir la línea en partes utilizando la coma como delimitador
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    mapa.put(partes[0], partes);
                }

                linea = br.readLine();
            }

            br.close(); // Cerrar el BufferedReader después de su uso
        } catch (IOException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return mapa;
    }
}