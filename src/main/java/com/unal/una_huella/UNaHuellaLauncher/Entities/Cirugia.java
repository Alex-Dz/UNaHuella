package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Cirugia {

    @Id
    private String id_cirugia;

    private String a_id_sala;
    private String b_id_veterinario;
    private String c_procedimiento;
    private String d_complicaciones;

    public String getId_cirugia() {
        return id_cirugia;
    }

    public void setId_cirugia(String id_cirugia) {
        this.id_cirugia = id_cirugia;
    }

    public String getA_id_sala() {
        return a_id_sala;
    }

    public void setA_id_sala(String a_id_sala) {
        this.a_id_sala = a_id_sala;
    }

    public String getB_id_veterinario() {
        return b_id_veterinario;
    }

    public void setB_id_veterinario(String b_id_veterinario) {
        this.b_id_veterinario = b_id_veterinario;
    }

    public String getC_procedimiento() {
        return c_procedimiento;
    }

    public void setC_procedimiento(String c_procedimiento) {
        this.c_procedimiento = c_procedimiento;
    }

    public String getD_complicaciones() {
        return d_complicaciones;
    }

    public void setD_complicaciones(String d_complicaciones) {
        this.d_complicaciones = d_complicaciones;
    }

}
