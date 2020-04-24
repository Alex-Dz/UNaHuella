package com.unal.una_huella.UNaHuellaLauncher.Entities;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Cita {
    @Id
    private String a_id_mascota;
    @Id
    private String b_id_jornada; 
    private Date c_fecha_cita; 
    private String d_especificacion_cita;

    public String getA_id_mascota() {
        return a_id_mascota;
    }

    public void setA_id_mascota(String a_id_mascota) {
        this.a_id_mascota = a_id_mascota;
    }

    public String getB_id_jornada() {
        return b_id_jornada;
    }

    public void setB_id_jornada(String b_id_jornada) {
        this.b_id_jornada = b_id_jornada;
    }

    public Date getC_fecha_cita() {
        return c_fecha_cita;
    }

    public void setC_fecha_cita(Date c_fecha_cita) {
        this.c_fecha_cita = c_fecha_cita;
    }

    public String getD_especificacion_cita() {
        return d_especificacion_cita;
    }

    public void setD_especificacion_cita(String d_especificacion_cita) {
        this.d_especificacion_cita = d_especificacion_cita;
    }
    
    
}
