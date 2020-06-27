package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JornadasController {


    @Autowired
    LugarService lugarService;
    @Autowired
    JornadaService jornadaService;

    DoubleLinkedList<Lugar> listaLugares = new DoubleLinkedList<Lugar>();

    public DoubleLinkedList<Lugar> getLugares() {
        for (Lugar lugar : lugarService.listAllLugares()) {
            listaLugares.pushBack(lugar);
        }
        return listaLugares;
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

}
