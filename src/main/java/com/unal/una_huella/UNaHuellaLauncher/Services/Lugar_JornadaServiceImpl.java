package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.Lugar_JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar_Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.Lugar_JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Lugar_JornadaServiceImpl implements Lugar_JornadaService {

    private Lugar_JornadaRepository lugar_jornadaRepository;

    @Autowired
    public void setLugar_JornadaRepository(Lugar_JornadaRepository lugar_jornadaRepository) {
        this.lugar_jornadaRepository = lugar_jornadaRepository;
    }

    @Override
    public Iterable<Lugar_Jornada> listAllLugar_Jornadas() {
        return lugar_jornadaRepository.findAll();
    }

    @Override
    public Lugar_Jornada getLugar_JornadaById(String id) {
        return lugar_jornadaRepository.findById(id)
                .orElse(new Lugar_Jornada());
    }

    @Override
    public Lugar_Jornada saveLugar_Jornada(Lugar_Jornada lugar_jornada) {
        return lugar_jornadaRepository.save(lugar_jornada);
    }

    @Override
    public void deleteLugar_Jornada(String id) {
        lugar_jornadaRepository.deleteById(id);
    }

}
