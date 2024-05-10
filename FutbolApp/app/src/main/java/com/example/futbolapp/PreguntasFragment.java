package com.example.futbolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PreguntasFragment extends Fragment {

    private ArrayList<String[]> opcionesPreguntas;

    public PreguntasFragment() {

    }

    // Método estático para crear una nueva instancia de PreguntasFragment con argumentos proporcionados.
    public static PreguntasFragment newInstance(ArrayList<String[]> opcionesPreguntas, String categoria) {
        PreguntasFragment fragment = new PreguntasFragment();
        // Crear un Bundle para almacenar los argumentos.
        Bundle args = new Bundle();
        args.putSerializable("opcionesPreguntas", opcionesPreguntas);
        args.putString("categoria",categoria);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preguntas, container, false);

        // Obtener la lista de opciones de preguntas del argumento
        if (getArguments() != null) {
            opcionesPreguntas = (ArrayList<String[]>) getArguments().getSerializable("opcionesPreguntas");

            //Cambiar fondo de color
            RelativeLayout layoutItems2 = view.findViewById(R.id.layoutItems2);
            String categoria = getArguments().getString("categoria");
            if(categoria.equals("primera")){
                layoutItems2.setBackgroundColor(getResources().getColor(R.color.colorPrimeraDivision));
            }else if(categoria.equals("segunda")){
                layoutItems2.setBackgroundColor(getResources().getColor(R.color.colorSegundaDivision));
            }
        }

        // Configurar el RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPreguntas);
        PreguntaAdapter adapter = new PreguntaAdapter(opcionesPreguntas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Configurando un OnClickListener para el ImageView 'imageViewVolver' para cerrar la actividad actual cuando se hace clic.
        ImageView imageViewVolver = view.findViewById(R.id.volver);
        imageViewVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificando si getActivity() no es nulo antes de llamar a finish() para evitar errores.
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });

        // Configurando un OnClickListener para el ImageView 'imageViewVolverMapa' para iniciar la actividad del mapa cuando se hace clic.
        ImageView imageViewVolverMapa = view.findViewById(R.id.volverMapa);
        imageViewVolverMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificando si getActivity() no es nulo antes de iniciar una nueva actividad para evitar errores.
                if (getActivity() != null) {
                    // Creando un intent para iniciar la actividad del mapa.
                    Intent intent = new Intent(getActivity(), Mapa.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}