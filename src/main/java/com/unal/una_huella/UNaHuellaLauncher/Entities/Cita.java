package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@IdClass(CitaId.class)
public class Cita {

    @Id
    private String a_id_mascota;
    @Id
    private String b_id_jornada;

    private Date a_fecha_cita;
    private String b_especificacion_cita;

    public String getId_mascota() {
        return a_id_mascota;
    }

    public void setId_mascota(String id_mascota) {
        this.a_id_mascota = id_mascota;
    }

    public String getId_jornada() {
        return b_id_jornada;
    }

    public void setId_jornada(String id_jornada) {
        this.b_id_jornada = id_jornada;
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
