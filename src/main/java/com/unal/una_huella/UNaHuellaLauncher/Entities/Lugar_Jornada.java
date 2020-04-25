package com.unal.una_huella.UNaHuellaLauncher.Entities;
import javax.persistence.*;

@Entity
@IdClass (Lugar_JornadaId.class)
public class Lugar_Jornada {
    @Id private String a_id_jornada;
    @Id private String b_id_lugar;
    private String nombre_jornada;

    public String getA_id_jornada() {
        return a_id_jornada;
    }

    public void setA_id_jornada(String a_id_jornada) {
        this.a_id_jornada = a_id_jornada;
    }

    public String getB_id_lugar() {
        return b_id_lugar;
    }

    public void setB_id_lugar(String b_id_lugar) {
        this.b_id_lugar = b_id_lugar;
    }

    public String getNombre_jornada() {
        return nombre_jornada;
    }

    public void setNombre_jornada(String nombre_jornada) {
        this.nombre_jornada = nombre_jornada;
    }
    
    
}
