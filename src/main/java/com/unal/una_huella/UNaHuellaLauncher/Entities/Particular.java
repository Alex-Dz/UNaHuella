package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Particular {

    @Id
    private String id_particular;
    private String a_primer_nombre_particular;
    private String b_primer_apellido_particular;
    private String c_direccion_particular;
    private String d_telefono_particular;
    private String e_segundo_nombre_particular;
    private String f_segundo_apellido_particular;
    private String g_correo_particular;
    private int h_cantidad_mascotas_inscritas;
    private char i_estrato;

    public String getId_particular() {
        return id_particular;
    }

    public void setId_particular(String id_particular) {
        this.id_particular = id_particular;
    }

    public String getPrimer_nombre_particular() {
        return a_primer_nombre_particular;
    }

    public void setPrimer_nombre_particular(String primer_nombre_particular) {
        this.a_primer_nombre_particular = primer_nombre_particular;
    }

    public String getPrimer_apellido_particular() {
        return b_primer_apellido_particular;
    }

    public void setPrimer_apellido_particular(String primer_apellido_particular) {
        this.b_primer_apellido_particular = primer_apellido_particular;
    }

    public String getDireccion_particular() {
        return c_direccion_particular;
    }

    public void setDireccion_particular(String direccion_particular) {
        this.c_direccion_particular = direccion_particular;
    }

    public String getTelefono_particular() {
        return d_telefono_particular;
    }

    public void setTelefono_particular(String telefono_particular) {
        this.d_telefono_particular = telefono_particular;
    }

    public String getSegundo_nombre_particular() {
        return e_segundo_nombre_particular;
    }

    public void setSegundo_nombre_particular(String segundo_nombre_particular) {
        this.e_segundo_nombre_particular = segundo_nombre_particular;
    }

    public String getSegundo_apellido_particular() {
        return f_segundo_apellido_particular;
    }

    public void setSegundo_apellido_particular(String segundo_apellido_particular) {
        this.f_segundo_apellido_particular = segundo_apellido_particular;
    }

    public String getCorreo_particular() {
        return g_correo_particular;
    }

    public void setCorreo_particular(String correo_particular) {
        this.g_correo_particular = correo_particular;
    }

    public int getCantidad_mascotas_inscritas() {
        return h_cantidad_mascotas_inscritas;
    }

    public void setCantidad_mascotas_inscritas(int cantidad_mascotas_inscritas) {
        this.h_cantidad_mascotas_inscritas = cantidad_mascotas_inscritas;
    }

    public char getEstrato() {
        return i_estrato;
    }

    public void setEstrato(char estrato) {
        this.i_estrato = estrato;
    }

}
