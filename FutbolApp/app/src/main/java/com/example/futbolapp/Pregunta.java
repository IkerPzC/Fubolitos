package com.example.futbolapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pregunta {

    /* Variables */
    public String nombreEquipo, competicion, pregunta, respuestaCorrecta, opcion2, opcion3, opcion4;

    /* Constructor */
    public Pregunta(String nombreEquipo, String competicion, String pregunta, String respuestaCorrecta, String opcion2, String opcion3, String opcion4) {
        this.nombreEquipo=nombreEquipo;
        this.competicion=competicion;
        this.pregunta=pregunta;
        this.respuestaCorrecta=respuestaCorrecta;
        this.opcion2=opcion2;
        this.opcion3=opcion3;
        this.opcion4=opcion4;
    }

    /* to String */
    public String toString() {
        return pregunta;
    }

    /* Getters y Setters */
    public String getNombreEquipo(){
        return nombreEquipo;
    }

    public String getCompeticion(){
        return competicion;
    }

    public String getPregunta(){
        return pregunta;
    }

    public String getRespuestaCorrecta(){
        return respuestaCorrecta;
    }

    public String getOpcion2(){
        return opcion2;
    }

    public String getOpcion3(){
        return opcion3;
    }

    public String getOpcion4(){
        return opcion4;
    }

    public String[] generarPregunta() {
        //Creamos una lista con las opciones
        List<String> opciones = Arrays.asList(respuestaCorrecta, opcion2, opcion3, opcion4);

        //Barajamos la lista de manera aleatoria
        Collections.shuffle(opciones);

        //Convertimos la lista barajada a un array
        String[] opcionesAleatorias = opciones.toArray(new String[opciones.size()]);

        return opcionesAleatorias;
    }
}