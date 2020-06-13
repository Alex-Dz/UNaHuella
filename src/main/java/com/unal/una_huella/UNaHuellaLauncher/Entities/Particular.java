package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Particular {

    @Id
    @Column(name = "ID_PARTICULAR", nullable = false, length = 25)
    private String id_particular;

    @Column(name = "PRIMER_NOMBRE_PARTICULAR", nullable = false, length = 30)
    private String a_primer_nombre_particular;
    @Column(name = "PRIMER_APELLIDO_PARTICULAR", nullable = false, length = 30)
    private String b_primer_apellido_particular;
    @Column(name = "DIRECCION_PARTICULAR", nullable = false, length = 50)
    private String c_direccion_particular;
    @Column(name = "TELEFONO_PARTICULAR", nullable = false, length = 15)
    private String d_telefono_particular;
    @Column(name = "SEGUNDO_NOMBRE_PARTICULAR", nullable = true, length = 30)
    private String e_segundo_nombre_particular;
    @Column(name = "SEGUNDO_APELLIDO_PARTICULAR", nullable = true, length = 30)
    private String f_segundo_apellido_particular;
    @Column(name = "CORREO_PARTICULAR", nullable = false, length = 35)
    private String g_correo_particular;
    @Column(name = "CANTIDAD_MASCOTAS_INSCRITAS", nullable = true)
    private int h_cantidad_mascotas_inscritas;
    @Column(name = "ESTRATO", nullable = false, length = 1)
    private char i_estrato;

    public String getId_particular() {
        return id_particular;
    }

    public void setId_particular(String id_particular) {
        this.id_particular = id_particular;
    }

    public String getA_primer_nombre_particular() {
        return a_primer_nombre_particular;
    }

    public void setA_primer_nombre_particular(String a_primer_nombre_particular) {
        this.a_primer_nombre_particular = a_primer_nombre_particular;
    }

    public String getB_primer_apellido_particular() {
        return b_primer_apellido_particular;
    }

    public void setB_primer_apellido_particular(String b_primer_apellido_particular) {
        this.b_primer_apellido_particular = b_primer_apellido_particular;
    }

    public String getC_direccion_particular() {
        return c_direccion_particular;
    }

    public void setC_direccion_particular(String c_direccion_particular) {
        this.c_direccion_particular = c_direccion_particular;
    }

    public String getD_telefono_particular() {
        return d_telefono_particular;
    }

    public void setD_telefono_particular(String d_telefono_particular) {
        this.d_telefono_particular = d_telefono_particular;
    }

    public String getE_segundo_nombre_particular() {
        return e_segundo_nombre_particular;
    }

    public void setE_segundo_nombre_particular(String e_segundo_nombre_particular) {
        this.e_segundo_nombre_particular = e_segundo_nombre_particular;
    }

    public String getF_segundo_apellido_particular() {
        return f_segundo_apellido_particular;
    }

    public void setF_segundo_apellido_particular(String f_segundo_apellido_particular) {
        this.f_segundo_apellido_particular = f_segundo_apellido_particular;
    }

    public String getG_correo_particular() {
        return g_correo_particular;
    }

    public void setG_correo_particular(String g_correo_particular) {
        this.g_correo_particular = g_correo_particular;
    }

    public int getH_cantidad_mascotas_inscritas() {
        return h_cantidad_mascotas_inscritas;
    }

    public void setH_cantidad_mascotas_inscritas(int h_cantidad_mascotas_inscritas) {
        this.h_cantidad_mascotas_inscritas = h_cantidad_mascotas_inscritas;
    }

    public char getI_estrato() {
        return i_estrato;
    }

    public void setI_estrato(char i_estrato) {
        this.i_estrato = i_estrato;
    }

    

}
