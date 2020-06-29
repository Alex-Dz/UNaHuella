package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.io.Serializable;
import java.util.Objects;


public class CitaId implements Serializable {

    private Mascota a_id_mascota;
    private Jornada b_id_jornada;

    public CitaId() {
    }

    public CitaId(Mascota a_id_mascota, Jornada b_id_jornada) {
        this.setA_id_mascota(a_id_mascota);
        this.setB_id_jornada(b_id_jornada);
    }

    public Mascota getA_id_mascota() {
        return a_id_mascota;
    }

    public void setA_id_mascota(Mascota a_id_mascota) {
        this.a_id_mascota = a_id_mascota;
    }

    public Jornada getB_id_jornada() {
        return b_id_jornada;
    }

    public void setB_id_jornada(Jornada b_id_jornada) {
        this.b_id_jornada = b_id_jornada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.a_id_mascota);
        hash = 59 * hash + Objects.hashCode(this.b_id_jornada);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CitaId other = (CitaId) obj;
        if (!Objects.equals(this.a_id_mascota, other.a_id_mascota)) {
            return false;
        }
        if (!Objects.equals(this.b_id_jornada, other.b_id_jornada)) {
            return false;
        }
        return true;
    }

}
