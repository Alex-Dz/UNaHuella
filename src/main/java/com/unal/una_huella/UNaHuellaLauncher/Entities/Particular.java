package com.unal.una_huella.UNaHuellaLauncher.Entities;

import javax.persistence.*;

@Entity
public class Particular {

    @Id
    private String id_particular;

    private String primer_nombre_particular;
    private String primer_apellido_particular;
    private String direccion_particular;
    private String telefono_particular;
    private String segundo_nombre_particular;
    private String segundo_apellido_particular;
    private String correo_particular;
    private int cantidad_mascotas_inscritas;
    private char estrato;

    public String getId_particular() {
        return id_particular;
    }

    public void setId_particular(String id_particular) {
        this.id_particular = id_particular;
    }

    public String getPrimer_nombre_particular() {
        return primer_nombre_particular;
    }

    public void setPrimer_nombre_particular(String primer_nombre_particular) {
        this.primer_nombre_particular = primer_nombre_particular;
    }

    public String getPrimer_apellido_particular() {
        return primer_apellido_particular;
    }

    public void setPrimer_apellido_particular(String primer_apellido_particular) {
        this.primer_apellido_particular = primer_apellido_particular;
    }

    public String getDireccion_particular() {
        return direccion_particular;
    }

    public void setDireccion_particular(String direccion_particular) {
        this.direccion_particular = direccion_particular;
    }

    public String getTelefono_particular() {
        return telefono_particular;
    }

    public void setTelefono_particular(String telefono_particular) {
        this.telefono_particular = telefono_particular;
    }

    public String getSegundo_nombre_particular() {
        return segundo_nombre_particular;
    }

    public void setSegundo_nombre_particular(String segundo_nombre_particular) {
        this.segundo_nombre_particular = segundo_nombre_particular;
    }

    public String getSegundo_apellido_particular() {
        return segundo_apellido_particular;
    }

    public void setSegundo_apellido_particular(String segundo_apellido_particular) {
        this.segundo_apellido_particular = segundo_apellido_particular;
    }

    public String getCorreo_particular() {
        return correo_particular;
    }

    public void setCorreo_particular(String correo_particular) {
        this.correo_particular = correo_particular;
    }

    public int getCantidad_mascotas_inscritas() {
        return cantidad_mascotas_inscritas;
    }

    public void setCantidad_mascotas_inscritas(int cantidad_mascotas_inscritas) {
        this.cantidad_mascotas_inscritas = cantidad_mascotas_inscritas;
    }

    public char getEstrato() {
        return estrato;
    }

    public void setEstrato(char estrato) {
        this.estrato = estrato;
    }

}
