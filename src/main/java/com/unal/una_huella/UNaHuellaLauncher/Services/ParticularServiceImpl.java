package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.ParticularService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.ParticularRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticularServiceImpl implements ParticularService {

    private ParticularRepository particularRepository;

    @Autowired
    public void setParticularRepository(ParticularRepository particularRepository) {
        this.particularRepository = particularRepository;
    }

    @Override
    public List <Particular> listAllParticulars() {
        return particularRepository.findAll();
    }

    @Override
    public Particular getParticularById(String id) {
        return particularRepository.findById(id)
                .orElse(new Particular());
    }

    @Override
    public Particular saveParticular(Particular particular) {
        return particularRepository.save(particular);
    }

    @Override
    public void deleteParticular(String id) {
        particularRepository.deleteById(id);
    }
    
}
