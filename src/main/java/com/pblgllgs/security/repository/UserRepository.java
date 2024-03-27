package com.pblgllgs.security.repository;
/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import com.pblgllgs.security.entities.User;
import com.pblgllgs.security.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByRole(Role role);
}
