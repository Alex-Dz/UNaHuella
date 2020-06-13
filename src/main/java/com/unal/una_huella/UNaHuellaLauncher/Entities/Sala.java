package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@IdClass(SalaId.class)
@Table(name = "SALA", indexes = {@Index(name = "RELACION_SALA_LUGAR", columnList = "ID_LUGAR")})
public class Sala {

    @Id
    @Column(name = "ID_SALA", length = 5)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String a_id_sala;
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_LUGAR")
    private Lugar b_id_lugar;

    @Column(name = "CAPACIDAD_SALA")
    private int capacidad_sala;

    public String getId_sala() {
        return a_id_sala;
    }

    public void setId_sala(String a_id_sala) {
        this.a_id_sala = a_id_sala;
    }

    public Lugar getId_lugar() {
        return b_id_lugar;
    }

    public void setId_lugar(Lugar b_id_lugar) {
        this.b_id_lugar = b_id_lugar;
    }

    public int getCapacidad_sala() {
        return capacidad_sala;
    }

    public void setCapacidad_sala(int capacidad_sala) {
        this.capacidad_sala = capacidad_sala;
    }

}
