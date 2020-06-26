package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.io.Serializable;
import java.util.Objects;

public class SalaId implements Serializable {

    private String id_sala;
    private Lugar id_lugar;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id_sala);
        hash = 37 * hash + Objects.hashCode(this.id_lugar);
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
        final SalaId other = (SalaId) obj;
        if (!Objects.equals(this.id_sala, other.id_sala)) {
            return false;
        }
        if (!Objects.equals(this.id_lugar, other.id_lugar)) {
            return false;
        }
        return true;
    }
}
