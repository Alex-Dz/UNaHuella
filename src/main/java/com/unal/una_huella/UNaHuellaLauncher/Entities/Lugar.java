package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Lugar {

    @Id
    private String id_lugar;

    private String a_ubicacion_lugar;
    private String b_nombre_lugar;
    private int c_capacidad_pacientes;
    private int d_personal;

    public String getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(String id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getUbicacion_lugar() {
        return a_ubicacion_lugar;
    }

    public void setUbicacion_lugar(String ubicacion_lugar) {
        this.a_ubicacion_lugar = ubicacion_lugar;
    }

    public String getNombre_lugar() {
        return b_nombre_lugar;
    }

    public void setNombre_lugar(String nombre_lugar) {
        this.b_nombre_lugar = nombre_lugar;
    }

    public int getCapacidad_pacientes() {
        return c_capacidad_pacientes;
    }

    public void setCapacidad_pacientes(int capacidad_pacientes) {
        this.c_capacidad_pacientes = capacidad_pacientes;
    }

    public int getPersonal() {
        return d_personal;
    }

    public void setPersonal(int personal) {
        this.d_personal = personal;
    }

}
