package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;

import java.util.List;

public interface MascotaService {
    Iterable<Mascota> listAllMascotas();
    
    Mascota getMascotaById(long id);

    void mapMascota(Mascota from, Mascota to);

    Mascota updateMascota (Mascota mascota);
    
    Mascota saveMascota(Mascota mascota);
    
    void deleteMascota(long id);

    List<Cita> listCitas(Mascota mascota);

}
