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
import android.widget.Button;
import android.widget.ImageView;
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

public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private SQLiteHelper sqLiteHelper;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtener el SupportMapFragment y ser notificado cuando el mapa esté listo para ser utilizado.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sqLiteHelper = new SQLiteHelper(this, "Equipos", null, 1);

        // Encuentra los botones por sus ID
        ImageView botonPrimeraDivision = findViewById(R.id.botonPrimeraDivision);
        ImageView botonSegundaDivision = findViewById(R.id.botonSegundaDivision);
        Button botonJugar = findViewById(R.id.botonJugar);

        // Asigna el evento de clic al boton para Jugar
        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Maneja la acción cuando se hace clic en el primer botón
                Intent intent = new Intent(Mapa.this, Jugar.class);
                startActivity(intent);
            }
        });
        // Asigna el evento de clic al boton para mostrar los equipos de Segunda division
        botonPrimeraDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Maneja la acción cuando se hace clic en el primer botón
                crearMarcadores(1);
            }
        });
        // Asigna el evento de clic al boton para mostrar los equipos de Primera division
        botonSegundaDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Maneja la acción cuando se hace clic en el segundo botón
                crearMarcadores(2);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Desactivar la brújula
        mMap.getUiSettings().setCompassEnabled(false);

        // Creamos un marcador en el centro de España
        LatLng centroEspaña = new LatLng(40.404240149893695, -3.2434315776129616);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(centroEspaña)      // Establece la ubicación del centro del mapa
                .zoom(5.3f)          // Establece el nivel de zoom
                .build();

        // Utiliza la posición de la cámara para actualizar el mapa
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void crearMarcadores(int division){
        // Limpiamos el mapa de marcadores
        mMap.clear();

        List<Equipo> listaEquipos = cargarEquiposPorDivision(division);

        for (Equipo e : listaEquipos) {
            // Sacamos sus coordenadas
            double latitud = Double.parseDouble(e.getLatitud());
            double longitud = Double.parseDouble(e.getLongitud());
            LatLng ubicacion = new LatLng(latitud, longitud);

            // obtenemos el escudo del equipo
            String escudo = e.getEscudo();
            // Obtenemos el identificador del recurso drawable para el escudo del equipo
            int resID = getResources().getIdentifier(escudo, "drawable", getPackageName());

            // Añadimos el marcador
            mMap.addMarker(new MarkerOptions()
                    .position(ubicacion)
                    .title(e.getNombre())
                    .icon(bitmapFromVector(getApplicationContext(), resID, 100, 100)));

            mMap.setOnMarkerClickListener(this);
        }

    }

    private List<Equipo> cargarEquiposPorDivision(int divisionSeleccionada) {
        // Creamos la lista donde iremos guardando los equipos
        ArrayList<Equipo> listaEquipos = new ArrayList<>();

        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        // Generamos la consulta para buscar por division
        String[] selectionArgs = { divisionSeleccionada + "" };
        Cursor c = db.rawQuery("SELECT * FROM Equipo WHERE DIVISION = ?", selectionArgs);

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

                // Creamos el equipo con los datos devueltos
                Equipo equipo = new Equipo(id, nombre, division, descripcion, latitud, longitud, escudo, imagenEstadio, nombreEstadio);

                // Añadimos el equipo a la lista
                listaEquipos.add(equipo);
            }
            while(c.moveToNext());
        }
        c.close();
        db.close();
        return listaEquipos;
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
    public boolean onMarkerClick(@NonNull Marker marker) {
        // Aquí puedes responder al clic en el marcador
        String nombreActividad = marker.getTitle();

        // Creamos el intent
        Intent intent = new Intent(this, DetallesEquipo.class);
        intent.putExtra("nombre", nombreActividad);

        startActivity(intent);
        return true;
    }
}