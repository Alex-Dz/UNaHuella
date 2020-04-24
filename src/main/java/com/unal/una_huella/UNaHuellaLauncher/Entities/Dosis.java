package com.unal.una_huella.UNaHuellaLauncher.Entities;
import javax.persistence.*;

@Entity
public class Dosis {
    @Id
    private String a_id_medicamento;
    @Id
    private String b_id_mascota;
    private String c_cantidad_dosis;
    private String d_descripcion_dosis;

    public String getA_id_medicamento() {
        return a_id_medicamento;
    }

    public void setA_id_medicamento(String a_id_medicamento) {
        this.a_id_medicamento = a_id_medicamento;
    }

    public String getB_id_mascota() {
        return b_id_mascota;
    }

    public void setB_id_mascota(String b_id_mascota) {
        this.b_id_mascota = b_id_mascota;
    }

    public String getC_cantidad_dosis() {
        return c_cantidad_dosis;
    }

    public void setC_cantidad_dosis(String c_cantidad_dosis) {
        this.c_cantidad_dosis = c_cantidad_dosis;
    }

    public String getD_descripcion_dosis() {
        return d_descripcion_dosis;
    }

    public void setD_descripcion_dosis(String d_descripcion_dosis) {
        this.d_descripcion_dosis = d_descripcion_dosis;
    }
    
    
}
