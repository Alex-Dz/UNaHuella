package com.unal.una_huella.UNaHuellaLauncher.Entities;
import javax.persistence.*;

@Entity
@IdClass(SalaId.class)
public class Sala {
    @Id private String a_id_sala;
    @Id private String b_id_lugar;
    private int capacidad_sala;

    public String getA_id_sala() {
        return a_id_sala;
    }

    public void setA_id_sala(String a_id_sala) {
        this.a_id_sala = a_id_sala;
    }

    public String getB_id_lugar() {
        return b_id_lugar;
    }

    public void setB_id_lugar(String b_id_lugar) {
        this.b_id_lugar = b_id_lugar;
    }

    public int getCapacidad_sala() {
        return capacidad_sala;
    }

    public void setCapacidad_sala(int capacidad_sala) {
        this.capacidad_sala = capacidad_sala;
    }
    
    
}
