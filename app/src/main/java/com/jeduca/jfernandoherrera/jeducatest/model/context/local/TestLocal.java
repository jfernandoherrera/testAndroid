package com.jeduca.jfernandoherrera.jeducatest.model.context.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.Test;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestAttributes;

public class TestLocal {


    private final String tableName = "test";
    private SQLiteDatabase database;
    private final int completed = 100;

    public TestLocal(SQLiteDatabase database) {

        this.database = database;

    }

    public Test getTest(String type) {

        Test test = null;

        String selectTest = "SELECT * FROM " + tableName + " WHERE " + TestAttributes.type +" =? AND " + TestAttributes.percentage + " < " + completed;

        Cursor cursor = database.rawQuery(selectTest,  new String[] {type});

        if(cursor != null && cursor.moveToFirst()) {

           test = new Test(cursor.getInt(TestAttributes.idIndex),

                    cursor.getString(TestAttributes.typeIndex));

                    test.setPercentage(cursor.getInt(TestAttributes.percentageIndex));

                    test.setAccomplished(cursor.getInt(TestAttributes.accomplishedIndex));

                    test.setNotAccomplished(cursor.getInt(TestAttributes.notAccomplishedIndex));

                    test.setDontApply(cursor.getInt(TestAttributes.dontApplyIndex));

        }

        return test;

    }

    public long insertTest(Test test) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(TestAttributes.percentage, test.getPercentage());

        contentValues.put(TestAttributes.accomplished, test.getAccomplished());

        contentValues.put(TestAttributes.notAccomplished, test.getNotAccomplished());

        contentValues.put(TestAttributes.dontApply, test.getDontApply());

        contentValues.put(TestAttributes.type, test.getType());

        return database.insert(tableName, null, contentValues);

    }

    public void updateTest(Test test) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(TestAttributes.percentage, test.getPercentage());

        contentValues.put(TestAttributes.accomplished, test.getAccomplished());

        contentValues.put(TestAttributes.notAccomplished, test.getNotAccomplished());

        contentValues.put(TestAttributes.dontApply, test.getDontApply());

        contentValues.put(TestAttributes.type, test.getType());

        database.update(tableName, contentValues,"rowid="+ test.getId(), null);

    }

}
