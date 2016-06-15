package com.jeduca.jfernandoherrera.jeducatest.model.context;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jeduca.jfernandoherrera.jeducatest.model.context.helper.DatabaseHelper;
import com.jeduca.jfernandoherrera.jeducatest.model.context.local.LoginLocal;
import com.jeduca.jfernandoherrera.jeducatest.model.context.local.UserLocal;
import com.jeduca.jfernandoherrera.jeducatest.model.context.remote.LoginRemote;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.DatabaseAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.User;

public class LoginContext {

    LoginRemote loginRemote;
    LoginLocal loginLocal;
    Context context;
    private DatabaseHelper databaseHelper;
    private UserLocal userLocal;

    public static LoginContext context(LoginContext loginContext, Context context) {

        if (loginContext == null) {

            loginContext = new LoginContext(context);

        }

        return loginContext;

    }

    public void open() {

        databaseHelper.open();

    }

    public LoginContext(Context context) {

        this.context = context;

        databaseHelper = new DatabaseHelper(context, DatabaseAttributes.name, null, DatabaseAttributes.databaseVersion);

        open();

        userLocal = new UserLocal(databaseHelper.getDatabase());

        loginLocal = new LoginLocal(userLocal);

        loginRemote =  new LoginRemote();

    }

    private void saveUser(String usuario, String contrasena) {

        User user = new User(0, usuario, contrasena);

        userLocal.insertUser(user);

    }

    public boolean login(String usuario, String contrasena) {

        boolean remote = false;

        boolean local = false;

        boolean isOnline = isOnline();

        local = loginLocal.login(usuario, contrasena);

        if(isOnline){

            remote = loginRemote.login(usuario, contrasena);

        }

        if(remote && !local) {

            saveUser(usuario, contrasena);
        }

        return remote || local;

    }

    public boolean isOnline() {

        ConnectivityManager cm =

                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

}
