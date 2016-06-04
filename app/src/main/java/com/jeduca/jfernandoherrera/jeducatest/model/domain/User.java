package com.jeduca.jfernandoherrera.jeducatest.model.domain;

public class User {

    private long id;
    private String usuario;
    private String contrasena;

    public User(long id, String usuario, String contrasena) {

        this.contrasena = contrasena;

        this.usuario = usuario;

        this.id = id;

    }

    public void setId(long id) {

        this.id = id;

    }

    public long getId() {

        return id;

    }

}
