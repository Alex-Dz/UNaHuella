package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lugar {

    @Id
    @Column(name = "ID_LUGAR", length = 12)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id_lugar;

    @Column(name = "UBICACION_LUGAR", nullable = false, length = 50)
    private String a_ubicacion_lugar;
    @Column(name = "NOMBRE_LUGAR", nullable = false, length = 60)
    private String b_nombre_lugar;
    @Column(name = "CAPACIDAD_PACIENTES", nullable = false)
    private int c_capacidad_pacientes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_lugar")
    @Column(name = "SALAS")
    private List<Sala> salas;


    //private int d_personal;

    public String getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(String id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getUbicacion_lugar() {
        return a_ubicacion_lugar;
    }

    public void setUbicacion_lugar(String ubicacion_lugar) {
        this.a_ubicacion_lugar = ubicacion_lugar;
    }

    public String getNombre_lugar() {
        return b_nombre_lugar;
    }

    public void setNombre_lugar(String nombre_lugar) {
        this.b_nombre_lugar = nombre_lugar;
    }

    public int getCapacidad_pacientes() {
        return c_capacidad_pacientes;
    }

    public void setCapacidad_pacientes(int capacidad_pacientes) {
        this.c_capacidad_pacientes = capacidad_pacientes;
    }

    /*public int getPersonal() {
        return d_personal;
    }

    public void setPersonal(int personal) {
        this.d_personal = personal;
    }
*/
}
