package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;

public interface MascotaService {
    Iterable<Mascota> listAllMascotas();
    
    Mascota getMascotaById(String id);
    
    Mascota saveMascota(Mascota mascota);
    
    void deleteMascota(String id);
}
