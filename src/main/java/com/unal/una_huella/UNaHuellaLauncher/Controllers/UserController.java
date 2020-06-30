package com.unal.una_huella.UNaHuellaLauncher.Controllers;

import com.unal.una_huella.UNaHuellaLauncher.ED.AVLTree;
import com.unal.una_huella.UNaHuellaLauncher.ED.LinkedStack;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Jornada;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.RoleRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.JornadaService;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.MascotaService;
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
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    IndexController indexController;
    @Autowired
    JornadaService jornadaService;


    private static final int PARTICULAR = 1;
    private static final int VETERINARIO = 2;
    private static final int GESTOR = 3;


    LinkedStack<Usuario[]> prevPag = new LinkedStack<>();
    LinkedStack<Usuario[]> nextPag = new LinkedStack<>();

    int tipo = 0;

    int[] regsPerPage = {10, 20, 30, 50, 100, 500};
    int pagDefault = 10;
    int[] sortValues = {1, 2, 3, 4, 5, 6, 7};
    String[] sortNames = {"Documento", "Primer Nombre", "Primer Apellido", "N° de Mascotas", "Estrato", "Nivel de Acceso", "Experiencia"};
    int sortDefault = 1;
    OrderPair params;
    SortParams[] sortPartiParams = null;
    SortParams[] sortVetParams = null;
    SortParams[] sortGestorParams = null;

    AVLTree<Usuario> avl = null;
    AVLTree<Mascota> pets = null;


    /*Instant tInicial = Instant.now();
    double d = (double) tInicial.getEpochSecond() + (double) tInicial.getNano() / 1_000_000_000;*/
    Instant time_start;
    Instant time_end;

    DecimalFormat formatoDecimal = new DecimalFormat("0.000000000");


    @ModelAttribute
    public void addLoggedUserToView(Model model){
        model.addAttribute("loggedUser", userService.getLoggedUser());
    }

    @RequestMapping("/particular")
    public String particular(Model model) {
        if (avl == null) {
            getUsers(sortDefault);
        }
        if (pets == null) {
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

        List<Jornada> proximasJornadas = proximasJornadas();
        try {
            model.addAttribute("listaProxJornadas", proximasJornadas);
            model.addAttribute("proxJornada", proximasJornadas.get(0));
        } catch (Exception e){
            System.err.println("No se han podido cargar las jornadas");
        }

        return "particular";
    }

    @RequestMapping("/gestor")
    String gestor(Model model) {
        SortParams temp;

        if (sortPartiParams == null || sortVetParams == null || sortGestorParams == null) {
            sortPartiParams = new SortParams[3];
            sortVetParams = new SortParams[3];
            sortGestorParams = new SortParams[3];
            for (int i = 0; i < sortPartiParams.length; i++) {
                temp = new SortParams(sortNames[i], sortValues[i]);
                sortPartiParams[i] = temp;
            }

            for (int i = 0; i < sortVetParams.length; i++) {
                temp = new SortParams(sortNames[i], sortValues[i]);
                sortVetParams[i] = temp;
            }
            /*temp = new SortParams(sortNames[6], sortValues[6]);
            sortVetParams[3] = temp;*/

            for (int i = 0; i < sortGestorParams.length; i++) {
                temp = new SortParams(sortNames[i], sortValues[i]);
                sortGestorParams[i] = temp;
            }
            /*temp = new SortParams(sortNames[5], sortValues[5]);
            sortGestorParams[3] = temp;*/
        }
        if (avl == null) {
            getUsers(sortDefault);
        }

        List<Jornada> proximasJornadas = proximasJornadas();
        try {
            model.addAttribute("listaProxJornadas", proximasJornadas);
            model.addAttribute("proxJornada", proximasJornadas.get(0));
        } catch (Exception e){
            System.err.println("No se han podido cargar las jornadas");
        }


        return "gestor";
    }

    @RequestMapping("/vet")
    public String vet(Model model) {
        if (avl == null) {
            getUsers(sortDefault);
        }
        List<Jornada> proximasJornadas = proximasJornadas();
        try {
            model.addAttribute("listaProxJornadas", proximasJornadas);
            model.addAttribute("proxJornada", proximasJornadas.get(0));
        } catch (Exception e){
            System.err.println("No se han podido cargar las jornadas");
        }
        return "veterinario";
    }

    public AVLTree<Mascota> getMascotas() {
        return pets;
    }

    public AVLTree<Usuario> getUsers(int sortBy) {
        if (avl == null) {
            avl = new AVLTree<Usuario>(AVLTree.ID);

            time_start = Instant.now();

            for (Usuario user : userService.listAllUser()) {
                try {
                    avl.insertAVL(user);
                    //System.out.println("ingresado en el arbol: " + user.toString());
                } catch (Exception e) {
                    System.err.println("no se pudo insertar el usuario con id " + user.getId_usuario() + " al arbol por parametro de ordenamiento erroneo");
                }
            }

            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado en crear arbol AVL de usuarios: " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
        } else {
            if (avl.getOrder() != sortBy) {
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

                time_start = Instant.now();

                for (Usuario user : userService.listAllUser()) {
                    try {
                        avl.insertAVL(user);
                        //System.out.println("ingresado en el arbol: " + user.toString());
                    } catch (Exception e) {
                        System.err.println("no se pudo insertar el usuario con id " + user.getId_usuario() + " al arbol por parametro de ordenamiento erroneo");
                    }
                }

                time_end = Instant.now();
                System.out.println("\n\n\t\tTiempo empleado en reconstruir arbol AVL de usuarios: " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
            }
        }
        return avl;
    }

    public void getPaginas(int regsPerPage, int tipo) {
        nextPag.emptyStack();
        prevPag.emptyStack();

        long count = 0;
        java.util.List<Usuario> listaUsuarios = avl.getList(tipo);

        time_start = Instant.now();
        double time_temp_start = 0;
        double time_temp_end = 0;
        double time_acumulado = 0;
        while (count < listaUsuarios.size()) {
            Usuario[] pagina = new Usuario[regsPerPage];
            for (int i = 0; i < regsPerPage; i++) {
                Usuario reg;
                if (count < listaUsuarios.size()) {
                    reg = listaUsuarios.get((int) count);
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
                time_temp_start = System.currentTimeMillis();
                prevPag.push(pagina);
                time_temp_end = System.currentTimeMillis();
                time_acumulado += time_temp_end - time_temp_start;
            }
        }
        time_end = Instant.now();
        System.out.println("\n\n\t\tTiempo empleado en llenar Stack prevPag " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");

        time_start = Instant.now();
        while (!prevPag.isEmpty()) {
            nextPag.push(prevPag.pop());
        }
        time_end = Instant.now();
        System.out.println("\n\n\t\tTiempo empleado en  llenar Stack nextPag " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
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

    @RequestMapping("/particular/inscribirMascota")
    String inscribirMascota() {
        return "inscribirMascota";
    }

    @RequestMapping("/particular/citas/new")
    String asignarCita() {
        return "asignarCita";
    }

    /*  de aquí para abajo van controladores de usuario unificado   */

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

    @GetMapping("/editUser/cancel")
    public String cancelEditUser() {
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String getUserList(Model model) {
        model.addAttribute("usuarios", userService.listAllUser());
        return "usuarios";
    }

    @RequestMapping("/particular/profile/{id}")
    public String partiProfile(@PathVariable String id, Model model) throws Exception {
        Usuario user = userService.getUserById(id);
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
            Usuario user = userService.getUserById(id);
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
    public String updateParticular(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
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
        Usuario vet = userService.getUserById(id);
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
            Usuario vet = userService.getUserById(id);
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
    public String updateVet(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
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
        Usuario gestor = userService.getUserById(id);
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
            Usuario gestor = userService.getUserById(id);
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
    public String updateGestor(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
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
    public String searchUser(Model model) {
        model.addAttribute("searchUser", new Usuario());
        return "usersearch";
    }

    @RequestMapping(value = "/gestor/idsearch", method = RequestMethod.POST)
    public String searchUser(Usuario idSearch, Model model) {

        Usuario user = new Usuario();
        try {
            user = userService.getUserById(idSearch.getId_usuario());
            time_start = Instant.now();
            user = avl.find(user, avl.getRoot());
            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado en buscar registro en arbol " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
        } catch (Exception e) {
            user = null;
        }
        model.addAttribute("user", user);
        model.addAttribute("gestorConsult", true);
        if (user == null || userService.getRoles(user).get(0).getId() == PARTICULAR) {
            return "particularshow";
        } else if (userService.getRoles(user).get(0).getId() == VETERINARIO) {
            return "vetshow";
        } else {
            return "gestorshow";
        }

    }

    @GetMapping("/gestor/viewParticular/{id}")
    public String gestorViewParticular(@PathVariable String id, Model model) {
        Usuario user = new Usuario();
        user.setId_usuario(id);
        return searchUser(user, model);
    }

    @GetMapping("/gestor/editParticular/{id}")
    public String gestorEditParticular(@PathVariable String id, Model model) {
        try {
            Usuario user = userService.getUserById(id);
            user = avl.find(user, avl.getRoot());
            if (user == null) {
                throw new Exception("El usuario no existe");
            }
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = PARTICULAR);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = PARTICULAR);
        model.addAttribute("gestorConsult", true);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/gestor/updateParticular")
    public String gestorUpdateParticular(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = PARTICULAR);
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
                model.addAttribute("tipo", tipo = PARTICULAR);
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

    @GetMapping("/gestor/viewVet/{id}")
    public String gestorViewVet(@PathVariable String id, Model model) {
        Usuario user = new Usuario();
        user.setId_usuario(id);
        return searchUser(user, model);
    }

    @GetMapping("/gestor/editVet/{id}")
    public String gestorEditVet(@PathVariable String id, Model model) {
        try {
            Usuario user = userService.getUserById(id);
            user = avl.find(user, avl.getRoot());
            if (user == null) {
                throw new Exception("El usuario no existe");
            }
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = VETERINARIO);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = VETERINARIO);
        model.addAttribute("gestorConsult", true);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/gestor/updateVet")
    public String gestorUpdateVet(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = VETERINARIO);
            ;
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
                model.addAttribute("tipo", tipo = VETERINARIO);
                model.addAttribute("gestorConsult", true);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("roles", roleRepo.findAll());
            return "vetshow";
        }
    }

    @GetMapping("/gestor/viewGestor/{id}")
    public String gestorViewGestor(@PathVariable String id, Model model) {
        Usuario user = new Usuario();
        user.setId_usuario(id);
        return searchUser(user, model);
    }

    @GetMapping("/gestor/editGestor/{id}")
    public String gestorEditGestor(@PathVariable String id, Model model) {
        try {
            Usuario user = userService.getUserById(id);
            user = avl.find(user, avl.getRoot());
            if (user == null) {
                throw new Exception("El usuario no existe");
            }
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = GESTOR);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("userCreated", false);
            model.addAttribute("roles", roleRepo.findAll());
        }
        model.addAttribute("edit", true);
        model.addAttribute("tipo", tipo = GESTOR);
        model.addAttribute("gestorConsult", true);
        model.addAttribute("userCreated", false);
        model.addAttribute("roles", roleRepo.findAll());
        return "formulario";
    }

    @PostMapping("/gestor/updateGestor")
    public String gestorUpdateGestor(@Valid @ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("tipo", tipo = GESTOR);
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
                model.addAttribute("tipo", tipo = GESTOR);
                model.addAttribute("gestorConsult", true);
                model.addAttribute("roles", roleRepo.findAll());
                return "formulario";
            }
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("gestorConsult", true);
            model.addAttribute("roles", roleRepo.findAll());
            return "gestorshow";
        }
    }

    @GetMapping("/gestor/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        try {
            Usuario user = new Usuario();
            user.setId_usuario(id);
            userService.deleteUser(id);
            time_start = Instant.now();
            avl.deleteAVL(user);
            time_end = Instant.now();
            System.out.println("\n\n\t\tTiempo empleado en eliminar registro de arbol " + formatoDecimal.format(((double) time_end.getEpochSecond() + (double) time_end.getNano() / 1_000_000_000) - ((double) time_start.getEpochSecond() + (double) time_start.getNano() / 1_000_000_000)) + " segundos");
            model.addAttribute("deleteSuccess", true);
            model.addAttribute("deleteError", false);
            model.addAttribute("deleteSuccessMessage", "Usuario eliminado");
        } catch (Exception e) {
            model.addAttribute("deleteSuccess", false);
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMessage", e.getMessage());
        }
        if (params.getUserType() == PARTICULAR) {
            return "redirect:/gestor/particular-list";
        } else if (params.getUserType() == VETERINARIO) {
            return "redirect:/gestor/vet-list";
        } else {
            return "redirect:/gestor/gestor-list";
        }

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

    public List<Jornada> getJornadas (){
        List<Jornada> listaJornadas = new ArrayList<Jornada>();
        for (Jornada jornada:jornadaService.listAllJornadas()) {
            listaJornadas.add(jornada);
        }

        return listaJornadas;
    }

    public List<Jornada> proximasJornadas (){
        //fecha-mes-dia
        Date fechaActual = new Date(System.currentTimeMillis());
        List<Jornada> proximasJornadas = new ArrayList<Jornada>();
        for (Jornada jornada:getJornadas()) {
            if (jornada.getB_fecha_jornada().after(fechaActual)){
                proximasJornadas.add(jornada);
            }
        }
        return proximasJornadas;
    }
}
