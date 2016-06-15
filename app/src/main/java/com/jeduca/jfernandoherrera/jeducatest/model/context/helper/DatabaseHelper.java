package com.jeduca.jfernandoherrera.jeducatest.model.context.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.DatabaseAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.UserAttributes;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String userTableCreate = "CREATE TABLE " + DatabaseAttributes.userTableName +

            " (id integer primary key autoincrement, " + UserAttributes.usuario + " text, " + UserAttributes.contrasena + " text);";

    private final String testTableCreate = "CREATE TABLE " + DatabaseAttributes.testTableName +

            " (id integer primary key autoincrement, " + TestAttributes.percentage + " integer, " +

            TestAttributes.accomplished + " integer, " + TestAttributes.notAccomplished + " integer, " +

            TestAttributes.dontApply + " integer, " + TestAttributes.type + " text );";

    private final String dbUserUpdgrade = "DROP TABLE IF EXISTS " + DatabaseAttributes.userTableName;

    private final String dbTestUpdgrade = "DROP TABLE IF EXISTS " + DatabaseAttributes.testTableName;

    private SQLiteDatabase database;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    public SQLiteDatabase getDatabase() {

        return database;

    }

    public void open() {

        database = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(testTableCreate);

        db.execSQL(userTableCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(dbTestUpdgrade);

        db.execSQL(dbUserUpdgrade);

    }

}
