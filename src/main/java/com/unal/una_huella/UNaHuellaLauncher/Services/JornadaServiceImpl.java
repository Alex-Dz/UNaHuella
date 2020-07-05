package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaServiceImpl implements JornadaService {

    private JornadaRepository jornadaRepository;

    @Autowired
    public void setJornadaRepository(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    @Override
    public List<Jornada> listAllJornadas() {
        return jornadaRepository.findAll();
    }

    @Override
    public Jornada getJornadaById(long id) {

        return jornadaRepository.findById(id)
                .orElse(new Jornada());
    }

    @Override
    public Jornada saveJornada(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    @Override
    public void deleteJornada(long id) {
        jornadaRepository.deleteById(id);
    }

}
