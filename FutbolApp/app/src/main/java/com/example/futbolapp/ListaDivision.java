package com.example.futbolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListaDivision extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView imageCenterLogo;

    private RelativeLayout layoutItems;
    public static LinkedHashMap<String, ArrayList<String[]>> mapaEquipos;

    private String categoria = "primera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_division);

        // Obtener el valor del intent
        String archivo = getIntent().getStringExtra("archivo");

        // Leer el archivo correspondiente
        int resourceId = getResources().getIdentifier(archivo, "raw", getPackageName());
        mapaEquipos = leerFichero(resourceId);

        recyclerView = findViewById(R.id.recyclerView);

        //Imagen central
        imageCenterLogo = findViewById(R.id.imageCenterLogo);

        //Fondo
        layoutItems = findViewById(R.id.layoutItems);

        // Verificar el resourceId y establecer la imagen correspondiente
        if (resourceId == R.raw.preguntas_primera_es) {
            imageCenterLogo.setImageResource(R.drawable.logoprimera);
            layoutItems.setBackgroundColor(getResources().getColor(R.color.colorPrimeraDivision));
            categoria="primera";
        } else {
            imageCenterLogo.setImageResource(R.drawable.logosegunda);
            layoutItems.setBackgroundColor(getResources().getColor(R.color.colorSegundaDivision));
            categoria="segunda";
        }

        // Convierte el HashMap en una lista para el adaptador
        List<String> keyList = new ArrayList<>(mapaEquipos.keySet());

        // Configura el RecyclerView con un GridLayoutManager de dos columnas
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 2 == 0) ? 1 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        // Crea y establece el adaptador en el RecyclerView
        MyAdapter adapter = new MyAdapter(this, keyList, categoria);
        recyclerView.setAdapter(adapter);
    }

    private LinkedHashMap<String, ArrayList<String[]>> leerFichero(int resourceId) {
        LinkedHashMap<String, ArrayList<String[]>> mapa = new LinkedHashMap<String, ArrayList<String[]>>();

        try {
            // Obtener el InputStream del recurso raw
            InputStream inputStream = getResources().openRawResource(resourceId);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String linea = br.readLine();

            // Recorrer cada linea
            while (linea != null) {
                // Split
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    ArrayList<String[]> arrList = new ArrayList<String[]>();

                    // Buscar equipo y actualizar ArrayList
                    if (mapa.containsKey(partes[0])) {
                        arrList = mapa.get(partes[0]);
                        arrList.add(partes);
                    }

                    mapa.put(partes[0], arrList);
                }

                linea = br.readLine();
            }

            br.close(); // Cerrar el BufferedReader después de su uso
        } catch (IOException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return mapa;
    }

    public void volver(View view){
        finish();
    }

    public void volverMapa(View view){
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
}