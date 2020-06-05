package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.RoleRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepo roleRepo;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/newUser")
    public String formUser(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("user", new Usuario());
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "createUserForm";
    }

    @PostMapping("/saveUser")
    public String createUser(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()){
            model.addAttribute("user", user);
        } else{
            try {
                userService.createUser(user);
                model.addAttribute("userCreated", true);
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", false);
                model.addAttribute("roles", roleRepo.findAll());
            }
        }
        model.addAttribute("edit", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "createUserForm";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
        } else{
            try {
                userService.updateUser(user);
                model.addAttribute("userCreated", true);
                model.addAttribute("edit", false);
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", true);
                model.addAttribute("roles", roleRepo.findAll());
            }
        }
        model.addAttribute("roles", roleRepo.findAll());

        return "createUserForm";
    }

    @GetMapping("/editUser/cancel")
    public String cancelEditUser(){
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String getUserList(Model model){
        model.addAttribute("usuarios", userService.listAllUser());
        return "usuarios";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        try {
            model.addAttribute("user", userService.getUserById(id));
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "createUserForm";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model){
        try {
            userService.deleteUser(id);
            model.addAttribute("deleteSuccess", true);
            model.addAttribute("deleteError", false);
            model.addAttribute("deleteSuccessMessage", "Usuario eliminado");
        }catch (Exception e){
            model.addAttribute("deleteSuccess", false);
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMessage", e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }
}
