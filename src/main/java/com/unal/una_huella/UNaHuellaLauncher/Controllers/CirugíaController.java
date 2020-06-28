package com.unal.una_huella.UNaHuellaLauncher.Controllers;


import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.ED.NodoList;
import com.unal.una_huella.UNaHuellaLauncher.Entities.*;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.CirugiaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CirugíaController {

    @Autowired
    private CirugiaService cirugiaService;
    @Autowired
    private JornadaService jornadaService;
    @Autowired
    private UserService userService;

    public DoubleLinkedList <Cirugia> getCirugias (){
        DoubleLinkedList <Cirugia> cirugias = new DoubleLinkedList<Cirugia>();

        for (Cirugia cirugia: cirugiaService.listAllCirugias()) {
            cirugias.pushBack(cirugia);
        }

        return cirugias;
    }

    public Cirugia[] getCirugiaById(String id_vet) {
        DoubleLinkedList<Cirugia> cirugiasById = new DoubleLinkedList<Cirugia>();
        int contador = 0;
        for (Cirugia cirugia : cirugiaService.listAllCirugias()) {
            if (cirugia.getB_id_veterinario().equals(id_vet)) {
                cirugiasById.pushBack(cirugia);
                contador++;
            }
        }

        Cirugia[] cirugiasVet = new Cirugia[contador];
        contador = 0;

        for (Cirugia cirugia : cirugiasVet) {
            cirugiasVet[contador] = cirugia;
            contador++;
        }

        return cirugiasVet;
    }

    public List<Cirugia> getCirugiasByJornada (Usuario vet, Jornada jornada){
        List <Cirugia> listaCirugias = vet.getCirugias();
        List <Cirugia> cirugiasByJornada = new ArrayList<Cirugia>();

        for (int i = 0; i < listaCirugias.size(); i++) {
            List <Jornada> listaJornadas = listaCirugias.get(i).getA_id_lugar().getJornadas();
            for (int j = 0; j < listaJornadas.size(); j++) {
                if (listaJornadas.get(j).getId_jornada()==jornada.getId_jornada()){
                    cirugiasByJornada.add(listaCirugias.get(i));
                }
            }
        }
        return cirugiasByJornada;
    }

    public List<Cita> getCitasByJornada (Usuario vet, Jornada jornada){
        List <Cirugia> listaCirugias = vet.getCirugias();
        List <Cita> citasByJornada = new ArrayList<Cita>();

        for (int i = 0; i < listaCirugias.size(); i++) {
            List <Jornada> listaJornadas = listaCirugias.get(i).getA_id_lugar().getJornadas();
            for (int j = 0; j < listaJornadas.size(); j++) {
                if (listaJornadas.get(j).getId_jornada()==jornada.getId_jornada()){
                    citasByJornada = listaJornadas.get(j).getCitas();
                }
            }
        }
        return citasByJornada;
    }

    public List<Jornada> getJornadasById() {
        List<Jornada> jornadasById = new ArrayList<Jornada>();
        int contador = 0;
        for (Jornada jornada : jornadaService.listAllJornadas()) {
            List<Usuario> listaVets = jornada.getE_listaVeterinarios();
            for (int i = 0; i < listaVets.size(); i++) {
                if (listaVets.get(i).getId_usuario().equals(userService.getLoggedUser().getId_usuario())) {
                    jornadasById.add(jornada);
                    contador+=1;
                }
            }

        }
        return jornadasById;
    }

    @RequestMapping("/vet/cirugias")
    public String mostrarJornadas(Model model) {
        List<Jornada> jornadasVet = getJornadasById();
        try {
            //botones
            model.addAttribute("listaJornadas", jornadasVet);
            if (jornadasVet != null || jornadasVet.size() > 0){
                model.addAttribute("jornada", jornadasVet.get(0));
            }
        } catch (Exception e) {
            System.err.println("No se han podido cargar las jornadas");
        }
        model.addAttribute("edit", false);
        return "cirugias";
    }

    @RequestMapping("/vet/cirugias/{id_vet}/{id_jornada}")
    public String datosJornada (ModelMap model, @PathVariable("id_vet") String id_vet,
                                @PathVariable("id_jornada") long id_jornada){
        Usuario vet = new Usuario();
        Jornada jornada = new Jornada();
        vet.setId_usuario(id_vet);
        jornada.setId_jornada(id_jornada);
        List<Cita> citasByJornada = getCitasByJornada(vet, jornada);
        try {
            model.addAttribute("listaCitas", citasByJornada);
            model.addAttribute("cita", citasByJornada.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("edit", false);
        return "cirugias";
    }


    /*public DoubleLinkedList<Cirugia> getCirugiasByJornada(Jornada jornada, Usuario vet) {
        DoubleLinkedList<Lugar> lugaresByJornada = new DoubleLinkedList<Lugar>();
        DoubleLinkedList<Cirugia> cirugiaByVet = new DoubleLinkedList<Cirugia>();

        for (Lugar lugar : jornada.getLugares()) {
            lugaresByJornada.pushBack(lugar);
        }

        NodoList<Lugar> temporal = lugaresByJornada.getHead();
        for (int i = 0; i < lugaresByJornada.size(); i++) {
            List<Cirugia> cirugias = temporal.key.getCirugias();
            for (int j = 0; j < cirugias.size(); j++) {
                if (cirugias.get(j).getB_id_veterinario().equals(vet.getId_usuario())) {
                    Cirugia cirugia = cirugias.get(j);
                    cirugiaByVet.pushBack(cirugia);
                }
            }
            if (cirugiaByVet.isEmpty()){
                temporal = temporal.next;
            } else {
                break;
            }
        }
        return cirugiaByVet;
    }*/
}
