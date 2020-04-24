package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Lugar {
    @Id
    private String id_lugar; 
    private String a_ubicacion_lugar; 
    private String b_nombre_lugar; 
    private int c_capacidad_pacientes, d_personal; 

    public String getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(String id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getA_ubicacion_lugar() {
        return a_ubicacion_lugar;
    }

    public void setA_ubicacion_lugar(String a_ubicacion_lugar) {
        this.a_ubicacion_lugar = a_ubicacion_lugar;
    }

    public String getB_nombre_lugar() {
        return b_nombre_lugar;
    }

    public void setB_nombre_lugar(String b_nombre_lugar) {
        this.b_nombre_lugar = b_nombre_lugar;
    }

    public int getC_capacidad_pacientes() {
        return c_capacidad_pacientes;
    }

    public void setC_capacidad_pacientes(int c_capacidad_pacientes) {
        this.c_capacidad_pacientes = c_capacidad_pacientes;
    }

    public int getD_personal() {
        return d_personal;
    }

    public void setD_personal(int d_personal) {
        this.d_personal = d_personal;
    }
    
    
}
