package com.example.agendadependientes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class basededatos extends SQLiteOpenHelper {
    public basededatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tareas(codigo  INTEGER primary key autoincrement, nombre TEXT not null, fecha text not null, descripcion TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateworks(String id, String nombretarea, String fechaactulizada ,String descripcion1 ){
        getReadableDatabase().execSQL("Update tareas Set nombre = '"+nombretarea+"', fecha = '"+fechaactulizada+"', descripcion = '"+descripcion1+"' where codigo = "+id+";");
    }

    public void Deletework(String id){
        getReadableDatabase().execSQL("Delete from tareas   where codigo = "+id+";");
    }

}
