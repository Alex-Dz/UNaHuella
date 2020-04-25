package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Jornada {

    @Id
    private String id_jornada;

    private String a_id_gestor;
    private Date b_fecha_jornada;
    private int c_cant_inscritos;
    private String d_servicios;

    public String getId_jornada() {
        return id_jornada;
    }

    public void setId_jornada(String id_jornada) {
        this.id_jornada = id_jornada;
    }

    public String getA_id_gestor() {
        return a_id_gestor;
    }

    public void setA_id_gestor(String a_id_gestor) {
        this.a_id_gestor = a_id_gestor;
    }

    public Date getB_fecha_jornada() {
        return b_fecha_jornada;
    }

    public void setB_fecha_jornada(Date b_fecha_jornada) {
        this.b_fecha_jornada = b_fecha_jornada;
    }

    public int getC_cant_inscritos() {
        return c_cant_inscritos;
    }

    public void setC_cant_inscritos(int c_cant_inscritos) {
        this.c_cant_inscritos = c_cant_inscritos;
    }

    public String getD_servicios() {
        return d_servicios;
    }

    public void setD_servicios(String d_servicios) {
        this.d_servicios = d_servicios;
    }

}
