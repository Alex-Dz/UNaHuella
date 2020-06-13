package com.unal.una_huella.UNaHuellaLauncher.Entities;

import java.io.Serializable;
import java.util.Objects;

public class DosisId implements Serializable {

    private Medicamento a_id_medicamento;
    private Mascota b_id_mascota;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.a_id_medicamento);
        hash = 23 * hash + Objects.hashCode(this.b_id_mascota);
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
        final DosisId other = (DosisId) obj;
        if (!Objects.equals(this.a_id_medicamento, other.a_id_medicamento)) {
            return false;
        }
        if (!Objects.equals(this.b_id_mascota, other.b_id_mascota)) {
            return false;
        }
        return true;
    }

}
