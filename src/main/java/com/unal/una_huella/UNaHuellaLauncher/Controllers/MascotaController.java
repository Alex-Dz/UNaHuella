package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.MascotaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.stereotype.Controller;
import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    private AVLTree<Mascota> pets = null;

    public AVLTree<Mascota> getMascotas() {
        if (pets == null || pets.getRoot() == null) {
            pets = new AVLTree<Mascota>(AVLTree.ID_DUEÑO);
            for (Mascota mascota : userService.getLoggedUser().getMismascotas()) {
                if (mascota != null) {
                    try {
                        pets.insertAVL(mascota);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } else {
            if (pets.getRoot().getKey().getI_id_usuario() != userService.getLoggedUser()) {
                pets = new AVLTree<Mascota>(AVLTree.ID_DUEÑO);
                for (Mascota mascota : userService.getLoggedUser().getMismascotas()) {
                    if (mascota != null) {
                        try {
                            pets.insertAVL(mascota);
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
        }

        return pets;
    }

    public void setMascotas(AVLTree<Mascota> pets){
        this.pets = pets;
        userController.pets = pets;
    }

    @RequestMapping("/particular/misMascotas/{id}")
    public String listaMascotas(Model model, @PathVariable String id) {
        Usuario user = new Usuario();
        user.setId_usuario(id);
        pets = getMascotas();
        List<Mascota> petList = pets.getList();
        try {
            model.addAttribute("listaMascota", petList);
            if (petList != null) {
                model.addAttribute("mascota", petList.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("edit", false);
        return "misMascotas";
    }

    @RequestMapping("/particular/profileMascota/{id_user}/{id_mascota}")
    public String datosMascota(ModelMap model, @PathVariable("id_user") String id_user,
                               @PathVariable("id_mascota") String id_mascota) throws Exception {
        Mascota mascota = new Mascota();
        Usuario user = new Usuario();
        user.setId_usuario(id_user);
        List<Mascota> petList = pets.getList();
        model.addAttribute("listaMascota", petList);
        mascota.setId_mascota(id_mascota);
        mascota = pets.find(mascota, pets.getRoot());
        model.addAttribute("mascota", mascota);
        model.addAttribute("edit", false);
        return "misMascotas";
    }

    @GetMapping("/particular/profileMascota/edit/{id_user}/{id_mascota}")
    public String editarMascota(Model model, @PathVariable("id_user") String id_user,
                                @PathVariable("id_mascota") String id_mascota) {
        try {
            Mascota mascota = new Mascota();
            mascota.setId_mascota(id_mascota);
            mascota = pets.find(mascota, pets.getRoot());

            if (mascota == null) {
                throw new Exception("Este registro de Mascota no existe");
            }

            model.addAttribute("mascota", mascota);
            model.addAttribute("edit", true);
            model.addAttribute("petCreated", false);

        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("petCreated", false);
        }
        return "misMascotas";
    }

    @PostMapping("/particular/profileMascota/update/{id_user}/{id_mascota}")
    public String actualizarMascota(@Valid @ModelAttribute("mascota") Mascota pet, BindingResult validar, Model model,
                                    @PathVariable("id_user") String id_user, @PathVariable("id_mascota") String id_mascota) throws Exception {
        if (validar.hasErrors()) {
            // rellena los campos nuevamente con los datos y solo muestra error en el que fue erroneo
            model.addAttribute("mascota", pet);
            // muestra el formulario con los datos en los campos
            model.addAttribute("edit", true);
            // no actualiza el registro
            model.addAttribute("petCreated", false);
            return "misMascotas";
        } else {
            try {
                mascotaService.updateMascota(pet);
                mascotaService.mapMascota(pet, pets.find(pet, pets.getRoot()));
                model.addAttribute("petCreated", true);
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("mascota", pet);
                model.addAttribute("edit", true);
                model.addAttribute("petCreated", false);
                return "misMascotas";
            }
            model.addAttribute("edit", false);
            return datosMascota((ModelMap) model, id_user, id_mascota);
        }
    }

    /*      post        */


    @RequestMapping("/particular/newMascota")
    public String newMascota(Model model) {
        pets = getMascotas();
        model.addAttribute("mascota", new Mascota());
        return "inscribirMascota";
    }

    /* Valid y BindingResult: valida la información de los objetos
     * que estemos introduciendo con un  formulario
     * Model: agregar atributos al modelo
     * vs
     * ModelMap: herramientas de interfaz de usuario */

    @PostMapping("/particular/saveMascota")
    public String saveMascota(@Valid @ModelAttribute("mascota") Mascota pet, BindingResult validar, ModelMap model) {
        if (validar.hasErrors()) {
            // rellena los campos nuevamente con los datos y solo muestra error en el que fue erroneo
            model.addAttribute("mascota", pet);
            return "inscribirMascota";
        } else {
            try {
                pet.setI_id_dueño(userService.getLoggedUser());
                pet = mascotaService.saveMascota(pet);
                Usuario user = userService.getLoggedUser();
                user.setH_cantidad_mascotas(user.getH_cantidad_mascotas() + 1);
                userService.updateUser(user);
                pets.insertAVL(pet);
                userService.mapUser(user, userController.avl.find(user, userController.avl.getRoot()));
                model.addAttribute("petCreated", true);
                model.addAttribute("edit", false);
                // con mensaje de confirmacion de mascota creada
                return datosMascota(model, userService.getLoggedUser().getId_usuario(), pet.getId_mascota());
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("mascota", pet);
                return "inscribirMascota";
            }
        }
    }


    @GetMapping("/particular/{id}/eliminarMascota/{id_mascota}")
    public String eliminarMascota(Model model, @PathVariable String id, @PathVariable String id_mascota) {
        try {
            mascotaService.deleteMascota(id_mascota);
            model.addAttribute("deletePet", true);
            model.addAttribute("deletePetMensaje", "Registro eliminado");
            model.addAttribute("deletePetError", false);
        } catch (Exception e) {
            model.addAttribute("deletePetError", true);
            model.addAttribute("deletePet", false);
            model.addAttribute("deletePetErrorMensaje", "No se ha podido eliminar el registro");
        }
        return "redirect:/mismascotas";
    }

    @RequestMapping(value = "idpet_search", method = RequestMethod.POST)
    public String searchMascota(Mascota idpet_search) {
        return "redirect:/petprofile/" + idpet_search.getId_mascota();
    }
}
