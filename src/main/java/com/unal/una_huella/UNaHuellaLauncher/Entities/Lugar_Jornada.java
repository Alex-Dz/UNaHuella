package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
@IdClass(Lugar_JornadaId.class)
public class Lugar_Jornada {

    @Id
    private String a_id_jornada;
    @Id
    private String b_id_lugar;
    
    private String nombre_jornada;

    public String getId_jornada() {
        return a_id_jornada;
    }

    public void setId_jornada(String a_id_jornada) {
        this.a_id_jornada = a_id_jornada;
    }

    public String getId_lugar() {
        return b_id_lugar;
    }

    public void setId_lugar(String b_id_lugar) {
        this.b_id_lugar = b_id_lugar;
    }

    public String getNombre_jornada() {
        return nombre_jornada;
    }

    public void setNombre_jornada(String nombre_jornada) {
        this.nombre_jornada = nombre_jornada;
    }

}
