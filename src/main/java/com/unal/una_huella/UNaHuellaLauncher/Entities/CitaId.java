package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.io.Serializable;
import java.util.Objects;

public class CitaId implements Serializable {

    private String a_id_mascota;
    private String b_id_jornada;

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
