package com.example.tareaspendientes;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario, password;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
