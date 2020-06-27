package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*@Entity
@IdClass(SalaId.class)
@Table(name = "SALA", indexes = {@Index(name = "RELACION_SALA_LUGAR", columnList = "ID_LUGAR")})
public class Sala implements Serializable {

    @Id
    @Column(name = "ID_SALA", length = 5)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id_sala;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LUGAR")
    private Lugar id_lugar;

    @Column(name = "CAPACIDAD_SALA")
    @NotNull(message = "Capacidad de la sala es obligatoria")
    private int capacidad_sala;

    public String getId_sala() {
        return id_sala;
    }

    public void setId_sala(String id_sala) {
        this.id_sala = id_sala;
    }

    public Lugar getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(Lugar id_lugar) {
        this.id_lugar = id_lugar;
    }

    public int getCapacidad_sala() {
        return capacidad_sala;
    }

    public void setCapacidad_sala(int capacidad_sala) {
        this.capacidad_sala = capacidad_sala;
    }

}*/
