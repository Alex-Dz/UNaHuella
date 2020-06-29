package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;

public interface JornadaService {
    Iterable<Jornada> listAllJornadas();
    
    Jornada getJornadaById(long id);
    
    Jornada saveJornada(Jornada jornada);
    
    void deleteJornada(long id);
}
