package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;

import java.util.List;

public interface CitaService {
    List<Cita> listAllCitas();
    
    Cita getCitaById(String id);
    
    Cita saveCita(Cita cita);

    void deleteCita(Cita cita);
    
    void deleteCitaById(String id);

    Cita mapCita(Cita from, Cita to);
}
