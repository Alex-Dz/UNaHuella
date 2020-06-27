package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CIRUGIA")
public class Cirugia {

    @Id
    @Column(name = "ID_CIRUGIA", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id_cirugia;

    @ManyToOne
    @JoinColumn(name = "SALA_ID")
    private Lugar a_id_lugar;

    @ManyToOne
    @JoinColumn(name = "ID_VETERINARIO")
    private Usuario b_id_veterinario;

    @Column(name = "PROCEDIMIENTO", nullable = false, length = 100)
    private String c_procedimiento;
    @Column(name = "COMPLICACIONES", nullable = false, length = 100)
    private String d_complicaciones;

    public String getId_cirugia() {
        return id_cirugia;
    }

    public void setId_cirugia(String id_cirugia) {
        this.id_cirugia = id_cirugia;
    }

    public Lugar getA_id_lugar() {
        return a_id_lugar;
    }

    public void setA_id_lugar(Lugar a_id_lugar) {
        this.a_id_lugar = a_id_lugar;
    }

    public Usuario getB_id_veterinario() {
        return b_id_veterinario;
    }

    public void setB_id_veterinario(Usuario b_id_veterinario) {
        this.b_id_veterinario = b_id_veterinario;
    }

    public String getC_procedimiento() {
        return c_procedimiento;
    }

    public void setC_procedimiento(String c_procedimiento) {
        this.c_procedimiento = c_procedimiento;
    }

    public String getD_complicaciones() {
        return d_complicaciones;
    }

    public void setD_complicaciones(String d_complicaciones) {
        this.d_complicaciones = d_complicaciones;
    }

}
