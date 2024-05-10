package com.example.futbolapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PreguntaAdapter extends RecyclerView.Adapter<PreguntaAdapter.ViewHolder> {

    private int contador = 0;
    private int aciertos = 0;
    private List<String[]> opcionesPreguntas;

    private SharedPreferences sharedPreferences;

    public PreguntaAdapter(List<String[]> opcionesPreguntas) {
        this.opcionesPreguntas = obtenerPreguntasAleatorias(opcionesPreguntas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] opcionesAleatorias = opcionesPreguntas.get(position);

        // Configurar los elementos en cada fila
        holder.textCategoria.setText(opcionesAleatorias[1].toUpperCase());
        holder.textPregunta.setText(opcionesAleatorias[2]);

        // Reorganizar aleatoriamente las opciones de respuesta
        List<String> opcionesDesordenadas = Arrays.asList(opcionesAleatorias[3], opcionesAleatorias[4], opcionesAleatorias[5], opcionesAleatorias[6]);
        Collections.shuffle(opcionesDesordenadas);

        // Asignar las opciones desordenadas a los botones
        holder.buttonOpcion1.setText(opcionesDesordenadas.get(0));
        holder.buttonOpcion2.setText(opcionesDesordenadas.get(1));
        holder.buttonOpcion3.setText(opcionesDesordenadas.get(2));
        holder.buttonOpcion4.setText(opcionesDesordenadas.get(3));

        // Establecer OnClickListener para cada botón
        holder.buttonOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarOpcion(holder.buttonOpcion1, opcionesAleatorias[3], view.getContext(),
                        holder.buttonOpcion1, holder.buttonOpcion2, holder.buttonOpcion3, holder.buttonOpcion4);
            }
        });

        holder.buttonOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarOpcion(holder.buttonOpcion2, opcionesAleatorias[3], view.getContext(),
                        holder.buttonOpcion1, holder.buttonOpcion2, holder.buttonOpcion3, holder.buttonOpcion4);
            }
        });

        holder.buttonOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarOpcion(holder.buttonOpcion3, opcionesAleatorias[3], view.getContext(),
                        holder.buttonOpcion1, holder.buttonOpcion2, holder.buttonOpcion3, holder.buttonOpcion4);
            }
        });

        holder.buttonOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarOpcion(holder.buttonOpcion4, opcionesAleatorias[3], view.getContext(),
                        holder.buttonOpcion1, holder.buttonOpcion2, holder.buttonOpcion3, holder.buttonOpcion4);
            }
        });

        //Establecer decoración
        if (opcionesAleatorias[1].equals("Club")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_club, R.color.colorClubBorde);
        } else if (opcionesAleatorias[1].equals("Primera División")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_primera, R.color.colorPrimeraDivisionBorde);
        } else if (opcionesAleatorias[1].equals("Segunda División")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_segunda, R.color.colorSegundaDivisionBorde);
        } else if (opcionesAleatorias[1].equals("Copa del Rey")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_copa, R.color.colorCopaBorde);
        } else if (opcionesAleatorias[1].equals("Europa League")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_europaleague, R.color.colorEuropaLeagueBorde);
        } else if (opcionesAleatorias[1].equals("Champions League")) {
            configurarDecoracionPregunta(holder, opcionesAleatorias[1], R.drawable.pregunta_champions, R.color.colorChampionsLeagueBorde);
        }
    }

    private void seleccionarOpcion(Button boton, String respuestaCorrecta, Context context, Button... botones) {

        //Aumentar contador de preguntas realizadas
        contador++;

        //Deshabilitar todos los botones y ponerles el fondo blanco y texto negro
        for (Button btn : botones) {
            btn.setEnabled(false);
            btn.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            btn.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        //Poner el botón seleccionado en verde (acierto) o rojo (fallo)
        if (boton.getText().toString().trim().equals(respuestaCorrecta.trim())) {
            boton.setBackgroundColor(ContextCompat.getColor(context, R.color.verde));
            boton.setTextColor(ContextCompat.getColor(context, R.color.white));
            aciertos++;
        } else {
            boton.setBackgroundColor(ContextCompat.getColor(context, R.color.rojo));
            boton.setTextColor(ContextCompat.getColor(context, R.color.white));
        }

        if(contador == 5){
            sharedPreferences = context.getSharedPreferences("estadisticas", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String nomAciertos = opcionesPreguntas.get(0)[0].trim() + "_aciertos".trim();
            String nomContador = opcionesPreguntas.get(0)[0].trim() + "_contador".trim();

            int totalAciertos = 0;
            int totalContador = 0;

            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();

                if (key.equals(nomContador)) {
                    totalContador = (int) entry.getValue();
                    editor.putInt(key, (contador + totalContador));
                } else if (key.equals(nomAciertos)) {
                    totalAciertos = (int) entry.getValue();
                    editor.putInt(key, (aciertos + totalAciertos));
                }
            }

            editor.apply();

            //DEBUG
            Log.d("SharedPreferences", "Clave Aciertos: " + nomAciertos);
            Log.d("SharedPreferences", "Clave Contador: " + nomContador);
            Log.d("SharedPreferences", "Aciertos: " + aciertos);
            Log.d("SharedPreferences", "Contador: " + contador);
            Log.d("SharedPreferences", "Total Aciertos: " + totalAciertos);
            Log.d("SharedPreferences", "Total Contador: " + totalContador);
            Log.d("SharedPreferences", "Suma Aciertos: " + (totalAciertos+aciertos));
            Log.d("SharedPreferences", "Suma Contador: " + (totalContador+contador));

            //Mostrar aciertos
            Toast.makeText(context, "Has acertado "+aciertos+"/"+contador, Toast.LENGTH_SHORT).show();

            //Redirigir a Jugar
            Intent intent = new Intent(context, Jugar.class);
            context.startActivity(intent);
        }
    }


    @Override
    public int getItemCount() {
        return opcionesPreguntas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCategoria, textPregunta;
        LinearLayout cartaPregunta;
        Button buttonOpcion1, buttonOpcion2, buttonOpcion3, buttonOpcion4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartaPregunta = itemView.findViewById(R.id.cartaPregunta);
            textCategoria = itemView.findViewById(R.id.textCategoria);
            textPregunta = itemView.findViewById(R.id.textPregunta);
            buttonOpcion1 = itemView.findViewById(R.id.buttonOpcion1);
            buttonOpcion2 = itemView.findViewById(R.id.buttonOpcion2);
            buttonOpcion3 = itemView.findViewById(R.id.buttonOpcion3);
            buttonOpcion4 = itemView.findViewById(R.id.buttonOpcion4);
        }
    }

    private List<String[]> obtenerPreguntasAleatorias(List<String[]> listaPreguntas) {
        // Asegúrate de que hay al menos 5 preguntas disponibles
        if (listaPreguntas.size() <= 5) {
            return listaPreguntas;
        }

        // Mezcla aleatoriamente la lista de preguntas
        Collections.shuffle(listaPreguntas, new Random(System.currentTimeMillis()));

        // Devuelve solo las primeras 5 preguntas
        return listaPreguntas.subList(0, 5);
    }

    private void configurarDecoracionPregunta(ViewHolder holder, String categoria, int fondoDrawable, int colorBorde) {
        holder.cartaPregunta.setBackgroundResource(fondoDrawable);
        holder.textCategoria.setBackgroundResource(fondoDrawable);
        holder.textCategoria.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), colorBorde));
        holder.textPregunta.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), colorBorde));
    }
}