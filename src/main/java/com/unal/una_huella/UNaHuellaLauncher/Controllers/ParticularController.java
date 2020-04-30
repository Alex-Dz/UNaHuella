package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.ED.LinkedStack;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParticularController {

    private ParticularService particularService;
    
    LinkedStack<Particular[]> prevPag = new LinkedStack<>();
    LinkedStack<Particular[]> nextPag = new LinkedStack<>();
    
    long time_start;
    long time_end;

    @Autowired
    public void setParticularService(ParticularService particularService) {
        this.particularService = particularService;
    }

    @RequestMapping("particular/new")
    public String newParticular(Model model) {
        model.addAttribute("particular", new Particular());
        model.addAttribute("edit", false);
        return "particularform";
    }
    
    @RequestMapping(value = "idsearch", method = RequestMethod.POST)
    public String searchParticular(Particular idSearch) {
        return "redirect:/particular/" + idSearch.getId_particular();
    }

    @RequestMapping(value = "particular", method = RequestMethod.POST)
    public String saveParticular(Particular particular) {
        particularService.saveParticular(particular);
        return "redirect:/particular/" + particular.getId_particular();
    }

    @RequestMapping("particular/{id}")
    public String showParticular(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        return "particularshow";
    }

    public DoubleLinkedList<Particular> getRegisters() {
        time_start = System.currentTimeMillis();
        DoubleLinkedList<Particular> list = new DoubleLinkedList<Particular>();

        for (Particular particular : particularService.listAllParticulars()) {
            list.pushBack(particular);
        }
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en crear y llenar DoubleLinkedList "+ ( time_end - time_start ) +" milliseconds");
        return list;
    }

    public void paginas(DoubleLinkedList<Particular> list) {
        int regsPerPage = 20;
        while (!nextPag.isEmpty()){
            nextPag.pop();
        }
        while (!prevPag.isEmpty()){
            prevPag.pop();
        }
        time_start = System.currentTimeMillis();
        while (!list.isEmpty()) {
            Particular[] grupo = new Particular[regsPerPage];
            for (int i = 0; i < regsPerPage; i++) {
                Particular reg = list.popFront();
                if(reg == null){
                    break;
                } else{
                    grupo[i] = reg;
                }
            }
            prevPag.push(grupo);
        }
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag "+ ( time_end - time_start ) +" milliseconds");
        time_start = System.currentTimeMillis();
        while (!prevPag.isEmpty()){
            nextPag.push(prevPag.pop());
        }
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en  llenar Stack nextPag"+ ( time_end - time_start ) +" milliseconds");
        
    }

    @RequestMapping(value = "/particulares", method = RequestMethod.GET)
    public String list(Model model) {
        paginas(getRegisters());
        if (nextPag.top() != null) {
            model.addAttribute("pagina", nextPag.top());
            model.addAttribute("prev", prevPag.top());
            model.addAttribute("next", nextPag.size());
        } else {
            model.addAttribute("pagina", null);
            model.addAttribute("prev", null);
            model.addAttribute("next", 0);
        }
        return "particulares";
    }

    @RequestMapping(value = "/particulares/next", method = RequestMethod.GET)
    public String nextPage(Model model) {
        prevPag.push(nextPag.pop());
        if (nextPag.top() != null) {
            model.addAttribute("pagina", nextPag.top());
            model.addAttribute("prev", prevPag.top());
            model.addAttribute("next", nextPag.size());
        } else {
            model.addAttribute("pagina", null);
            model.addAttribute("prev", null);
            model.addAttribute("next", 0);
        }
        return "particulares";
    }

    @RequestMapping(value = "/particulares/preview", method = RequestMethod.GET)
    public String prevPage(Model model) {
        nextPag.push(prevPag.pop());
        if (nextPag.top() != null) {
            model.addAttribute("pagina", nextPag.top());
            model.addAttribute("prev", prevPag.top());
            model.addAttribute("next", nextPag.size());
        } else {
            model.addAttribute("pagina", null);
            model.addAttribute("prev", null);
            model.addAttribute("next", 0);
        }
        return "particulares";
    }

    @RequestMapping("/particular/edit/{id}")
    public String editParticular(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        model.addAttribute("edit", true);
        return "particularform";
    }
    
    @RequestMapping("/particular/buscar")
    public String searchParticular(Model model){
        model.addAttribute("idSearch", new Particular());
        return "particularsearch";
    }

    @RequestMapping("/particular/delete/{id}")
    public String deleteParticular(@PathVariable String id) {
        particularService.deleteParticular(id);
        return "redirect:/particulares";
    }

}
