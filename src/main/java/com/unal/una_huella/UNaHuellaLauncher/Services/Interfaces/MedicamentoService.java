package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Medicamento;

public interface MedicamentoService {
    Iterable<Medicamento> listAllMedicamento();
    
    Medicamento getMedicamentoById(String id);
    
    Medicamento saveMedicamento(Medicamento medicamento);
    
    void deleteMedicamento(String id);
}
