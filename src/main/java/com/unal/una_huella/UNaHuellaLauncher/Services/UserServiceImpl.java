package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Role;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.UserRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Usuario> listAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Usuario getUserById(String id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(() -> new Exception("El usuario no existe"));
    }

    @Override
    public List<Role> getRoles(Usuario user) {
        try {
            return getUserById(user.getId_usuario()).getRoles();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario createUser(Usuario user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)) {

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            user = userRepo.save(user);
        }
        return user;
    }

    @Override
    public Usuario updateUser(Usuario user) throws Exception {
        Usuario storedUser = getUserById(user.getId_usuario());
        mapUser(user, storedUser);
        return userRepo.save(storedUser);
    }

    @Override
    public void mapUser(Usuario from, Usuario to) {      //  copia todos los atributos del usuario menos contraseñas para actualizar en la BBDD
        to.setA_primer_nombre(from.getA_primer_nombre());
        to.setB_primer_apellido(from.getB_primer_apellido());
        to.setC_direccion(from.getC_direccion());
        to.setD_telefono(from.getD_telefono());
        to.setE_segundo_nombre(from.getE_segundo_nombre());
        to.setF_segundo_apellido(from.getF_segundo_apellido());
        to.setG_correo(from.getG_correo());
        to.setH_cantidad_mascotas(from.getH_cantidad_mascotas());
        to.setI_estrato(from.getI_estrato());
        to.setJ_funciones(from.getJ_funciones());
        to.setK_nivel_acceso(from.getK_nivel_acceso());
        to.setL_num_tarjetaprof(from.getL_num_tarjetaprof());
        to.setM_especializacion(from.getM_especializacion());
        to.setN_anos_experiencia(from.getN_anos_experiencia());
        //to.setRoles(from.getRoles());
    }

    @Override
    public void deleteUser(String id) throws Exception {
        Usuario user = getUserById(id);
        userRepo.delete(user);

    }

    private boolean checkUsernameAvailable(Usuario user) throws Exception {
        Optional<Usuario> userFound = userRepo.findById(user.getId_usuario());
        if (userFound.isPresent()) {
            throw new Exception("Ya existe un registro con este documento");
        }
        return true;
    }

    private boolean checkPasswordValid(Usuario user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Campo contraseña y confirmar contraseña no coinciden");
        }
        return true;
    }
}
