package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cirugia;

public interface CirugiaService {
    Iterable<Cirugia> listAllCirugias();
    
    Cirugia getCirugiaById(String id);
    
    Cirugia saveCirugia(Cirugia cirugia);
    
    void deleteCirugia(String id);
}
