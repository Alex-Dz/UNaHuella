package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
@Table (name = "MEDICAMENTO")
public class Medicamento {

    @Id
    @Column (name = "MEDICAMENTO", length = 20)
    private String id_medicamento;
    @Column (name = "NOMBRE_MEDICAMENTO", length = 30, nullable = false)
    private String a_nombre_medicamento;
    @Column (name = "TIPO_MEDICAMENTO", length = 30, nullable = false)
    private String b_tipo_medicamento;
    @Column (name = "PRESENTACION", length = 30, nullable = false)
    private String c_presentacion_med;
    @Column (name = "COMPONENTE_ACTIVO", length = 30, nullable = false)
    private String d_componente_act;

    public String getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(String id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getNombre_medicamento() {
        return a_nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.a_nombre_medicamento = nombre_medicamento;
    }

    public String getTipo_medicamento() {
        return b_tipo_medicamento;
    }

    public void setTipo_medicamento(String tipo_medicamento) {
        this.b_tipo_medicamento = tipo_medicamento;
    }

    public String getPresentacion_med() {
        return c_presentacion_med;
    }

    public void setPresentacion_med(String presentacion_med) {
        this.c_presentacion_med = presentacion_med;
    }

    public String getComponente_act() {
        return d_componente_act;
    }

    public void setComponente_act(String componente_act) {
        this.d_componente_act = componente_act;
    }

}
