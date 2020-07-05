package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;


import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Role;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;

import java.util.List;

public interface UserService {

    List<Usuario> listAllUser();

    Usuario getUserById(String id) throws Exception;

    List<Role> getRoles(Usuario user);

    Usuario createUser(Usuario user) throws Exception;

    public List<Mascota> getMascotas (Usuario user) throws Exception;

    void deleteUser(String id) throws Exception;

    Usuario updateUser(Usuario user) throws Exception;

    void mapUser(Usuario from, Usuario to);

    boolean checkUsernameAvailable(Usuario user) throws Exception;

    boolean checkPasswordValid(Usuario user) throws Exception;

    Usuario getLoggedUser();
}
