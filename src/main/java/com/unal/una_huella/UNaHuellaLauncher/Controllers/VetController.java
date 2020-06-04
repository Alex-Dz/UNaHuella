package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VetController {

    @RequestMapping("/vet")
    public String vet(){
        return "veterinario";
    }
}
