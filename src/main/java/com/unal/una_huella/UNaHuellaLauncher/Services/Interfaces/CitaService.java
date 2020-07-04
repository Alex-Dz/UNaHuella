package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;

import java.util.List;

public interface CitaService {
    List<Cita> listAllCitas();

    Cita getCitaById(long id);

    Cita saveCita(Cita cita);

    List<Cita> saveAllCitas(List<Cita> citas);

    void deleteCita(Cita cita);

    void deleteCitaById(long id);

    Cita mapCita(Cita from, Cita to);
}
