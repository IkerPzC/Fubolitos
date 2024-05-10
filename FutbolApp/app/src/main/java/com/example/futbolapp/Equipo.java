package com.example.futbolapp;

public class Equipo {
    private int id;
    private String nombre;
    private int division;
    private String descripcion;
    private String escudo;
    private String latitud;
    private String longitud;
    private String imagenEstadio;

    private String nombreEstadio;

    // Constructor
    public Equipo(int id, String nombre, int division, String descripcion, String escudo, String latitud, String longitud, String imagenEstadio, String nombreEstadio) {
        this.id = id;
        this.nombre = nombre;
        this.division = division;
        this.descripcion = descripcion;
        this.escudo = escudo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagenEstadio = imagenEstadio;
        this.nombreEstadio = nombreEstadio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDivision() {
        return division;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEscudo() {
        return escudo;
    }

    public String getLatitud() {
        return latitud;
    }
    public String getLongitud() {
        return longitud;
    }

    public String getImagenEstadio() {
        return imagenEstadio;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setImagenEstadio(String estadio) {
        this.imagenEstadio = imagenEstadio;
    }

    public void setNombreEstadio(String estadio) {
        this.nombreEstadio = nombreEstadio;
    }

    // toString
    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", division=" + division +
                ", descripcion='" + descripcion + '\'' +
                ", escudo='" + escudo + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", imagenEstadio='" + imagenEstadio + '\'' +
                ", nombreEstadio='" + nombreEstadio + '\'' +
                '}';
    }
}