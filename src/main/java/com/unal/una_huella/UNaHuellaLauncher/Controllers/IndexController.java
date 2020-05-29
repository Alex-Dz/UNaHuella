package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/login")
    String login(){
        return "login";
    }
}
