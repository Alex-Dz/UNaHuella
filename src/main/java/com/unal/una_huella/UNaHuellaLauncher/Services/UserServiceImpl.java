package com.unal.una_huella.UNaHuellaLauncher.Services;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;
import com.unal.una_huella.UNaHuellaLauncher.Repositories.UserRepo;
import com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Usuario> listAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Usuario getUserById(Long id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(() -> new Exception("El usuario no existe"));
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        return userRepo.save(usuario);
    }

    @Override
    public Usuario createUser(Usuario user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)){

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            user = userRepo.save(user);
        }
        return user;
    }

    @Override
    public Usuario updateUser(Usuario user) throws Exception {
        Usuario storedUser = getUserById(user.getId());
        mapUser(user, storedUser);
        return userRepo.save(storedUser);
    }

    public void mapUser(Usuario from, Usuario to){
        to.setUsername(from.getUsername());
        to.setRoles(from.getRoles());
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Usuario user = getUserById(id);
        userRepo.delete(user);

    }

    private boolean checkUsernameAvailable(Usuario user) throws Exception {
        Optional<Usuario> userFound = userRepo.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("Username no disponible");
        }
        return true;
    }

    private boolean checkPasswordValid(Usuario user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Campo contraseña y confirmar contraseña no coinciden");
        }
        return true;
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepo.findById(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDet = new User(user.getUsername(), user.getPassword(), roles);
        return userDet;
    }*/
}
