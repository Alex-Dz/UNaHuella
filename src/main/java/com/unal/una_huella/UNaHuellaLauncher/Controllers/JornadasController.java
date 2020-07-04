package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.ED.HashTable;
import com.unal.una_huella.UNaHuellaLauncher.Entities.*;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JornadasController {


    @Autowired
    LugarService lugarService;
    @Autowired
    JornadaService jornadaService;
    @Autowired
    UserService userService;
    @Autowired
    MascotaService mascotaService;
    @Autowired
    CitaService citaService;
    @Autowired
    UserController userController;
    @Autowired
    MascotaController mascotaController;

    //private AVLTree<Mascota> pets = null;

    HashTable petsTable = null;

    @ModelAttribute
    public void addLoggedUserToView(Model model) {
        model.addAttribute("loggedUser", userService.getLoggedUser());
    }

    @GetMapping("/gestor/newJornada")
    public String newJornada(Model model) {
        model.addAttribute("jornada", new Jornada());
        model.addAttribute("lugares", lugarService.listAllLugares());
        return "newJornada";
    }

    @PostMapping("/gestor/saveJornada")
    public String saveJornada(@Valid @ModelAttribute("jornada") Jornada jornada, BindingResult result, ModelMap model) {
        Usuario loggedUser = userService.getLoggedUser();
        if (loggedUser != null) {
            jornada.setA_id_gestor(loggedUser);
        }
        if (result.hasErrors()) {
            model.addAttribute("jornada", jornada);
            model.addAttribute("lugares", lugarService.listAllLugares());
            return "newJornada";
        } else {
            try {
                jornada = jornadaService.saveJornada(jornada);
                List<Cita> nuevasCitas = new ArrayList<Cita>();
                int hour = 8;
                for (Lugar lugar : jornada.getLugares()) {
                    for (int i = 0; i < 5; i++) {
                        Cita cita = new Cita();
                        cita.setA_id_mascota(null);
                        cita.setB_id_jornada(jornada);
                        cita.setC_hora_cita(new Time(hour, 0, 0));
                        hour++;
                        cita.setD_especificacion_cita("");
                        cita.setLugar(lugar);
                        nuevasCitas.add(cita);
                    }
                    hour = 8;
                }
                citaService.saveAllCitas(nuevasCitas);
                for (Lugar lugar : jornada.getLugares()) {
                    //Usuario vet = lugar.getVets().get(0);
                    for (Usuario vet : lugar.getVets()) {
                        List<Jornada> jornadasVet = vet.getO_jornadas();
                        if (jornadasVet.contains(jornada) == false) {
                            jornadasVet.add(jornada);
                            vet.setO_jornadas(jornadasVet);
                            userService.updateUser(vet);
                            userService.mapUser(vet, userController.avl.find(vet, userController.avl.getRoot()));
                        }
                    }
                }
            } catch (Exception e) {
                model.addAttribute("jornada", jornada);
                model.addAttribute("lugares", lugarService.listAllLugares());
                return "newJornada";
            }
        }

        return "redirect:/gestor/jornadas/ester";
    }

    @GetMapping("/gestor/jornadas/ester")
    public String jornadasEster(Model model) {
        List<Jornada> esterList = new ArrayList<Jornada>();
        List<Jornada> vacunList = new ArrayList<Jornada>();
        for (Jornada jornada : jornadaService.listAllJornadas()) {
            if (jornada.getD_servicios().contains("esterilización")) {
                esterList.add(jornada);
            } else if (jornada.getD_servicios().contains("vacunación")) {
                vacunList.add(jornada);
            } else if (jornada.getD_servicios().contains("vacunación y esterilización")) {
                esterList.add(jornada);
                vacunList.add(jornada);
            }
        }
        model.addAttribute("esterList", esterList);
        model.addAttribute("vacunList", vacunList);
        if (esterList.size() > 0) {
            model.addAttribute("esterSelected", true);
        } else if (vacunList.size() > 0) {
            model.addAttribute("esterSelected", false);
        }
        return "jornadas";
    }

    @GetMapping("/gestor/jornadas/vacun")
    public String jornadasVacun(Model model) {
        List<Jornada> esterList = new ArrayList<Jornada>();
        List<Jornada> vacunList = new ArrayList<Jornada>();
        for (Jornada jornada : jornadaService.listAllJornadas()) {
            if (jornada.getD_servicios().contains("vacunación y esterilización")) {
                esterList.add(jornada);
                vacunList.add(jornada);
            } else if (jornada.getD_servicios().contains("vacunación")) {
                vacunList.add(jornada);
            } else if (jornada.getD_servicios().contains("esterilización")) {
                esterList.add(jornada);
            }
        }
        model.addAttribute("esterList", esterList);
        model.addAttribute("vacunList", vacunList);
        if (vacunList.size() > 0) {
            model.addAttribute("esterSelected", false);
        } else if (esterList.size() > 0) {
            model.addAttribute("esterSelected", true);
        }
        return "jornadas";
    }

    @GetMapping("/gestor/newLugar")
    public String newLugar(Model model) {
        model.addAttribute("lugar", new Lugar());
        return "newLugar";
    }

    @PostMapping("/gestor/saveLugar")
    public String saveLugar(@Valid @ModelAttribute(name = "lugar") Lugar lugar, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("lugar", lugar);
            return "newLugar";
        } else {
            lugarService.saveLugar(lugar);
            model.addAttribute("lugarCreated", true);
            return newLugar((Model) model);
        }
    }

    @GetMapping("/particular/newCita")
    public String newCita(Model model) {
        petsTable = mascotaController.getMascotos();
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", petsTable.findAll(userService.getLoggedUser().getId_usuario()));
        return "asignarCita";
    }

    @GetMapping("/particular/newCita-2")
    public String newCita2(@ModelAttribute("cita") Cita cita, ModelMap model) {
        Date actualDate = new Date();
        java.sql.Date serverDate = new java.sql.Date(actualDate.getTime());
        List<Jornada> allJornadas = jornadaService.listAllJornadas();
        List<Jornada> jornadas = new ArrayList<Jornada>();
        for (Jornada jornada : allJornadas) {
            if (jornada.getB_fecha_jornada().compareTo(serverDate) >= 0 && jornada.getD_servicios().contains(cita.getD_especificacion_cita())) {
                jornadas.add(jornada);
            }
        }
        if (jornadas.size() == 0) {
            jornadas = null;
            model.addAttribute("noJornadas", true);
        }
        model.addAttribute("cita", cita);
        model.addAttribute("jornadas", jornadas);
        return "asignarCita";
    }

    @GetMapping("/particular/newCita-3")
    public String newCita3(@ModelAttribute("cita") Cita cita, ModelMap model) {
        List<Lugar> lugares = cita.getB_id_jornada().getLugares();
        List<Lugar> lugaresFiltrados = new ArrayList<Lugar>();
        for (Lugar lugar : lugares) {
            boolean tieneCitasLibres = false;
            List<Cita> citas = lugar.getCitas();
            int i = 0;
            while (i < citas.size()) {
                if (citas.get(i).getB_id_jornada().getId_jornada() != cita.getB_id_jornada().getId_jornada()) {
                    citas.remove(i);
                } else {
                    i++;
                }
            }
            for (Cita cit : citas) {
                if (cit.getA_id_mascota() == null) {
                    tieneCitasLibres = true;
                }
            }
            if (tieneCitasLibres) {
                lugaresFiltrados.add(lugar);
            }
        }
        if (lugaresFiltrados.size() == 0) {
            lugaresFiltrados = null;
            Date actualDate = new Date();
            java.sql.Date serverDate = new java.sql.Date(actualDate.getTime());
            List<Jornada> allJornadas = jornadaService.listAllJornadas();
            List<Jornada> jornadas = new ArrayList<Jornada>();
            for (Jornada jornada : allJornadas) {
                if (jornada.getB_fecha_jornada().compareTo(serverDate) >= 0 && jornada.getD_servicios().contains(cita.getD_especificacion_cita())) {
                    jornadas.add(jornada);
                }
            }
            model.addAttribute("noLugares", true);
            model.addAttribute("jornadas", jornadas);
        } else {
            List<Jornada> jornadas = new ArrayList<Jornada>();
            jornadas.add(cita.getB_id_jornada());
            model.addAttribute("jornadas", jornadas);
        }

        model.addAttribute("cita", cita);
        model.addAttribute("lugares", lugaresFiltrados);
        return "asignarCita";
    }

    @GetMapping("/particular/newCita-4")
    public String newCita4(@ModelAttribute("cita") Cita cita, ModelMap model) {
        List<Cita> citasLibres = new ArrayList<Cita>();
        List<Cita> citas = cita.getLugar().getCitas();
        int i = 0;
        while (i < citas.size()) {
            if (citas.get(i).getB_id_jornada().getId_jornada() != cita.getB_id_jornada().getId_jornada()) {
                citas.remove(i);
            } else {
                i++;
            }
        }
        for (Cita cit : citas) {
            if (cit.getA_id_mascota() == null) {
                citasLibres.add(cit);
            }
        }
        model.addAttribute("cita", cita);
        model.addAttribute("citas", citasLibres);
        model.addAttribute("selectCita", true);
        return "asignarCita";
    }

    @PostMapping("/particular/saveCita")
    public String saveCita(@ModelAttribute("cita") Cita cita, ModelMap model) {
        Cita citaToSave = new Cita();
        Jornada jornada = cita.getB_id_jornada();
        for (Cita cit : jornada.getCitas()) {
            if (cit.getLugar().getId_lugar() == cita.getLugar().getId_lugar()
                    && cita.getC_hora_cita().toString().equals(cit.getC_hora_cita().toString())) {
                citaToSave = citaService.mapCita(cita, cit);
                break;
            }
        }
        List<Cita> citasMascota = citaToSave.getA_id_mascota().getCitasMascota();
        if (citasMascota == null) {
            citasMascota = new ArrayList<Cita>();
        }
        citasMascota.add(citaToSave);
        citaToSave.getA_id_mascota().setCitasMascota(citasMascota);
        citaService.saveCita(citaToSave);
        petsTable = mascotaController.getMascotos();
        mascotaService.mapMascota(mascotaService.getMascotaById(citaToSave.getA_id_mascota().getId_mascota()), petsTable.find(userService.getLoggedUser().getId_usuario(), citaToSave.getA_id_mascota().getId_mascota()));
        model.addAttribute("citaCreated", true);
        return newCita((Model) model);
    }

    @GetMapping("/particular/citas")
    public String citasList(Model model) {
        petsTable = mascotaController.getMascotos();
        List<Mascota> mascotas = petsTable.findAll(userService.getLoggedUser().getId_usuario());
        List<Cita> allCitas = new ArrayList<Cita>();
        for (Mascota mascota : mascotas) {
            List<Cita> citas = mascota.getCitasMascota();
            if (citas != null) {
                for (int i = 0; i < citas.size(); i++) {
                    allCitas.add(citas.get(i));
                }
            }
        }
        if (allCitas.size() == 0) {
            allCitas = null;
        }
        model.addAttribute("citasByUser", allCitas);
        return "citas";
    }

    @GetMapping("/particular/deleteCita/{idCita}")
    public String deleteCita(@PathVariable("idCita") String idCita, Model model) throws Exception {
        Cita cita = citaService.getCitaById(idCita);
        petsTable = mascotaController.getMascotos();
        Mascota mascota;
        if (cita != null) {
            mascota = cita.getA_id_mascota();
            cita.setA_id_mascota(null);
            cita.setD_especificacion_cita("");
            citaService.saveCita(cita);
            mascotaService.mapMascota(mascotaService.getMascotaById(mascota.getId_mascota()), petsTable.find(userService.getLoggedUser().getId_usuario(), mascota.getId_mascota()));
        }
        model.addAttribute("citaCreated", true);
        return citasList(model);
    }

    @GetMapping("/vet/jornadas")
    public String jornadasVet(Model model) {
        Date fechaActual = new Date();
        java.sql.Date serverDate = new java.sql.Date(fechaActual.getTime());
        List<Jornada> allJornadas = userService.getLoggedUser().getO_jornadas();
        List<Lugar> lugares = userService.getLoggedUser().getP_lugares();
        List<Jornada> jornadas = new ArrayList<Jornada>();
        List<Cita> citasFiltradas = new ArrayList<>();
        List<Cita> citas = null;
        for (Jornada jornada : allJornadas) {
            if (jornada.getB_fecha_jornada().compareTo(serverDate) >= 0) {
                jornadas.add(jornada);
            }
        }
        if (jornadas.size() > 0) {
            citas = jornadas.get(0).getCitas();
            for (Cita cita : citas) {
                if (cita.getLugar().getId_lugar() == lugares.get(0).getId_lugar()) {
                    citasFiltradas.add(cita);
                }
            }
        } else {
            jornadas = null;
        }
        for (int i = 0; i < citasFiltradas.size(); i++) {
            if (i < citasFiltradas.size() - 1) {
                Cita aux = citasFiltradas.get(i + 1);
                if (citasFiltradas.get(i).getC_hora_cita().compareTo(citasFiltradas.get(i + 1).getC_hora_cita()) > 0) {
                    citasFiltradas.set(i + 1, citasFiltradas.get(i));
                    citasFiltradas.set(i, aux);
                }
            }
        }
        model.addAttribute("listaJornadas", jornadas);
        model.addAttribute("listaCitas", citasFiltradas);
        return "jornadasVet";
    }

    @GetMapping("/vet/jornadas/{idJornada}")
    public String jornadasVetButton(@PathVariable("idJornada") long idJornada, Model model) {
        int posJornada = 0;
        Date fechaActual = new Date();
        java.sql.Date serverDate = new java.sql.Date(fechaActual.getTime());
        List<Jornada> allJornadas = userService.getLoggedUser().getO_jornadas();
        List<Lugar> lugares = userService.getLoggedUser().getP_lugares();
        List<Jornada> jornadas = new ArrayList<Jornada>();
        List<Cita> citasFiltradas = new ArrayList<>();
        List<Cita> citas = null;
        for (Jornada jornada : allJornadas) {
            if (jornada.getB_fecha_jornada().compareTo(serverDate) >= 0) {
                jornadas.add(jornada);
            }
        }
        if (jornadas.size() > 0) {
            for (int i = 0; i < jornadas.size(); i++) {
                if (jornadas.get(i).getId_jornada() == idJornada) {
                    posJornada = i;
                }
            }
            citas = jornadas.get(posJornada).getCitas();
            for (Cita cita : citas) {
                if (cita.getLugar().getId_lugar() == lugares.get(0).getId_lugar()) {
                    citasFiltradas.add(cita);
                }
            }
        } else {
            jornadas = null;
        }
        for (int i = 0; i < citasFiltradas.size(); i++) {
            if (i < citasFiltradas.size() - 1) {
                Cita aux = citasFiltradas.get(i + 1);
                if (citasFiltradas.get(i).getC_hora_cita().compareTo(citasFiltradas.get(i + 1).getC_hora_cita()) > 0) {
                    citasFiltradas.set(i + 1, citasFiltradas.get(i));
                    citasFiltradas.set(i, aux);
                }
            }
        }
        model.addAttribute("listaJornadas", jornadas);
        model.addAttribute("listaCitas", citasFiltradas);
        return "jornadasVet";

    }
}
