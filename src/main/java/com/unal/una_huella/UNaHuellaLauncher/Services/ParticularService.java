package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;

public interface ParticularService {
    Iterable<Particular> listAllParticulars();
    
    Particular getParticularById(String id);
    
    Particular saveParticular(Particular particular);
}
