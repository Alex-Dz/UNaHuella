package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParticularController {
    
    private ParticularService particularService;
    
    @Autowired
    public void setParticularService(ParticularService particularService){
        this.particularService = particularService;
    }

    @RequestMapping("particular/new")
    public String newParticular(Model model){
        model.addAttribute("particular", new Particular());
        return "particularform";
    }
    
    @RequestMapping(value = "particular", method = RequestMethod.POST)
    public String saveParticular(Particular particular){
        particularService.saveParticular(particular);
        return "redirect:/particular/" + particular.getId_particular();
    }
    
    @RequestMapping("particular/{id}")
    public String showParticular(@PathVariable String id, Model model){
        model.addAttribute("particular", particularService.getParticularById(id));
        return "particularshow";
    }
    
    public DoubleLinkedList <Particular> getRegisters (){
        DoubleLinkedList <Particular> list = new DoubleLinkedList<Particular>();
        
        for (Particular particular : particularService.listAllParticulars()){
            list.pushBack(particular);
            //System.out.println(list.topBack());
        }
        return list;
    }
    
    @RequestMapping(value = "/particulares", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("particulares", getRegisters());
        return "particulares";
    }
    
    @RequestMapping("particular/edit/{id}")
    public String editParticular(@PathVariable String id, Model model){
        model.addAttribute("particular", particularService.getParticularById(id));
        return "particularform";
    }
    
    public String deleteParticular(@PathVariable String id){
        particularService.deleteParticular(id);
        return "redirect:/particulares";
    }
    
}
