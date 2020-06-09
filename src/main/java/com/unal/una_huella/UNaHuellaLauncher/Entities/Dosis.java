package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
@IdClass(DosisId.class)
@Table (name = "DOSIS")
public class Dosis {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_MEDICAMENTO")
    private Medicamento a_id_medicamento;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_MASCOTA")
    private Mascota b_id_mascota;
    @Column (name = "CANTIDAD_DOSIS", nullable = false, length = 10)
    private String a_cantidad_dosis;
    @Column (name = "DESCRIPCION_DOSIS", nullable = false, length = 30)
    private String b_descripcion_dosis;

    public Medicamento getA_id_medicamento() {
        return a_id_medicamento;
    }

    public void setA_id_medicamento(Medicamento a_id_medicamento) {
        this.a_id_medicamento = a_id_medicamento;
    }

    public Mascota getB_id_mascota() {
        return b_id_mascota;
    }

    public void setB_id_mascota(Mascota b_id_mascota) {
        this.b_id_mascota = b_id_mascota;
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
