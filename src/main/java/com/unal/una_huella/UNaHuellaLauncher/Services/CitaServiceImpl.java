package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.CitaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    private CitaRepository citaRepository;

    @Autowired
    public void setCitaRepository(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Iterable<Cita> listAllCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Cita getCitaById(String id) {
        return citaRepository.findById(id)
                .orElse(new Cita());
    }

    @Override
    public Cita saveCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void deleteCita(String id) {
        citaRepository.deleteById(id);
    }

}
