package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;
import com.unal.una_huella.UNaHuellaLauncher.ED.LinkedStack;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.RoleRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.ParticularService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ParticularController {

    private ParticularService particularService;

    LinkedStack<Particular[]> prevPag = new LinkedStack<>();
    LinkedStack<Particular[]> nextPag = new LinkedStack<>();

    long time_start = 0;
    long time_end = 0;

    @Autowired
    public void setParticularService(ParticularService particularService) {
        this.particularService = particularService;
    }

    @RequestMapping("/particular")
    public String particular() {
        getUsers();
        return "particular";
    }

    /*@RequestMapping("particular/new")
    public String newParticular(Model model) {
        model.addAttribute("particular", new Particular());
        model.addAttribute("edit", false);
        model.addAttribute("tipo", tipo = 0);
        return "formulario";
    }*/

    /*@RequestMapping("/particular/profile/{id}")
    public String showParticular(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        return "particularshow";
    }*/

    /*@RequestMapping("/particular/edit/{id}")
    public String editPart(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 1);
        return "formulario";
    }*/

    /*@RequestMapping(value = "/save-particular", method = RequestMethod.POST)
    public String saveParticular(Particular particular) {
        particularService.saveParticular(particular);
        return "redirect:/particular/profile/" + particular.getId_particular();
    }*/

    AVLTree<Usuario> avl = new AVLTree<Usuario>(AVLTree.ID);

    public void getUsers() {
        for (Usuario user : userService.listAllUser()) {
            try {
                avl.insertAVL(user);
            } catch (Exception e) {
                System.err.println("no se pudo insertar el usuario con id " + user.getId_usuario()+ " al arbol por parametro de ordenamiento erroneo");
            }
        }

        time_start = 0;
        time_start = System.currentTimeMillis();

        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en buscar registro: " + (time_end - time_start) + " milliseconds");

    }


    public DoubleLinkedList<Particular> getRegisters() {
        time_start = 0;
        time_start = System.currentTimeMillis();
        DoubleLinkedList<Particular> list = new DoubleLinkedList<Particular>();

        for (Particular particular : particularService.listAllParticulars()) {
            list.pushBack(particular);
        }
        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en crear y llenar DoubleLinkedList " + (time_end - time_start) + " milliseconds");
        return list;
    }

    public void paginas(DoubleLinkedList<Particular> list) {
        int regsPerPage = 20;
        while (!nextPag.isEmpty()) {
            nextPag.pop();
        }
        while (!prevPag.isEmpty()) {
            prevPag.pop();
        }
        time_start = 0;
        time_start = System.currentTimeMillis();
        while (!list.isEmpty()) {
            Particular[] grupo = new Particular[regsPerPage];
            for (int i = 0; i < regsPerPage; i++) {
                Particular reg = list.popFront();
                if (reg == null) {
                    break;
                } else {
                    grupo[i] = reg;
                }
            }
            prevPag.push(grupo);
        }
        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag " + (time_end - time_start) + " milliseconds");
        time_start = 0;
        time_start = System.currentTimeMillis();
        while (!prevPag.isEmpty()) {
            nextPag.push(prevPag.pop());
        }
        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en  llenar Stack nextPag " + (time_end - time_start) + " milliseconds");

    }

    @RequestMapping(value = "/gestor/particular-list", method = RequestMethod.GET)
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

    @RequestMapping(value = "/particulares/previous", method = RequestMethod.GET)
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

    /*@RequestMapping("/particular/delete/{id}")
    public String deleteParticular(@PathVariable String id) {
        particularService.deleteParticular(id);
        return "redirect:/particulares";
    }*/

    @RequestMapping("/particular/inscribirMascota")
    String inscribirMascota() {
        return "inscribirMascota";
    }

    @RequestMapping("/particular/citas/new")
    String asignarCita() {
        return "asignarCita";
    }

    @RequestMapping("/particular/mascotas")
    String misMascotas() {
        return "misMascotas";
    }

    /*  de aquí para abajo son controladores que se deben mover a su respectiva clase después   */

    int tipo = 0;

    /*@RequestMapping("/vet/profile/{id}")
    public String vetProfile(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        return "vetshow";
    }*/


   /* @RequestMapping("/vet/new")
    public String newVet(Model model) {
        model.addAttribute("particular", new Particular());
        model.addAttribute("edit", false);
        model.addAttribute("tipo", tipo);
        return "formulario";
    }*/

    /*@RequestMapping("/vet/edit/{id}")
    public String editVete(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 2);
        return "formulario";
    }*/

    /*@RequestMapping(value = "/save-vet", method = RequestMethod.POST)
    public String saveVet(Particular particular) {
        particularService.saveParticular(particular);
        return "redirect:/vet/profile/" + particular.getId_particular();
    }*/

    /*@RequestMapping("/gestor/profile/{id}")
    public String gestorProfile(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        return "gestorshow";
    }*/

    /*@RequestMapping("/gestor/new")
    public String newGestor(Model model) {
        model.addAttribute("particular", new Particular());
        model.addAttribute("edit", false);
        model.addAttribute("tipo", tipo);
        return "formulario";
    }*/

    /*@RequestMapping("/gestor/edit/{id}")
    public String editGestor(@PathVariable String id, Model model) {
        model.addAttribute("particular", getRegisters().findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 3);
        return "formulario";
    }*/

    /*@RequestMapping(value = "/save-gestor", method = RequestMethod.POST)
    public String saveGestor(Particular particular) {
        particularService.saveParticular(particular);
        return "redirect:/gestor/profile/" + particular.getId_particular();
    }*/

    @RequestMapping("/gestor/searchUser")
    public String searchParticular(Model model) {
        model.addAttribute("idSearch", new Particular());
        return "particularsearch";
    }

    @RequestMapping(value = "idsearch", method = RequestMethod.POST)
    public String searchParticular(Particular idSearch) {
        return "redirect:/particular/profile/" + idSearch.getId_particular();
    }

    /*  de aquí para abajo van controladores de usuario unificado   */

    @Autowired
    UserService userService;

    @Autowired
    RoleRepo roleRepo;

    @RequestMapping("/newUser")
    public String formNewUser(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("user", new Usuario());
        model.addAttribute("userCreated", false);
        model.addAttribute("tipo", tipo = 0);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/saveUser")
    public String createUser(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
        } else {
            try {
                userService.createUser(user);
                model.addAttribute("userCreated", true);
                model.addAttribute("edit", false);
                model.addAttribute("roles", roleRepo.findAll());
                avl.insertAVL(user);
                return "formulario";
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", false);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
        }
        model.addAttribute("edit", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    /*@PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
        } else {
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

        return "formulario";
    }*/

    @GetMapping("/editUser/cancel")
    public String cancelEditUser() {
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String getUserList(Model model) {
        model.addAttribute("usuarios", userService.listAllUser());
        return "usuarios";
    }

    /*@GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable String id, Model model) {
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
    }*/

    @GetMapping("/user/delete/{id}")
    public String deleteUs(@PathVariable String id, Model model) {
        try {
            userService.deleteUser(id);
            model.addAttribute("deleteSuccess", true);
            model.addAttribute("deleteError", false);
            model.addAttribute("deleteSuccessMessage", "Usuario eliminado");
        } catch (Exception e) {
            model.addAttribute("deleteSuccess", false);
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMessage", e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @RequestMapping("/particular/profile/{id}")
    public String partiProfile(@PathVariable String id, Model model) throws Exception {
        Usuario user = new Usuario();
        user.setId_usuario(id);
        user = avl.find(user, avl.getRoot());
        if (user == null) {
            throw new Exception("El usuario no existe");
        }
        model.addAttribute("user", user);
        return "particularshow";
    }

    @GetMapping("/particular/edit/{id}")
    public String editParticular(@PathVariable String id, Model model) {
        try {
            Usuario user = new Usuario();
            user.setId_usuario(id);
            user = avl.find(user, avl.getRoot());
            if (user == null) {
                throw new Exception("El usuario no existe");
            }
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 1);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 1);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/particular/updateUser")
    public String updateParticular(/*@Valid*/ @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 1);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "formulario";
        } else {
            try {
                userService.updateUser(user);
                model.addAttribute("userCreated", true);
                userService.mapUser(user, avl.find(user,avl.getRoot()));
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", true);
                model.addAttribute("tipo", tipo = 1);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "particularshow";
        }
    }

    @RequestMapping("/vet/profile/{id}")
    public String vetProfile(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("user", userService.getUserById(id));
        return "vetshow";
    }

    @GetMapping("/vet/edit/{id}")
    public String editVet(@PathVariable String id, Model model) {
        try {
            model.addAttribute("user", userService.getUserById(id));
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 2);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 2);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/vet/updateUser")
    public String updateVet(/*@Valid*/ @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 2);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "formulario";
        } else {
            try {
                userService.updateUser(user);
                model.addAttribute("userCreated", true);

            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", true);
                model.addAttribute("tipo", tipo = 2);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "vetshow";
        }
    }

    @RequestMapping("/gestor/profile/{id}")
    public String gestorProfile(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("user", userService.getUserById(id));
        return "gestorshow";
    }

    @GetMapping("/gestor/edit/{id}")
    public String editGestor(@PathVariable String id, Model model) {
        try {
            model.addAttribute("user", userService.getUserById(id));
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 3);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 3);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/gestor/updateUser")
    public String updateGestor(/*@Valid*/ @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 3);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "formulario";
        } else {
            try {
                userService.updateUser(user);
                model.addAttribute("userCreated", true);

            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", true);
                model.addAttribute("tipo", tipo = 3);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "gestorshow";
        }
    }

    @GetMapping("/gestor/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        try {
            userService.deleteUser(id);
            model.addAttribute("deleteSuccess", true);
            model.addAttribute("deleteError", false);
            model.addAttribute("deleteSuccessMessage", "Usuario eliminado");
        } catch (Exception e) {
            model.addAttribute("deleteSuccess", false);
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMessage", e.getMessage());
        }
        return "redirect:/usuarios";
    }

}
