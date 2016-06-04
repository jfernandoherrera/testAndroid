package com.jeduca.jfernandoherrera.jeducatest.model.context.local;


import android.content.Context;

import com.jeduca.jfernandoherrera.jeducatest.activities.LoginActivity;
import com.jeduca.jfernandoherrera.jeducatest.model.context.helper.DatabaseHelper;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.DatabaseAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.User;

public class LoginLocal {

    private UserLocal userLocal;

    public LoginLocal(UserLocal userLocal) {

        this.userLocal = userLocal;

    }


    public Boolean login(String usuario, String contrasena) {

        User user = new User(0, usuario, contrasena);

        boolean login = userLocal.exists(user);

        return login;

    }

}
