package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Gestor;

public interface GestorService {
    Iterable<Gestor> listAllGestors();
    
    Gestor getGestorById(String id);
    
    Gestor saveGestor(Gestor gestor);
    
    void deleteGestor(String id);
}
