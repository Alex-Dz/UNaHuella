package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@IdClass(CitaId.class)
@Table (name = "CITA")
public class Cita {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_MASCOTA")
    private Mascota a_id_mascota;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_JORNADA")
    private Jornada b_id_jornada;
    @Column(name = "FECHA_CITA", nullable = false)
    private Date a_fecha_cita;
    @Column(name = "ESPECIFICACION", nullable = false)
    private String b_especificacion_cita;

    public Mascota getA_id_mascota() {
        return a_id_mascota;
    }

    public void setA_id_mascota(Mascota a_id_mascota) {
        this.a_id_mascota = a_id_mascota;
    }

    public Jornada getB_id_jornada() {
        return b_id_jornada;
    }

    public void setB_id_jornada(Jornada b_id_jornada) {
        this.b_id_jornada = b_id_jornada;
    }

    public Date getFecha_cita() {
        return a_fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.a_fecha_cita = fecha_cita;
    }

    public String getEspecificacion_cita() {
        return b_especificacion_cita;
    }

    public void setEspecificacion_cita(String especificacion_cita) {
        this.b_especificacion_cita = especificacion_cita;
    }

}
