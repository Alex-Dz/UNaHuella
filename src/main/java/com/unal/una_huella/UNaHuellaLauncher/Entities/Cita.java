package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "CITA")
public class Cita implements Serializable {

    @Id
    @Column(name = "ID_CITA")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id_cita;

    @ManyToOne
    @JoinColumn(name = "ID_MASCOTA")
    private Mascota a_id_mascota;

    @ManyToOne
    @JoinColumn(name = "ID_JORNADA")
    private Jornada b_id_jornada;

    @Column(name = "HORA_CITA", nullable = false)
    private Time c_hora_cita;

    @Column(name = "ESPECIFICACION", nullable = false)
    private String d_especificacion_cita;

    @ManyToOne
    @JoinColumn(name = "ID_LUGAR")
    private Lugar lugar;

    public long getId_cita() {
        return id_cita;
    }

    public void setId_cita(long id_cita) {
        this.id_cita = id_cita;
    }

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

    public Time getC_hora_cita() {
        return c_hora_cita;
    }

    public void setC_hora_cita(Time a_hora_cita) {
        this.c_hora_cita = a_hora_cita;
    }

    public String getD_especificacion_cita() {
        return d_especificacion_cita;
    }

    public void setD_especificacion_cita(String b_especificacion_cita) {
        this.d_especificacion_cita = b_especificacion_cita;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
