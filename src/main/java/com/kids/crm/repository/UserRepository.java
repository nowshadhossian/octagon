package com.kids.crm.repository;

import com.kids.crm.model.Role;
import com.kids.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findById(long id);
    List<User> findByRole(Role role);

}
