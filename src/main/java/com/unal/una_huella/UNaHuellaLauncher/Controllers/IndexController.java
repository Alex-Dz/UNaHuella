package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void addLoggedUserToView(Model model) {
        model.addAttribute("loggedUser", userService.getLoggedUser());
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }
}
