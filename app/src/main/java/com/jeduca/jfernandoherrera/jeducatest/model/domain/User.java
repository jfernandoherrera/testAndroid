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

    public String getContrasena() {

        return contrasena;

    }

    public String getUsuario() {

        return usuario;

    }

    public void setContrasena(String contrasena) {

        this.contrasena = contrasena;

    }

    public void setUsuario(String usuario) {

        this.usuario = usuario;

    }

}
