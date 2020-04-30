package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar_Jornada;

public interface Lugar_JornadaService {
    Iterable<Lugar_Jornada> listAllLugar_Jornadas();
    
    Lugar_Jornada getLugar_JornadaById(String id);
    
    Lugar_Jornada saveLugar_Jornada(Lugar_Jornada lugar_Jornada);
    
    void deleteLugar_Jornada(String id);
}
