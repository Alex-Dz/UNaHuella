package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Veterinario;

public interface VeterinarioService {
    Iterable<Veterinario> listAllVeterinarios();
    
    Veterinario getVeterinarioById(String id);
    
    Veterinario saveVeterinario(Veterinario veterinario);
    
    void deleteVeterinario(String id);
}
