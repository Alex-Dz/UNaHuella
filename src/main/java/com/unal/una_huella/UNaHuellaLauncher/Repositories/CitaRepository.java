package com.unal.una_huella.UNaHuellaLauncher.Repositories;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;
import com.unal.una_huella.UNaHuellaLauncher.Entities.CitaId;
import org.springframework.data.repository.CrudRepository;

public interface CitaRepository extends CrudRepository<Cita, String> {

}
