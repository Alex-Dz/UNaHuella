package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Cita;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.MascotaService;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MascotaServiceImpl implements MascotaService {

    private MascotaRepository mascotaRepository;

    @Autowired
    public void setMascotaRepository(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<Mascota> listAllMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota getMascotaById(String id) {
        return mascotaRepository.findById(id)
                .orElse(new Mascota());
    }

    @Override
    public Mascota saveMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void deleteMascota(String id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<Cita> listCitas(Mascota mascota) {
        return mascota.getCitasMascota();
    }

    @Override
    public void mapMascota(Mascota from, Mascota to) {
        to.setId_mascota(from.getId_mascota());
        to.setA_especie(from.getA_especie());
        to.setB_nombre_mascota(from.getB_nombre_mascota());
        to.setC_genero(from.getC_genero());
        to.setD_raza(from.getD_raza());
        to.setE_edad_mascota(from.getE_edad_mascota());
        to.setF_historial_cirugias(from.getF_historial_cirugias());
        to.setG_portador_parasito(from.getG_portador_parasito());
        to.setH_carnet_vacunacion(from.getH_carnet_vacunacion());
        to.setCitasMascota(from.getCitasMascota());
    }

    @Override
    public Mascota updateMascota(Mascota mascota) {
        Mascota bddMascota = getMascotaById(mascota.getId_mascota());
        mapMascota(mascota, bddMascota);
        return mascotaRepository.save(bddMascota);
    }

}
