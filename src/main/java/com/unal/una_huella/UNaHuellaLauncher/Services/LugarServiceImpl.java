package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.LugarService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LugarServiceImpl implements LugarService {

    @Autowired
    private LugarRepository lugarRepository;

    @Override
    public Iterable<Lugar> listAllLugares() {
        return lugarRepository.findAll();
    }

    @Override
    public Lugar getLugarById(String id) {
        return lugarRepository.findById(id)
                .orElse(new Lugar());
    }

    @Override
    public Lugar saveLugar(Lugar lugar) {
        return lugarRepository.save(lugar);
    }

    @Override
    public void deleteLugar(String id) {
        lugarRepository.deleteById(id);
    }

}
