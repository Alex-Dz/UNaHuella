package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "MASCOTA")
public class Mascota {

    @Id
    @Column (name = "ID_MASCOTA", length = 10)
    private String id_mascota;
    @Column (name = "ESPECIE",nullable = false,  length = 1)
    private String a_especie;
    @Column (name = "NOMBRE_MASCOTA", nullable = false, length = 20)
    private String b_nombre_mascota;
    @Column (name = "GENERO", nullable = false, length = 6)
    private char c_genero;
    @Column (name = "RAZA", nullable = false, length = 15)
    private String d_raza;
    @Column (name = "EDAD_MASCOTA", length = 10, nullable = false)
    private String e_edad_mascota;
    @Column (name = "HISTORIAL_CIRUGIAS", nullable = false, length = 500)
    private String f_historial_cirugias;
    @Column (name = "PORTADOR_PARASITO", length = 15, nullable = false)
    private String g_portador_parasito;
    @Column (name = "CARNET_VACUNACION", nullable = false, length = 12)
    private String h_carnet_vacunacion;
    @ManyToOne
    @JoinColumn(name="ID_DUEÑO")
    private Particular i_id_particular;

    public String getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(String id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getA_especie() {
        return a_especie;
    }

    public void setA_especie(String a_especie) {
        this.a_especie = a_especie;
    }

    public String getB_nombre_mascota() {
        return b_nombre_mascota;
    }

    public void setB_nombre_mascota(String b_nombre_mascota) {
        this.b_nombre_mascota = b_nombre_mascota;
    }

    public char getC_genero() {
        return c_genero;
    }

    public void setC_genero(char c_genero) {
        this.c_genero = c_genero;
    }

    public String getD_raza() {
        return d_raza;
    }

    public void setD_raza(String d_raza) {
        this.d_raza = d_raza;
    }

    public String getE_edad_mascota() {
        return e_edad_mascota;
    }

    public void setE_edad_mascota(String e_edad_mascota) {
        this.e_edad_mascota = e_edad_mascota;
    }

    public String getF_historial_cirugias() {
        return f_historial_cirugias;
    }

    public void setF_historial_cirugias(String f_historial_cirugias) {
        this.f_historial_cirugias = f_historial_cirugias;
    }

    public String getG_portador_parasito() {
        return g_portador_parasito;
    }

    public void setG_portador_parasito(String g_portador_parasito) {
        this.g_portador_parasito = g_portador_parasito;
    }

    public String getH_carnet_vacunacion() {
        return h_carnet_vacunacion;
    }

    public void setH_carnet_vacunacion(String h_carnet_vacunacion) {
        this.h_carnet_vacunacion = h_carnet_vacunacion;
    }

    public Particular getI_id_particular() {
        return i_id_particular;
    }

    public void setI_id_particular(Particular i_id_particular) {
        this.i_id_particular = i_id_particular;
    }

}
