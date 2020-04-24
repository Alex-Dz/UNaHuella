package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
@IdClass(DosisId.class)
public class Dosis {

    @Id
    private String a_id_medicamento;
    @Id
    private String b_id_mascota;

    private String a_cantidad_dosis;
    private String b_descripcion_dosis;

    public String getId_medicamento() {
        return a_id_medicamento;
    }

    public void setId_medicamento(String id_medicamento) {
        this.a_id_medicamento = id_medicamento;
    }

    public String getId_mascota() {
        return b_id_mascota;
    }

    public void setId_mascota(String id_mascota) {
        this.b_id_mascota = id_mascota;
    }

    public String getCantidad_dosis() {
        return a_cantidad_dosis;
    }

    public void setCantidad_dosis(String cantidad_dosis) {
        this.a_cantidad_dosis = cantidad_dosis;
    }

    public String getDescripcion_dosis() {
        return b_descripcion_dosis;
    }

    public void setDescripcion_dosis(String descripcion_dosis) {
        this.b_descripcion_dosis = descripcion_dosis;
    }

}
