package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.GestorService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Gestor;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorServiceImpl implements GestorService {

    private GestorRepository gestorRepository;

    @Autowired
    public void setGestorRepository(GestorRepository gestorRepository) {
        this.gestorRepository = gestorRepository;
    }

    @Override
    public Iterable<Gestor> listAllGestors() {
        return gestorRepository.findAll();
    }

    @Override
    public Gestor getGestorById(String id) {
        return gestorRepository.findById(id)
                .orElse(new Gestor());
    }

    @Override
    public Gestor saveGestor(Gestor gestor) {
        return gestorRepository.save(gestor);
    }

    @Override
    public void deleteGestor(String id) {
        gestorRepository.deleteById(id);
    }

}
