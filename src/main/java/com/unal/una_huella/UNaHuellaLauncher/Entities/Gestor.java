package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Gestor {

    @Id
    private String id_gestor;

    private String a_primer_nombre_gestor;
    private String b_primer_apellido_gestor;
    private String c_direccion_gestor;
    private String d_telefono_gestor;
    private String e_segundo_nombre_gestor;
    private String f_segundo_apellido_gestor;
    private String g_correo_gestor;
    private String h_funciones;
    private char i_nivel_acceso;

    public String getId_gestor() {
        return id_gestor;
    }

    public void setId_gestor(String id_gestor) {
        this.id_gestor = id_gestor;
    }

    public String getA_primer_nombre_gestor() {
        return a_primer_nombre_gestor;
    }

    public void setA_primer_nombre_gestor(String a_primer_nombre_gestor) {
        this.a_primer_nombre_gestor = a_primer_nombre_gestor;
    }

    public String getB_primer_apellido_gestor() {
        return b_primer_apellido_gestor;
    }

    public void setB_primer_apellido_gestor(String b_primer_apellido_gestor) {
        this.b_primer_apellido_gestor = b_primer_apellido_gestor;
    }

    public String getC_direccion_gestor() {
        return c_direccion_gestor;
    }

    public void setC_direccion_gestor(String c_direccion_gestor) {
        this.c_direccion_gestor = c_direccion_gestor;
    }

    public String getD_telefono_gestor() {
        return d_telefono_gestor;
    }

    public void setD_telefono_gestor(String d_telefono_gestor) {
        this.d_telefono_gestor = d_telefono_gestor;
    }

    public String getE_segundo_nombre_gestor() {
        return e_segundo_nombre_gestor;
    }

    public void setE_segundo_nombre_gestor(String e_segundo_nombre_gestor) {
        this.e_segundo_nombre_gestor = e_segundo_nombre_gestor;
    }

    public String getF_segundo_apellido_gestor() {
        return f_segundo_apellido_gestor;
    }

    public void setF_segundo_apellido_gestor(String f_segundo_apellido_gestor) {
        this.f_segundo_apellido_gestor = f_segundo_apellido_gestor;
    }

    public String getG_correo_gestor() {
        return g_correo_gestor;
    }

    public void setG_correo_gestor(String g_correo_gestor) {
        this.g_correo_gestor = g_correo_gestor;
    }

    public String getH_funciones() {
        return h_funciones;
    }

    public void setH_funciones(String h_funciones) {
        this.h_funciones = h_funciones;
    }

    public char getI_nivel_acceso() {
        return i_nivel_acceso;
    }

    public void setI_nivel_acceso(char i_nivel_acceso) {
        this.i_nivel_acceso = i_nivel_acceso;
    }

}
