package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
@IdClass(Lugar_JornadaId.class)
@Table (name = "LUGAR_JORNADA", indexes = {@Index(name = "RELACION_LJ_JORNADA", columnList = "ID_JORNADA"),
        @Index(name = "RELACION_LJ_LUGAR", columnList="ID_LUGAR")})
public class Lugar_Jornada {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_JORNADA")
    private Jornada a_id_jornada;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_LUGAR")
    private Lugar b_id_lugar;

    @Column (name = "NOMBRE_JORNADA", nullable = false, length = 40)
    private String nombre_jornada;

    public Jornada getId_jornada() {
        return a_id_jornada;
    }

    public void setId_jornada(Jornada a_id_jornada) {
        this.a_id_jornada = a_id_jornada;
    }

    public Lugar getId_lugar() {
        return b_id_lugar;
    }

    public void setId_lugar(Lugar b_id_lugar) {
        this.b_id_lugar = b_id_lugar;
    }

    public String getNombre_jornada() {
        return nombre_jornada;
    }

    public void setNombre_jornada(String nombre_jornada) {
        this.nombre_jornada = nombre_jornada;
    }

}
