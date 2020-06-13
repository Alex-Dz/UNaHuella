package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LugarController {


    @Autowired
    LugarService lugarService;

    DoubleLinkedList<Lugar> listaLugares = new DoubleLinkedList<Lugar>();

    public DoubleLinkedList<Lugar> getLugares(){
        for (Lugar lugar : lugarService.listAllLugares()) {
            listaLugares.pushBack(lugar);
        }
        return listaLugares;
    }


}
