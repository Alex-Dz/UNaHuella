package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.util.Objects;
import java.io.Serializable;

public class Lugar_JornadaId implements Serializable {

    private Jornada a_id_jornada;
    private Lugar b_id_lugar;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.a_id_jornada);
        hash = 89 * hash + Objects.hashCode(this.b_id_lugar);
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
        final Lugar_JornadaId other = (Lugar_JornadaId) obj;
        if (!Objects.equals(this.a_id_jornada, other.a_id_jornada)) {
            return false;
        }
        if (!Objects.equals(this.b_id_lugar, other.b_id_lugar)) {
            return false;
        }
        return true;
    }

}
