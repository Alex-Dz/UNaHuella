package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;

import java.util.List;

public interface JornadaService {
    List<Jornada> listAllJornadas();
    
    Jornada getJornadaById(long id);
    
    Jornada saveJornada(Jornada jornada);
    
    void deleteJornada(long id);
}
