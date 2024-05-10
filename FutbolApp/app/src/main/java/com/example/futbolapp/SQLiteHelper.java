package com.example.futbolapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper
{
    private final String sqlCreateElemento = "CREATE TABLE Equipo (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "division INTEGER, " +
            "descripcion TEXT, " +
            "escudo TEXT, " +
            "latitud TEXT, " +
            "longitud TEXT, " +
            "nombreEstadio TEXT, " +
            "imagenEstadio TEXT)";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreateElemento);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Equipo");

        //Se crea la nueva version de la tabla
        onCreate(db);
    }
}
