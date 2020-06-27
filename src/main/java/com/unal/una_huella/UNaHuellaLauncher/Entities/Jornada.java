package com.unal.una_huella.UNaHuellaLauncher.Entities;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "JORNADA")
public class Jornada implements Serializable {

    @Id
    @Column(name = "ID_JORNADA")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id_jornada;

    @ManyToOne
    @JoinColumn(name = "ID_GESTOR")
    private Usuario a_id_gestor;

    @Column(name = "FECHA_JORNADA", nullable = false)
    private Date b_fecha_jornada;

    @Column(name = "CANTIDAD_INSCRITOS", nullable = false)
    private int c_cant_inscritos;

    @Column(name = "SERVICIOS", nullable = false, length = 500)
    private String d_servicios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lugar_jornada",
            joinColumns = @JoinColumn(name = "lugar_id"),
            inverseJoinColumns = @JoinColumn(name = "jornada_id"))
    private List<Lugar> lugares;

    public long getId_jornada() {
        return id_jornada;
    }

    public void setId_jornada(long id_jornada) {
        this.id_jornada = id_jornada;
    }

    public Usuario getA_id_gestor() {
        return a_id_gestor;
    }

    public void setA_id_gestor(Usuario a_id_gestor) {
        this.a_id_gestor = a_id_gestor;
    }

    public Date getB_fecha_jornada() {
        return b_fecha_jornada;
    }

    public void setB_fecha_jornada(Date b_fecha_jornada) {
        this.b_fecha_jornada = b_fecha_jornada;
    }

    public int getC_cant_inscritos() {
        return c_cant_inscritos;
    }

    public void setC_cant_inscritos(int c_cant_inscritos) {
        this.c_cant_inscritos = c_cant_inscritos;
    }

    public String getD_servicios() {
        return d_servicios;
    }

    public void setD_servicios(String d_servicios) {
        this.d_servicios = d_servicios;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }
}
