package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.HashTable;
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
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.List;

@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    HashTable petsTable = null;

    Instant time_start;
    Instant time_end;

    DecimalFormat formatoDecimal = new DecimalFormat("0.000000000");

    @ModelAttribute
    public void addLoggedUserToView(Model model) {
        model.addAttribute("loggedUser", userService.getLoggedUser());
    }


    public HashTable getMascotos() {
        if (petsTable == null) {
            time_start = Instant.now();
            petsTable = new HashTable();
            for (Mascota mascota : mascotaService.listAllMascotas()) {
                if (mascota != null) {
                    petsTable.insert(mascota);
                }
            }
            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado en crear HashTable de mascotas: " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
        }
        return petsTable;
    }

    @RequestMapping("/particular/misMascotas/{idUsuario}")
    public String listaMascotas(Model model, @PathVariable String idUsuario) {
        petsTable = getMascotos();
        List<Mascota> petList = petsTable.findAll(idUsuario);
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
                               @PathVariable("id_mascota") long id_mascota) throws Exception {
        Mascota mascota = new Mascota();
        List<Mascota> petList = petsTable.findAll(id_user);
        model.addAttribute("listaMascota", petList);
        mascota = petsTable.find(id_user, id_mascota);
        model.addAttribute("mascota", mascota);
        model.addAttribute("edit", false);
        return "misMascotas";
    }

    @GetMapping("/particular/profileMascota/edit/{id_user}/{id_mascota}")
    public String editarMascota(Model model, @PathVariable("id_user") String id_user,
                                @PathVariable("id_mascota") long id_mascota) {
        try {
            Mascota mascota = new Mascota();
            time_start = Instant.now();
            mascota = petsTable.find(id_user, id_mascota);
            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado en buscar una mascota en la HashTable: " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
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
                                    @PathVariable("id_user") String id_user, @PathVariable("id_mascota") long id_mascota) throws Exception {
        if (validar.hasErrors()) {
            // rellena los campos nuevamente con los datos y solo muestra error en el que fue erroneo
            model.addAttribute("mascota", pet);
            // muestra el formulario con los datos en los campos
            model.addAttribute("edit", true);
            // no actualiza el registro
            model.addAttribute("petUpdated", false);
            return "misMascotas";
        } else {
            try {
                mascotaService.updateMascota(pet);
                mascotaService.mapMascota(pet, petsTable.find(id_user, id_mascota));
                model.addAttribute("petUpdated", true);
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("mascota", pet);
                model.addAttribute("edit", true);
                model.addAttribute("petUpdated", false);
                return "misMascotas";
            }
            model.addAttribute("edit", false);
            return datosMascota((ModelMap) model, id_user, id_mascota);
        }
    }

    /*      post        */


    @RequestMapping("/particular/newMascota")
    public String newMascota(Model model) {
        petsTable = getMascotos();
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
                petsTable.insert(pet);
                Usuario user = userService.getLoggedUser();
                user.setH_cantidad_mascotas(user.getH_cantidad_mascotas() + 1);
                userService.updateUser(user);
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

    @GetMapping("/particular/eliminarMascota/{id_mascota}")
    public String eliminarMascota(Model model, @PathVariable long id_mascota) {
        try {
            petsTable = getMascotos();
            time_start = Instant.now();
            Mascota mascoto = petsTable.delete(petsTable.find(userService.getLoggedUser().getId_usuario(), id_mascota));
            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado eliminar una mascota de la HashTable: " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
            mascotaService.deleteMascota(id_mascota);
            Usuario user = userService.getLoggedUser();
            user.setH_cantidad_mascotas(user.getH_cantidad_mascotas() - 1);
            userService.updateUser(user);
            userService.mapUser(user, userController.avl.find(user, userController.avl.getRoot()));
            model.addAttribute("deletePet", true);
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", "No se ha podido eliminar el registro");
            model.addAttribute("deletePet", false);
        }
        return listaMascotas(model, userService.getLoggedUser().getId_usuario());
    }
}
