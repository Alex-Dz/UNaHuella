package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.DosisService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Dosis;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.DosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DosisServiceImpl implements DosisService {

    private DosisRepository dosisRepository;

    @Autowired
    public void setDosisRepository(DosisRepository dosisRepository) {
        this.dosisRepository = dosisRepository;
    }

    @Override
    public Iterable<Dosis> listAllDosis() {
        return dosisRepository.findAll();
    }

    @Override
    public Dosis getDosisById(String id) {
        return dosisRepository.findById(id)
                .orElse(new Dosis());
    }

    @Override
    public Dosis saveDosis(Dosis dosis) {
        return dosisRepository.save(dosis);
    }

    @Override
    public void deleteDosis(String id) {
        dosisRepository.deleteById(id);
    }

}
