package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;

public interface MascotaService {
    Iterable<Mascota> listAllMascotas();
    
    Mascota getMascotaById(String id);

    void mapMascota(Mascota from, Mascota to);

    Mascota updateMascota (Mascota mascota);
    
    Mascota saveMascota(Mascota mascota);
    
    void deleteMascota(String id);

}
