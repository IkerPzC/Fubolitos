package com.example.futbolapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class DetallesEquipo extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private String nombreEquipo;
    private Equipo equipo;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_equipo);

        // Inicializamos el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Hacer que no se pueda desplazar por el mapa
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    // Desactivamos para que el mapa donde sale el estadio se pueda desplazar
                    googleMap.getUiSettings().setAllGesturesEnabled(false);
                }
            });
        }

        // Creamos la instancia de SQLiteHelper
        sqLiteHelper = new SQLiteHelper(this, "Equipos", null, 1);

        // Recibir el valor del atributo "nombre" desde el Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nombre")) {
            nombreEquipo = intent.getStringExtra("nombre");

            // Buscamos el equipo seleccionado en la base de datos
            this.equipo = buscarEquipoPorNombre(nombreEquipo);

            // Dependiendo de la division del equipo cambiamos el color del fondo a Primera division o Segunda
            LinearLayout layoutPrincipal = findViewById(R.id.layoutPrincipal);
            if(equipo.getDivision()==1){
                int color = ContextCompat.getColor(this, R.color.colorPrimeraDivision);
                layoutPrincipal.setBackgroundColor(color);
            }else if(equipo.getDivision()==2){
                int color = ContextCompat.getColor(this, R.color.colorSegunda);
                layoutPrincipal.setBackgroundColor(color);
            }

            // Ahora ponemos el nombre del Equipo en el TextView
            TextView textViewNombre = findViewById(R.id.textViewNombre);
            textViewNombre.setText(equipo.getNombre());

            // Sacamos el escudo del equipo seleccionado y lo ponemos en el imageViewEscudo
            String imagen = equipo.getEscudo();
            int idImagen = getResources().getIdentifier(imagen, "drawable", getPackageName());
            ImageView imageView = findViewById(R.id.imageViewEscudo);
            imageView.setImageResource(idImagen);

            // Ahora ponemos la descripcion del equipo en el textViewDescripcion
            TextView textViewDescripcion = findViewById(R.id.textViewDescripcion);
            textViewDescripcion.setText(equipo.getDescripcion());

            // Ahora ponemos el nombre del estadio en el textViewNombreEstadio
            TextView textViewNombreEstadio = findViewById(R.id.textViewNombreEstadio);
            textViewNombreEstadio.setText(textViewNombreEstadio.getText()+equipo.getNombreEstadio());

            // Sacamos el escudo del equipo seleccionado y lo ponemos en el imageViewEscudo
            imagen = equipo.getImagenEstadio();
            idImagen = getResources().getIdentifier(imagen, "drawable", getPackageName());
            imageView = findViewById(R.id.imageViewEstadio);
            imageView.setImageResource(idImagen);
        }
    }

    private BitmapDescriptor bitmapFromVector(Context context, int vectorResId, int width, int height) {
        // Generador de drawable
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        if (vectorDrawable == null) {
            return null;
        }
        vectorDrawable.setBounds(0, 0, width, height);

        // Generador de bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Generador de lienzo
        Canvas canvas = new Canvas(bitmap);

        // Pasa el lienzo al drawable
        vectorDrawable.draw(canvas);

        // Retorna un BitmapDescriptor
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Desactivar la brújula
        mMap.getUiSettings().setCompassEnabled(false);

        // Añadimos un marcador en el centro de España
        LatLng centroEspaña = new LatLng(40.404240149893695, -3.2434315776129616);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(centroEspaña)      // Establecemos la ubicación del centro del mapa
                .zoom(5.3f)          // Establecemos el nivel de zoom
                .build();

        // Utiliza la posición de la cámara para actualizar el mapa
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Creamos el marcador
        // Sacamos sus coordenadas
        double latitud = Double.parseDouble(equipo.getLatitud());
        double longitud = Double.parseDouble(equipo.getLongitud());
        LatLng ubicacion = new LatLng(latitud, longitud);

        // Añadimos el marcador en la ubicacion del estadio del equipo
        LatLng ubicacionEstadio = new LatLng(latitud, longitud);

        // Creando una nueva posición de cámara centrada en la ubicación del estadio y con un nivel de zoom de 11.
        cameraPosition = new CameraPosition.Builder()
                .target(ubicacionEstadio)
                .zoom(11f)
                .build();

        // Moviendo la cámara del mapa a la nueva posición especificada.
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // obtenemos el escudo del equipo para ponerselo al marcador
        String escudo = equipo.getEscudo();
        int resID = getResources().getIdentifier(escudo, "drawable", getPackageName());

        // Añadiendo un marcador al mapa con la posición especificada, el nombre del estadio del equipo como título y un icono personalizado.
        mMap.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title(equipo.getNombreEstadio())
                .icon(bitmapFromVector(getApplicationContext(), resID, 100, 100)));

        // Estableciendo un listener para los clics en los marcadores del mapa.
        mMap.setOnMarkerClickListener(this);
    }

    private Equipo buscarEquipoPorNombre(String nombreBuscar) {
        // Obtiene una instancia de SQLiteDatabase para lectura desde la base de datos utilizando el helper de SQLite.
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        // Generamos un equipo
        Equipo equipo = null;
        // Generamos la consulta para buscar por nombre
        String[] selectionArgs = { nombreBuscar };
        Cursor c = db.rawQuery("SELECT * FROM Equipo WHERE NOMBRE = ?", selectionArgs);

        //Nos aseguramos de que existe el primer registro
        if (c.moveToFirst())
        {
            do
            {
                int id = c.getInt(0);
                String nombre = c.getString(1);
                int division = c.getInt(2);
                String descripcion = c.getString(3);
                String latitud = c.getString(4);
                String longitud = c.getString(5);
                String escudo = c.getString(6);
                String nombreEstadio = c.getString(7);
                String imagenEstadio = c.getString(8);

                // Creamos el equipo con los datos cargados
                equipo = new Equipo(id, nombre, division, descripcion, latitud, longitud, escudo, imagenEstadio, nombreEstadio);

                // Devolvemos el equipo
                return equipo;
            }
            while(c.moveToNext());
        }
        // Cerramos el cursos y la base de datos
        c.close();
        db.close();
        return equipo;
    }

    // Metodo que cierra la ventana y vuelve a la anterior
    public void volver(View view){
        finish();
    }

    // Metodo que vuelve a la ventana del Mapa
    public void volverMapa(View view){
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}