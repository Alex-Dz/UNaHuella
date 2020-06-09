package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "JORNADA")
public class Jornada {

    @Id
    @Column(name = "ID_JORNADA", length = 5)
    private String id_jornada;
    // CAMBIAR A MANYTOONE CON USUARIO
    //@ManyToOne
    //@JoinColumn(name = "ID_GESTOR")
    //private Usuario a_id_gestor;
    @Column(name = "ID_GESTOR", nullable = false, length = 25)
    private String a_id_gestor;

    @Column(name = "FECHA_JORNADA", nullable = false)
    private Date b_fecha_jornada;

    @Column(name = "CANTIDAD_INSCRITOS", nullable = false)
    private int c_cant_inscritos;

    @Column(name = "SERVICIOS", nullable = false, length = 500)
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
