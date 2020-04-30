package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.VeterinarioService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Veterinario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private VeterinarioRepository veterinarioRepository;

    @Autowired
    public void setVeterinarioRepository(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public Iterable<Veterinario> listAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario getVeterinarioById(String id) {
        return veterinarioRepository.findById(id)
                .orElse(new Veterinario());
    }

    @Override
    public Veterinario saveVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public void deleteVeterinario(String id) {
        veterinarioRepository.deleteById(id);
    }

}
