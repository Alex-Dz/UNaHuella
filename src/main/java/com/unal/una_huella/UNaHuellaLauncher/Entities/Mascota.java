package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Mascota {

    @Id
    private String id_mascota;
    
    private String a_especie;
    private String b_nombre_mascota;
    private char c_genero;
    private String d_raza;
    private String e_edad_mascota;
    private String f_historial_cirugias;
    private String g_portador_parasito;
    private String h_carnet_vacunacion;
    private String i_id_particular;

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

    public String getI_id_particular() {
        return i_id_particular;
    }

    public void setI_id_particular(String i_id_particular) {
        this.i_id_particular = i_id_particular;
    }

}
