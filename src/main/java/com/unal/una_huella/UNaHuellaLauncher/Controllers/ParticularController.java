package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.ED.LinkedStack;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.RoleRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.ParticularService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import com.unal.una_huella.UNaHuellaLauncher.util.OrderPair;
import com.unal.una_huella.UNaHuellaLauncher.util.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ParticularController {

    private ParticularService particularService;

    private static final int PARTICULAR = 1;
    private static final int VETERINARIO = 2;
    private static final int GESTOR = 3;


    LinkedStack<Usuario[]> prevPag = new LinkedStack<>();
    LinkedStack<Usuario[]> nextPag = new LinkedStack<>();


    int[] regsPerPage = {10, 20, 30, 50, 100, 500};
    int pagDefault = 10;
    int[] sortValues = {1, 2, 3, 4, 5, 6, 7};
    String[] sortNames = {"Documento", "Primer Nombre", "Primer Apellido", "N° de Mascotas", "Estrato", "Nivel de Acceso", "Experiencia"};
    int sortDefault = 1;
    OrderPair params;
    SortParams[] sortPartiParams = new SortParams[5];
    SortParams[] sortVetParams = new SortParams[4];
    SortParams[] sortGestorParams = new SortParams[4];

    long time_start = 0;
    long time_end = 0;

    @Autowired
    public void setParticularService(ParticularService particularService) {
        this.particularService = particularService;
    }

    @RequestMapping("/particular")
    public String particular() {
        getUsers(sortDefault);
        return "particular";
    }

    @RequestMapping("/gestor")
    String gestor() {
        SortParams temp;
        for (int i = 0; i < sortPartiParams.length; i++) {
            temp = new SortParams(sortNames[i], sortValues[i]);
            sortPartiParams[i] = temp;
        }

        for (int i = 0; i < sortVetParams.length - 1; i++) {
            temp = new SortParams(sortNames[i], sortValues[i]);
            sortVetParams[i] = temp;
        }
        temp = new SortParams(sortNames[6], sortValues[6]);
        sortVetParams[3] = temp;

        for (int i = 0; i < sortGestorParams.length - 1; i++) {
            temp = new SortParams(sortNames[i], sortValues[i]);
            sortGestorParams[i] = temp;
        }
        temp = new SortParams(sortNames[5], sortValues[5]);
        sortGestorParams[3] = temp;

        getUsers(sortDefault);
        return "gestor";
    }

    @RequestMapping("/vet")
    public String vet() {
        getUsers(sortDefault);
        return "veterinario";
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

    AVLTree<Usuario> avl = null;

    public void getUsers(int sortBy) {
        if (avl == null) {
            avl = new AVLTree<Usuario>(AVLTree.ID);
        } else {
            avl.emptyTree();
            switch (sortBy) {
                case 1: {
                    avl = new AVLTree<Usuario>(AVLTree.ID);
                    break;
                }
                case 2: {
                    avl = new AVLTree<Usuario>(AVLTree.PRIMER_NOMBRE);
                    break;
                }
                case 3: {
                    avl = new AVLTree<Usuario>(AVLTree.PRIMER_APELLIDO);
                    break;
                }
                case 4: {
                    avl = new AVLTree<Usuario>(AVLTree.N_MASCOTAS);
                    break;
                }
                case 5: {
                    avl = new AVLTree<Usuario>(AVLTree.ESTRATO);
                    break;
                }
                case 6: {
                    avl = new AVLTree<Usuario>(AVLTree.NIVEL_ACCESO);
                    break;
                }
                case 7: {
                    avl = new AVLTree<Usuario>(AVLTree.EXP);
                    break;
                }
            }
        }

        time_start = 0;
        time_start = System.currentTimeMillis();

        for (Usuario user : userService.listAllUser()) {
            try {
                avl.insertAVL(user);
                //System.out.println("ingresado en el arbol: " + user.toString());
            } catch (Exception e) {
                System.err.println("no se pudo insertar el usuario con id " + user.getId_usuario() + " al arbol por parametro de ordenamiento erroneo");
            }
        }

        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en crear/reconstruir arbol AVL de usuarios: " + (time_end - time_start) + " milliseconds");
    }


    /*public DoubleLinkedList<Particular> getRegisters() {
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
    }*/

    public void getPaginas(int regsPerPage, int tipo) {
        nextPag.emptyStack();
        prevPag.emptyStack();

        long count = 0;
        java.util.List<Usuario> listaUsuarios = avl.getList();
        if (tipo == PARTICULAR) {
            java.util.List<Usuario> listaParticulares = new ArrayList<Usuario>();
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario temp = listaUsuarios.get(i);
                try {
                    if (userService.getRoles(temp).get(0).getId() == PARTICULAR) {
                        listaParticulares.add(temp);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            time_start = 0;
            time_start = System.currentTimeMillis();
            while (count < listaParticulares.size()) {
                Usuario[] pagina = new Usuario[regsPerPage];
                for (int i = 0; i < regsPerPage; i++) {
                    Usuario reg;
                    if (count < listaParticulares.size()) {
                        reg = listaParticulares.get((int) count);
                    } else {
                        reg = null;
                    }
                    ++count;
                    if (reg == null) {
                        break;
                    } else {
                        pagina[i] = reg;
                    }
                }
                if (pagina[0] != null) {
                    prevPag.push(pagina);
                }
            }
            time_end = 0;
            time_end = System.currentTimeMillis();
            System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag " + (time_end - time_start) + " milliseconds");
        } else if (tipo == VETERINARIO) {
            java.util.List<Usuario> listaVets = new ArrayList<Usuario>();
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario temp = listaUsuarios.get(i);
                try {
                    if (userService.getRoles(temp).get(0).getId() == VETERINARIO) {
                        listaVets.add(temp);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            time_start = 0;
            time_start = System.currentTimeMillis();
            while (count < listaVets.size()) {
                Usuario[] pagina = new Usuario[regsPerPage];
                for (int i = 0; i < regsPerPage; i++) {
                    Usuario reg;
                    if (count < listaVets.size()) {
                        reg = listaVets.get((int) count);
                    } else {
                        reg = null;
                    }
                    ++count;
                    if (reg == null) {
                        break;
                    } else {
                        pagina[i] = reg;
                    }
                }
                if (pagina[0] != null) {
                    prevPag.push(pagina);
                }
            }
            time_end = 0;
            time_end = System.currentTimeMillis();
            System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag " + (time_end - time_start) + " milliseconds");
        } else {
            java.util.List<Usuario> listaGestores = new ArrayList<Usuario>();
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario temp = listaUsuarios.get(i);
                try {
                    if (userService.getRoles(temp).get(0).getId() == GESTOR) {
                        listaGestores.add(temp);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            time_start = 0;
            time_start = System.currentTimeMillis();
            while (count < listaGestores.size()) {
                Usuario[] pagina = new Usuario[regsPerPage];
                for (int i = 0; i < regsPerPage; i++) {
                    Usuario reg;
                    if (count < listaGestores.size()) {
                        reg = listaGestores.get((int) count);
                    } else {
                        reg = null;
                    }
                    ++count;
                    if (reg == null) {
                        break;
                    } else {
                        pagina[i] = reg;
                    }
                }
                if (pagina[0] != null) {
                    prevPag.push(pagina);
                }
            }
            time_end = 0;
            time_end = System.currentTimeMillis();
            System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag " + (time_end - time_start) + " milliseconds");
        }

        time_start = 0;
        time_start = System.currentTimeMillis();
        while (!prevPag.isEmpty()) {
            nextPag.push(prevPag.pop());
        }
        time_end = 0;
        time_end = System.currentTimeMillis();
        System.out.println("\n\n\t\tTiempo empleado en  llenar Stack nextPag " + (time_end - time_start) + " milliseconds");
    }

    /*      métodos paginadores y reordenamiento de tabla para las listas de gestor     */

    @GetMapping("/reordenar/{view}/{orderBy}")
    public String sortUser(@PathVariable("view") String view, @PathVariable("orderBy") String orderBy, ModelMap model) {
        nextPag.emptyStack();
        prevPag.emptyStack();
        params.setView(Integer.parseInt(view));
        params.setOrderBy(Integer.parseInt(orderBy));
        getUsers(params.getOrderBy());
        getPaginas(params.getView(), params.getUserType());

        if (params.getUserType() == PARTICULAR) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortPartiParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "particulares";
        } else if (params.getUserType() == VETERINARIO) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortVetParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "veterinarios";
        } else {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortGestorParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "gestores";
        }
    }

    @GetMapping("/gestor/particular-list/next")
    public String nextPage(Model model) {
        if (nextPag.top() != null) {
            prevPag.push(nextPag.pop());
        } else {
            getPaginas(params.getView(), params.getUserType());
        }

        if (params.getUserType() == PARTICULAR) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortPartiParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "particulares";
        } else if (params.getUserType() == VETERINARIO) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortVetParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "veterinarios";
        } else {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortGestorParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "gestores";
        }
    }

    @GetMapping("/gestor/particular-list/previous")
    public String prevPage(Model model) {
        if (prevPag.top() != null) {
            nextPag.push(prevPag.pop());
        } else {
            getPaginas(params.getView(), params.getUserType());
        }

        if (params.getUserType() == PARTICULAR) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortPartiParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "particulares";
        } else if (params.getUserType() == VETERINARIO) {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortVetParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "veterinarios";
        } else {
            if (nextPag.top() != null) {
                model.addAttribute("regsPerPageArray", regsPerPage);
                model.addAttribute("sortParams", sortGestorParams);
                model.addAttribute("pagDefault", pagDefault);
                model.addAttribute("params", params);
                model.addAttribute("pagina", nextPag.top());
                model.addAttribute("prev", prevPag.top());
                model.addAttribute("next", nextPag.size());
            } else {
                model.addAttribute("pagina", null);
                model.addAttribute("prev", null);
                model.addAttribute("next", 0);
            }
            return "gestores";
        }
    }

    @GetMapping("/gestor/vet-list/next")
    public String nextPageVet(Model model) {
        return nextPage(model);
    }

    @GetMapping("/gestor/vet-list/previous")
    public String prevPageVet(Model model) {
        return prevPage(model);
    }

    @GetMapping("/gestor/gestor-list/next")
    public String nextPageGestor(Model model) {
        return nextPage(model);
    }

    @GetMapping("/gestor/gestor-list/previous")
    public String prevPageGestor(Model model) {
        return prevPage(model);
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
        /*if (user == null) {
            throw new Exception("El usuario no existe");
        }*/
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
            model.addAttribute("userRole", userService.getRoles(user));
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
                userService.mapUser(user, avl.find(user, avl.getRoot()));
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
        Usuario vet = new Usuario();
        vet.setId_usuario(id);
        vet = avl.find(vet, avl.getRoot());
        /*if (vet == null){
            throw new Exception("el usuario no existe");
        }*/
        model.addAttribute("user", vet);
        return "vetshow";
    }

    @GetMapping("/vet/edit/{id}")
    public String editVet(@PathVariable String id, Model model) {
        try {
            Usuario vet = new Usuario();
            vet.setId_usuario(id);
            vet = avl.find(vet, avl.getRoot());
            if (vet == null) {
                throw new Exception("el usuario no existe");
            }
            model.addAttribute("user", vet);
            model.addAttribute("userRole", userService.getRoles(vet));
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
                userService.mapUser(user, avl.find(user, avl.getRoot()));

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
        Usuario gestor = new Usuario();
        gestor.setId_usuario(id);
        gestor = avl.find(gestor, avl.getRoot());
        /*if (gestor == null){
            throw new Exception("El usuario no existe");
        }*/
        model.addAttribute("user", gestor);
        return "gestorshow";
    }

    @GetMapping("/gestor/edit/{id}")
    public String editGestor(@PathVariable String id, Model model) {
        try {
            Usuario gestor = new Usuario();
            gestor.setId_usuario(id);
            gestor = avl.find(gestor, avl.getRoot());
            if (gestor == null) {
                throw new Exception("El usuario no existe");
            }
            model.addAttribute("user", gestor);
            model.addAttribute("userRole", userService.getRoles(gestor));
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
                userService.mapUser(user, avl.find(user, avl.getRoot()));

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

    @RequestMapping("/gestor/searchUser")
    public String searchParticular(Model model) {
        model.addAttribute("searchUser", new Usuario());
        return "usersearch";
    }

    @RequestMapping(value = "/gestor/idsearch", method = RequestMethod.POST)
    public String searchParticular(Usuario idSearch, Model model) {
        Usuario user = new Usuario();
        user.setId_usuario(idSearch.getId_usuario());
        try {
            user = avl.find(user, avl.getRoot());
        } catch (Exception e) {
            user = null;
        }
        model.addAttribute("user", user);
        model.addAttribute("gestorConsult", true);
        return "particularshow";
    }

    @GetMapping("/gestor/editParticular/{id}")
    public String gestorEditParticular(@PathVariable String id, Model model) {
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
            model.addAttribute("gestorConsult", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = 1);
        model.addAttribute("gestorConsult", true);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/gestor/updateParticular")
    public String gestorUpdateParticular(/*@Valid*/ @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = 1);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
            return "formulario";
        } else {
            try {
                userService.updateUser(user);
                model.addAttribute("userCreated", true);
                userService.mapUser(user, avl.find(user, avl.getRoot()));
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("userCreated", false);
                model.addAttribute("edit", true);
                model.addAttribute("tipo", tipo = 1);
                model.addAttribute("gestorConsult", true);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("roles", roleRepo.findAll());
            return "particularshow";
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

    @RequestMapping(value = "/gestor/particular-list", method = RequestMethod.GET)
    public String listParticular(Model model) {
        params = new OrderPair(sortDefault, pagDefault);
        params.setUserType(PARTICULAR);
        getPaginas(params.getView(), PARTICULAR);

        if (nextPag.top() != null) {
            model.addAttribute("regsPerPageArray", regsPerPage);
            model.addAttribute("sortParams", sortPartiParams);
            model.addAttribute("pagDefault", pagDefault);
            model.addAttribute("params", params);
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

    @RequestMapping(value = "/gestor/vet-list", method = RequestMethod.GET)
    public String listVets(Model model) {
        params = new OrderPair(sortDefault, pagDefault);
        params.setUserType(VETERINARIO);
        getPaginas(params.getView(), VETERINARIO);

        if (nextPag.top() != null) {
            model.addAttribute("regsPerPageArray", regsPerPage);
            model.addAttribute("sortParams", sortVetParams);
            model.addAttribute("pagDefault", pagDefault);
            model.addAttribute("params", params);
            model.addAttribute("pagina", nextPag.top());
            model.addAttribute("prev", prevPag.top());
            model.addAttribute("next", nextPag.size());
        } else {
            model.addAttribute("pagina", null);
            model.addAttribute("prev", null);
            model.addAttribute("next", 0);
        }
        return "veterinarios";
    }

    @RequestMapping(value = "/gestor/gestor-list", method = RequestMethod.GET)
    public String listGestores(Model model) {
        params = new OrderPair(sortDefault, pagDefault);
        params.setUserType(GESTOR);
        getPaginas(params.getView(), GESTOR);

        if (nextPag.top() != null) {
            model.addAttribute("regsPerPageArray", regsPerPage);
            model.addAttribute("sortParams", sortGestorParams);
            model.addAttribute("pagDefault", pagDefault);
            model.addAttribute("params", params);
            model.addAttribute("pagina", nextPag.top());
            model.addAttribute("prev", prevPag.top());
            model.addAttribute("next", nextPag.size());
        } else {
            model.addAttribute("pagina", null);
            model.addAttribute("prev", null);
            model.addAttribute("next", 0);
        }
        return "gestores";
    }

}
