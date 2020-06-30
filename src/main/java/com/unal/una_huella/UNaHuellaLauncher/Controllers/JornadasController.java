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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", pets.getList());
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
        citaService.saveCita(citaToSave);
        model.addAttribute("citaCreated", true);
        return newCita((Model) model);
    }

    /*      controlador para refrescar un solo item select al vuelo mediante jQuery     */

    /*@RequestMapping(value = "/particular/refreshJornada", method = RequestMethod.GET)
    public String refreshItem(@RequestParam("d_especificacion_cita") String servicio, ModelMap model) {
        List<Jornada> allJornadas = jornadaService.listAllJornadas();
        List<Jornada> jornadasFiltradas = new ArrayList<Jornada>();
        for (Jornada jornada : allJornadas) {
            if (jornada.getD_servicios().contains(servicio)) {
                jornadasFiltradas.add(jornada);
            }
        }

        model.addAttribute("jornadas", jornadasFiltradas);

        return "contents :: jornadaSelect-frag";
    }*/

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
