package com.jeduca.jfernandoherrera.jeducatest.model.context.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.User;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.UserAttributes;

public class UserLocal {

    private final String tableName = "user";
    private SQLiteDatabase database;

    public UserLocal(SQLiteDatabase database) {

        this.database = database;

    }

    public boolean exists(User user) {

        boolean exists = false;

        String selectUser = "SELECT * FROM " + tableName + " WHERE usuario =? AND contrasena =?";

        Cursor cursor = database.rawQuery(selectUser,  new String[] {user.getUsuario(), user.getContrasena()});

        if(cursor != null && cursor.moveToFirst()) {

            exists = true;

        }

        return exists;

    }

    public long insertUser(User user) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(UserAttributes.usuario, user.getUsuario());

        contentValues.put(UserAttributes.contrasena, user.getContrasena());

        return database.insert(tableName, null, contentValues);

    }

}
