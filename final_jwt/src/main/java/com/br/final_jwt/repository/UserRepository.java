package com.br.final_jwt.repository;

import com.br.final_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="select user from tbl_user user where user.username = ?1")
    public Optional<User> findByUsername(String username);

}
