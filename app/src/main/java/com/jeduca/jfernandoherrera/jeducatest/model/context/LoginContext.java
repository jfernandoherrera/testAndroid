package com.jeduca.jfernandoherrera.jeducatest.model.context;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jeduca.jfernandoherrera.jeducatest.model.context.local.LoginLocal;
import com.jeduca.jfernandoherrera.jeducatest.model.context.remote.LoginRemote;

public class LoginContext {

    LoginRemote loginRemote;
    LoginLocal loginLocal;
    Context context;

    public static LoginContext context(LoginContext loginContext, Context context) {

        if (loginContext == null) {

            loginContext = new LoginContext(context);

        }

        return loginContext;

    }

    public LoginContext(Context context) {

        this.context = context;

        loginRemote =  new LoginRemote();

        loginLocal = new LoginLocal();

    }

    public boolean login(String usuario, String contrasena) {

        boolean login = false;

        boolean isOnline = isOnline();

        if(isOnline){

            login = loginRemote.login(usuario, contrasena);

        } else {

            login = loginLocal.login(usuario, contrasena);

        }

        return login;

    }

    public boolean isOnline() {

        ConnectivityManager cm =

                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

}
