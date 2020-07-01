package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Lugar implements Serializable {

    @Id
    @Column(name = "ID_LUGAR", length = 12)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id_lugar;

    @Column(name = "UBICACION_LUGAR")
    @NotBlank(message = "Ubicación es obligatoria")
    @Size(max = 50, message = "máximo 50 caracteres")
    private String a_ubicacion_lugar;

    @Column(name = "NOMBRE_LUGAR")
    @NotBlank(message = "Nombre es obligatoria")
    @Size(max = 60, message = "máximo 60 caracteres")
    private String b_nombre_lugar;

    @Column(name = "CAPACIDAD_PACIENTES")
    @NotNull(message = "Cantidad de pacientes es obligatorio")
    private int c_capacidad_pacientes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "a_id_lugar")
    @Column(name = "CIRUGIAS")
    private List<Cirugia> cirugias;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "lugares")
    @Column(name = "JORNADAS")
    private List<Jornada> jornadas;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "p_lugares")
    @Column(name = "VETERINARIOS")
    private List<Usuario> vets;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lugar")
    @Column(name = "CITAS")
    private List<Cita> citas;


    public String getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(String id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getA_ubicacion_lugar() {
        return a_ubicacion_lugar;
    }

    public void setA_ubicacion_lugar(String a_ubicacion_lugar) {
        this.a_ubicacion_lugar = a_ubicacion_lugar;
    }

    public String getB_nombre_lugar() {
        return b_nombre_lugar;
    }

    public void setB_nombre_lugar(String b_nombre_lugar) {
        this.b_nombre_lugar = b_nombre_lugar;
    }

    public int getC_capacidad_pacientes() {
        return c_capacidad_pacientes;
    }

    public void setC_capacidad_pacientes(int c_capacidad_pacientes) {
        this.c_capacidad_pacientes = c_capacidad_pacientes;
    }

    public List<Cirugia> getCirugias() {
        return cirugias;
    }

    public void setCirugias(List<Cirugia> cirugias) {
        this.cirugias = cirugias;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
