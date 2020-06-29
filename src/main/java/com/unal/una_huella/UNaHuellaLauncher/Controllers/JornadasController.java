package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.Entities.*;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
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
    MascotaController mascotaController;

    private AVLTree<Mascota> pets = null;

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
            return "newJornada";
        } else {
            try {
                jornadaService.saveJornada(jornada);
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
        pets = mascotaController.getMascotas();
        Iterable<Jornada> jornadas = jornadaService.listAllJornadas();
        List<Cita> allCitas = citaService.listAllCitas();
        List<Cita> citas = new ArrayList<Cita>();
        for (Cita cita : allCitas) {
            if (cita.getA_id_mascota() == null) {
                citas.add(cita);
            }
        }
        /*List<Lugar> lugares = lugarService.listAllLugares();
        List<CitasByLugar> citasByLugar = new ArrayList<CitasByLugar>();
        for (Lugar lugar : lugares) {
            CitasByLugar temp;
            List<Cita> citasLibres = new ArrayList<Cita>();
            for (Cita cita : lugar.getCitas()) {
                if (cita.getA_id_mascota() == null) {
                    citasLibres.add(cita);
                }
            }
            temp = new CitasByLugar(lugar, citasLibres);
            citasByLugar.add(temp);
        }
        if (citasByLugar.size() == 0) {
            citasByLugar = null;
        }*/
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", pets.getList());
        model.addAttribute("jornadas", jornadas);
        model.addAttribute("citasLibres", citas);
        return "asignarCita";
    }

    @PostMapping("/particular/saveCita")
    public String saveCita(@ModelAttribute("cita") Cita cita, ModelMap model) {
        Cita citaToSave = new Cita();
        Jornada jornada = cita.getB_id_jornada();
        List<Cita> citaslibres = new ArrayList<Cita>();
        for (Cita cit : jornada.getCitas()) {
            if (cit.getA_id_mascota() == null) {
                citaslibres.add(cit);
            }
        }
        for (Cita cit : citaslibres) {
            if (cita.getC_hora_cita().toString().equals(cit.getC_hora_cita().toString())){
                citaToSave = citaService.mapCita(cita, cit);
                break;
            }
        }
        citaService.saveCita(citaToSave);
        model.addAttribute("citaCreated", true);
        return newCita((Model) model);
    }

    @GetMapping("/particular/citas")
    public String citasList(Model model) {
        pets = mascotaController.getMascotas();
        List<Mascota> mascotas = pets.getList();
        List<Cita> allCitas = new ArrayList<Cita>();
        for (Mascota mascota : mascotas) {
            List<Cita> citas = mascota.getCitasMascota();
            for (int i = 0; i < citas.size(); i++) {
                allCitas.add(citas.get(i));
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
        if (cita != null) {
            citaService.deleteCita(cita);
        }
        model.addAttribute("citaCreated", true);
        return citasList(model);
    }

    public class CitasByLugar {
        Lugar lugar;
        List<Cita> citaslibres;

        public CitasByLugar(Lugar lugar, List<Cita> citaslibres) {
            this.lugar = lugar;
            this.citaslibres = citaslibres;
        }

        public Lugar getLugar() {
            return lugar;
        }

        public void setLugar(Lugar lugar) {
            this.lugar = lugar;
        }

        public List<Cita> getCitaslibres() {
            return citaslibres;
        }

        public void setCitaslibres(List<Cita> citaslibres) {
            this.citaslibres = citaslibres;
        }
    }
}
