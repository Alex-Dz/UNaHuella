package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Veterinario {
    
    @Id
    private String id_veterinario;
    
    private String a_primer_nombre_veterinario;
    private String b_primer_apellido_veterinario;
    private String c_direccion_veterinario;
    private String d_telefono_veterinario;
    private String e_segundo_nombre_veterinario;
    private String f_segundo_apellido_veterinario;
    private String g_correo_veterinario;
    private String h_num_tarjetaprof;
    private String i_especializacion;
    private int j_anos_experiencia;

    public String getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(String id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public String getA_primer_nombre_veterinario() {
        return a_primer_nombre_veterinario;
    }

    public void setA_primer_nombre_veterinario(String a_primer_nombre_veterinario) {
        this.a_primer_nombre_veterinario = a_primer_nombre_veterinario;
    }

    public String getB_primer_apellido_veterinario() {
        return b_primer_apellido_veterinario;
    }

    public void setB_primer_apellido_veterinario(String b_primer_apellido_veterinario) {
        this.b_primer_apellido_veterinario = b_primer_apellido_veterinario;
    }

    public String getC_direccion_veterinario() {
        return c_direccion_veterinario;
    }

    public void setC_direccion_veterinario(String c_direccion_veterinario) {
        this.c_direccion_veterinario = c_direccion_veterinario;
    }

    public String getD_telefono_veterinario() {
        return d_telefono_veterinario;
    }

    public void setD_telefono_veterinario(String d_telefono_veterinario) {
        this.d_telefono_veterinario = d_telefono_veterinario;
    }

    public String getE_segundo_nombre_veterinario() {
        return e_segundo_nombre_veterinario;
    }

    public void setE_segundo_nombre_veterinario(String e_segundo_nombre_veterinario) {
        this.e_segundo_nombre_veterinario = e_segundo_nombre_veterinario;
    }

    public String getF_segundo_apellido_veterinario() {
        return f_segundo_apellido_veterinario;
    }

    public void setF_segundo_apellido_veterinario(String f_segundo_apellido_veterinario) {
        this.f_segundo_apellido_veterinario = f_segundo_apellido_veterinario;
    }

    public String getG_correo_veterinario() {
        return g_correo_veterinario;
    }

    public void setG_correo_veterinario(String g_correo_veterinario) {
        this.g_correo_veterinario = g_correo_veterinario;
    }

    public String getH_num_tarjetaprof() {
        return h_num_tarjetaprof;
    }

    public void setH_num_tarjetaprof(String h_num_tarjetaprof) {
        this.h_num_tarjetaprof = h_num_tarjetaprof;
    }

    public String getI_especializacion() {
        return i_especializacion;
    }

    public void setI_especializacion(String i_especializacion) {
        this.i_especializacion = i_especializacion;
    }

    public int getJ_anos_experiencia() {
        return j_anos_experiencia;
    }

    public void setJ_anos_experiencia(int j_anos_experiencia) {
        this.j_anos_experiencia = j_anos_experiencia;
    }
    
    
}
