package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import java.util.List;

public interface ParticularService {
    List<Particular> listAllParticulars();
    
    Particular getParticularById(String id);
    
    Particular saveParticular(Particular particular);
    
    void deleteParticular(String id);
}
