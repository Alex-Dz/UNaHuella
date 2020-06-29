package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Entities.CitaId;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.CitaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.CitaRepository;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    private CitaRepository citaRepository;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private JornadaService jornadaService;

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
    public void deleteCita(Cita cita) {
        citaRepository.delete(cita);
    }

    @Override
    public void deleteCitaById(String id) {
        citaRepository.deleteById(id);
    }

}
