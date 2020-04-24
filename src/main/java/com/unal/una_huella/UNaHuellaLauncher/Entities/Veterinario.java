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

    public String getPrimer_nombre_veterinario() {
        return a_primer_nombre_veterinario;
    }

    public void setPrimer_nombre_veterinario(String primer_nombre_veterinario) {
        this.a_primer_nombre_veterinario = primer_nombre_veterinario;
    }

    public String getPrimer_apellido_veterinario() {
        return b_primer_apellido_veterinario;
    }

    public void setPrimer_apellido_veterinario(String primer_apellido_veterinario) {
        this.b_primer_apellido_veterinario = primer_apellido_veterinario;
    }

    public String getDireccion_veterinario() {
        return c_direccion_veterinario;
    }

    public void setDireccion_veterinario(String direccion_veterinario) {
        this.c_direccion_veterinario = direccion_veterinario;
    }

    public String getTelefono_veterinario() {
        return d_telefono_veterinario;
    }

    public void setTelefono_veterinario(String telefono_veterinario) {
        this.d_telefono_veterinario = telefono_veterinario;
    }

    public String getSegundo_nombre_veterinario() {
        return e_segundo_nombre_veterinario;
    }

    public void setSegundo_nombre_veterinario(String segundo_nombre_veterinario) {
        this.e_segundo_nombre_veterinario = segundo_nombre_veterinario;
    }

    public String getSegundo_apellido_veterinario() {
        return f_segundo_apellido_veterinario;
    }

    public void setSegundo_apellido_veterinario(String segundo_apellido_veterinario) {
        this.f_segundo_apellido_veterinario = segundo_apellido_veterinario;
    }

    public String getCorreo_veterinario() {
        return g_correo_veterinario;
    }

    public void setCorreo_veterinario(String correo_veterinario) {
        this.g_correo_veterinario = correo_veterinario;
    }

    public String getNum_tarjetaprof() {
        return h_num_tarjetaprof;
    }

    public void setNum_tarjetaprof(String num_tarjetaprof) {
        this.h_num_tarjetaprof = num_tarjetaprof;
    }

    public String getEspecializacion() {
        return i_especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.i_especializacion = especializacion;
    }

    public int getAnos_experiencia() {
        return j_anos_experiencia;
    }

    public void setAnos_experiencia(int anos_experiencia) {
        this.j_anos_experiencia = anos_experiencia;
    }

}
