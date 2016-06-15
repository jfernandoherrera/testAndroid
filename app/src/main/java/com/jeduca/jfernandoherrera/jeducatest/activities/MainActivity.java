package com.jeduca.jfernandoherrera.jeducatest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.jeduca.jfernandoherrera.jeducatest.R;
import com.jeduca.jfernandoherrera.jeducatest.activities.fragments.TestFragment;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.User;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.UserAttributes;

public class MainActivity extends AppCompatActivity {

    private TextView userInfo;

    public static void goToMain(Context context, String usuario, String contrasena) {

        Intent intent = new Intent(context, MainActivity.class);

        intent.putExtra(UserAttributes.usuario, usuario);

        intent.putExtra(UserAttributes.contrasena, contrasena);

        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userInfo = (TextView) findViewById(R.id.userInfo);

        String info = getIntent().getExtras().getString(UserAttributes.usuario) + " " +

                getIntent().getExtras().getString(UserAttributes.contrasena);

        userInfo.setText(info);

    }


    public void showTestDialog(String type) {

        TestFragment testFragment = new TestFragment();

        String tag = "test";

        testFragment.setType(type);

        testFragment.show(getSupportFragmentManager(), tag);

    }

    public void onClickCEA(View view) {

        showTestDialog(TestAttributes.testCEA);

    }

    public void onClickTalleres(View view) {

        showTestDialog(TestAttributes.testTalleres);

    }

}
