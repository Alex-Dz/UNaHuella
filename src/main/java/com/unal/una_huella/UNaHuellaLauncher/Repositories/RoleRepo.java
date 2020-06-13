package com.unal.una_huella.UNaHuellaLauncher.Repositories;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
}
