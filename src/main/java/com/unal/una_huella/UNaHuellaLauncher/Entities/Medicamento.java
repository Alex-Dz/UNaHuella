package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Medicamento {
    @Id
    private String id_medicamento;
    private String a_nombre_medicamento; 
    private String b_tipo_medicamento; 
    private String c_presentacion_med; 
    private String d_componente_act;

    public String getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(String id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getA_nombre_medicamento() {
        return a_nombre_medicamento;
    }

    public void setA_nombre_medicamento(String a_nombre_medicamento) {
        this.a_nombre_medicamento = a_nombre_medicamento;
    }

    public String getB_tipo_medicamento() {
        return b_tipo_medicamento;
    }

    public void setB_tipo_medicamento(String b_tipo_medicamento) {
        this.b_tipo_medicamento = b_tipo_medicamento;
    }

    public String getC_presentacion_med() {
        return c_presentacion_med;
    }

    public void setC_presentacion_med(String c_presentacion_med) {
        this.c_presentacion_med = c_presentacion_med;
    }

    public String getD_componente_act() {
        return d_componente_act;
    }

    public void setD_componente_act(String d_componente_act) {
        this.d_componente_act = d_componente_act;
    }
    
}
