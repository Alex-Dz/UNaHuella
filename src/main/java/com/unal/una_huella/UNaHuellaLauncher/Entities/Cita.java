package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import javax.persistence.*;

@Entity
@IdClass(CitaId.class)
@Table(name = "CITA")
public class Cita {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_MASCOTA")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Mascota a_id_mascota;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_JORNADA")
    private Jornada b_id_jornada;

    @Column(name = "HORA_CITA", nullable = false)
    private Time a_hora_cita;

    @Column(name = "ESPECIFICACION", nullable = false)
    private String b_especificacion_cita;

    @ManyToOne
    @JoinColumn(name = "ID_LUGAR")
    private Lugar lugar;

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

    public Time getA_hora_cita() {
        return a_hora_cita;
    }

    public void setA_hora_cita(Time a_hora_cita) {
        this.a_hora_cita = a_hora_cita;
    }

    public String getB_especificacion_cita() {
        return b_especificacion_cita;
    }

    public void setB_especificacion_cita(String b_especificacion_cita) {
        this.b_especificacion_cita = b_especificacion_cita;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
