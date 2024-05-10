package com.example.futbolapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> keyList;
    private Context context;

    private String categoria;

    private SharedPreferences sharedPreferences;

    public static LinkedHashMap<String, String[]> mapaInfoEquipos;

    public MyAdapter(Context context, List<String> keyList, String categoria) {
        this.keyList = keyList;
        this.context = context;
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Obtener las referencias
        LinearLayout itemLayout = holder.itemView.findViewById(R.id.itemLayout);
        TextView textBottom = holder.itemView.findViewById(R.id.textBottom);

        //Configura los elementos en cada fila
        int startPosition = position;

        //Leer información de los equipos
        int resourceId = this.context.getResources().getIdentifier("info_equipos", "raw", holder.itemView.getContext().getPackageName());
        mapaInfoEquipos = leerFicheroEquipo(resourceId);

        //Obtener el nombre del equipo, imagen, item, item_nombre, color del texto
        String teamName = keyList.get(startPosition);
        String imageName = (mapaInfoEquipos.get(teamName)[1]).toString();
        String itemName = (mapaInfoEquipos.get(teamName)[2]).toString();
        String itemNombreName = (mapaInfoEquipos.get(teamName)[3]).toString();
        String colorSecundario = (mapaInfoEquipos.get(teamName)[4]).toString();

        //Cambiar el fondo
        int teamDrawableId = holder.itemView.getContext().getResources().getIdentifier(itemName, "drawable", holder.itemView.getContext().getPackageName());
        itemLayout.setBackgroundResource(teamDrawableId);

        //Cambiar el color del texto
        int colorNombre = holder.itemView.getContext().getResources().getIdentifier(itemNombreName, "drawable", holder.itemView.getContext().getPackageName());
        textBottom.setBackgroundResource(colorNombre);

        //Verificar si el nombre de la imagen no es nulo y no está vacío
        if (imageName != null && !imageName.isEmpty()) {

            //Obtener id de la imagen y fondo
            int imageResourceId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());
            int colorResId = holder.itemView.getResources().getIdentifier(colorSecundario, "color", holder.itemView.getContext().getPackageName());
            int color = holder.itemView.getResources().getColor(colorResId);

            //Establecer imagen, fondo y color de fondo
            holder.imageCenter.setImageResource(imageResourceId);
            holder.textBottom.setText(teamName);
            holder.textBottom.setTextColor(color);

            // Clic del elemento
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ArrayList<String[]> opcionesPreguntas = ListaDivision.mapaEquipos.get(String.valueOf(teamName));

                    //Crear un Intent
                    Intent intent = new Intent(view.getContext(), PreguntasActivity.class);
                    intent.putExtra("preguntasEquipo", opcionesPreguntas);
                    intent.putExtra("categoria", categoria);

                    // Iniciar la nueva actividad
                    view.getContext().startActivity(intent);
                }
            });

            //Debug Preguntas
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // Inicializar SharedPreferences
                    sharedPreferences = context.getSharedPreferences("estadisticas", Context.MODE_PRIVATE);

                    String aciertos = teamName+"_aciertos";
                    String contador = teamName+"_contador";

                    // Inicializando variables para almacenar el total de aciertos y el total del contador del equipo.
                    int totalAciertos = 0;
                    int totalContador = 0;

                    // Iterando a través de todas las entradas en SharedPreferences.
                    for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                        String key = entry.getKey();

                        // Comprobando si la clave es igual al contador o a los aciertos del equipo.
                        if (key.equals(contador)) {
                            totalContador = (int) entry.getValue();
                        } else if (key.equals(aciertos)) {
                            totalAciertos = (int) entry.getValue();
                        }
                    }

                    // Calculando la media de aciertos si el contador total es distinto de cero para evitar división por cero.
                    double media = 0;
                    if(totalContador!=0){
                        media = totalAciertos*100/totalContador;
                    }

                    // Crear el diálogo
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogStyle);
                    builder.setTitle("Estadísticas del "+teamName);
                    builder.setMessage(totalAciertos+"/"+totalContador+" ("+media+"%)");

                    builder.show();

                    return true;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        // Calcula el número total de filas
        return (int) Math.ceil(keyList.size() / 1.0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCenter;
        TextView textBottom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCenter = itemView.findViewById(R.id.imageCenter);
            textBottom = itemView.findViewById(R.id.textBottom);
        }
    }

    //Leer info de cada equipo
    private LinkedHashMap<String, String[]> leerFicheroEquipo(int resourceId) {
        LinkedHashMap<String, String[]> mapa = new LinkedHashMap<String, String[]>();

        try {
            // Obtener el InputStream del recurso raw
            InputStream inputStream = this.context.getResources().openRawResource(resourceId);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String linea = br.readLine();

            // Recorrer cada linea
            while (linea != null) {
                // Split
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