package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar;

public interface LugarService {
    Iterable<Lugar> listAllLugar();
    
    Lugar getLugarById(String id);
    
    Lugar saveLugar(Lugar lugar);
    
    void deleteLugar(String id);
}
