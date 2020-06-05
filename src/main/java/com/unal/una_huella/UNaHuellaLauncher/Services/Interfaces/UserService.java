package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;


import com.unal.una_huella.UNaHuellaLauncher.Entities.Particular;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;

import java.util.List;

public interface UserService {

    List<Usuario> listAllUser();

    Usuario getUserById(Long id) throws Exception;

    Usuario saveUser(Usuario usuario);

    Usuario createUser(Usuario user) throws Exception;

    void deleteUser(Long id) throws Exception;

    Usuario updateUser(Usuario user) throws Exception;
}
