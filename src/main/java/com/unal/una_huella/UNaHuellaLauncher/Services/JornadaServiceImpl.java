package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JornadaServiceImpl implements JornadaService {

    private JornadaRepository jornadaRepository;

    @Autowired
    public void setJornadaRepository(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    @Override
    public Iterable<Jornada> listAllJornadas() {
        return jornadaRepository.findAll();
    }

    @Override
    public Jornada getJornadaById(String id) {
        return jornadaRepository.findById(id)
                .orElse(new Jornada());
    }

    @Override
    public Jornada saveJornada(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    @Override
    public void deleteJornada(String id) {
        jornadaRepository.deleteById(id);
    }

}
