package com.example.tareaspendientes;

import java.util.Date;

public class Tareas {
    private String titulo;
    private String fecha_inicio;
    private String fecha_fin;
    private String observacion;

    public Tareas(String titulo, String fecha_inicio, String fecha_fin, String observacion) {
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.observacion = observacion;
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

    public String toString(){
        return "Tareas{" +
                "Titulo=" + titulo + "\n" +
                "Fecha inicio=" + fecha_inicio + "\n" +
                "Fecha fin=" + fecha_fin + "\n" +
                "Observacion=" + observacion + "\n" +
                '}';
    }
}
