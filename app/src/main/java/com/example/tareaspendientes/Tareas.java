package com.example.tareaspendientes;

import java.io.Serializable;
import java.util.Date;

public class Tareas implements Serializable {
    private String user; //Usuario
    private String titulo;
    private String fecha_inicio;
    private String fecha_fin;
    private String observacion;
    private String estado;

    public Tareas(String user, String titulo, String fecha_inicio, String fecha_fin, String observacion, String estado) {
        this.user = user;
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.observacion = observacion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
