package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Dosis;

public interface DosisService {
    Iterable<Dosis> listAllDosis();
    
    Dosis getDosisById(String id);
    
    Dosis saveDosis(Dosis dosis);
    
    void deleteDosis(String id);
}
