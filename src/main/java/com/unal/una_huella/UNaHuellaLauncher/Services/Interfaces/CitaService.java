package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;

public interface CitaService {
    Iterable<Cita> listAllCitas();
    
    Cita getCitaById(String id);
    
    Cita saveCita(Cita cita);
    
    void deleteCita(String id);
}
