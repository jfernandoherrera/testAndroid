package com.jeduca.jfernandoherrera.jeducatest.activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.jeduca.jfernandoherrera.jeducatest.R;
import com.jeduca.jfernandoherrera.jeducatest.model.context.LoginContext;

public class LoginActivity extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;
    private LoginContext loginContext;
    private UserLoginTask userLoginTask = null;
    private RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginContext = LoginContext.context(loginContext, this);

        checkToStrictMode();

        progress =(RelativeLayout) findViewById(R.id.progress);

        emailView = (EditText) findViewById(R.id.email);

        passwordView = (EditText) findViewById(R.id.password);

    }

    public void loginOnClick(View view) {

        attemptLogin();

    }

    private void checkToStrictMode() {

        if (android.os.Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

        }

    }

    private void attemptLogin() {

        String usuario = emailView.getText().toString();
        String contrasena = passwordView.getText().toString();
        boolean cancel = false;

        if (!TextUtils.isEmpty(contrasena) && !isPasswordValid(contrasena)) {

            passwordView.setError(getString(R.string.error_invalid_password));

            cancel = true;

        }

        if (cancel) {

            passwordView.requestFocus();

        } else {

            showProgress(true);

            userLoginTask = new UserLoginTask(usuario, contrasena);

            userLoginTask.execute((Void) null);

        }

    }

    private boolean isPasswordValid(String password) {

        return password.length() > 4;

    }

    protected void showProgress(boolean show){

        if(show) {

            progress.setVisibility(View.VISIBLE);

        } else {

            progress.setVisibility(View.GONE);

        }

    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String usuario;
        private final String contrasena;

        UserLoginTask(String usuario, String contrasena) {

            this.usuario = usuario;

            this.contrasena = contrasena;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

          return loginContext.login(usuario, contrasena);

        }

        @Override
        protected void onPostExecute(final Boolean success) {

            userLoginTask = null;

            showProgress(false);

            if (success) {

                Log.i("login","done");

            } else {

                passwordView.setError(getString(R.string.error_login));

                passwordView.requestFocus();

            }

        }

        @Override
        protected void onCancelled() {

            userLoginTask = null;

            showProgress(false);

        }

    }

}