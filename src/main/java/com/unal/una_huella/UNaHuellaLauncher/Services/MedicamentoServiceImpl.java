package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.MedicamentoService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Medicamento;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private MedicamentoRepository medicamentoRepository;

    @Autowired
    public void setMedicamentoRepository(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Iterable<Medicamento> listAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento getMedicamentoById(String id) {
        return medicamentoRepository.findById(id)
                .orElse(new Medicamento());
    }

    @Override
    public Medicamento saveMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void deleteMedicamento(String id) {
        medicamentoRepository.deleteById(id);
    }

}
