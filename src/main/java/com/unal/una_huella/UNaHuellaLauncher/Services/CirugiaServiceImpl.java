package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.CirugiaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Cirugia;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.CirugiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CirugiaServiceImpl implements CirugiaService {

    private CirugiaRepository cirugiaRepository;

    @Autowired
    public void setCirugiaRepository(CirugiaRepository cirugiaRepository) {
        this.cirugiaRepository = cirugiaRepository;
    }

    @Override
    public Iterable<Cirugia> listAllCirugias() {
        return cirugiaRepository.findAll();
    }

    @Override
    public Cirugia getCirugiaById(String id) {
        return cirugiaRepository.findById(id)
                .orElse(new Cirugia());
    }

    @Override
    public Cirugia saveCirugia(Cirugia cirugia) {
        return cirugiaRepository.save(cirugia);
    }

    @Override
    public void deleteCirugia(String id) {
        cirugiaRepository.deleteById(id);
    }

}
