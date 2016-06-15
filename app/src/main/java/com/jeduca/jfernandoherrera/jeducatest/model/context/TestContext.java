package com.jeduca.jfernandoherrera.jeducatest.model.context;

import android.content.Context;

import com.jeduca.jfernandoherrera.jeducatest.model.context.helper.DatabaseHelper;
import com.jeduca.jfernandoherrera.jeducatest.model.context.local.TestLocal;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.DatabaseAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.Test;

public class TestContext {

    private TestLocal testLocal;
    private DatabaseHelper databaseHelper;

    public static TestContext context(TestContext testContext, Context context) {

        if (testContext == null) {

            testContext = new TestContext(context);

        }

        return testContext;

    }

    public TestContext(Context context) {

        databaseHelper = new DatabaseHelper(context, DatabaseAttributes.name, null, DatabaseAttributes.databaseVersion);

        open();

        testLocal = new TestLocal(databaseHelper.getDatabase());

    }

    public void open() {

        databaseHelper.open();

    }

    public long insertTest(Test test) {

        return testLocal.insertTest(test);

    }

    public void updateTest(Test test) {

        testLocal.updateTest(test);

    }

    public Test getInitiatedTest(String type) {

      return testLocal.getTest(type);

    }

}
