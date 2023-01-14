package com.br.generate_token_new_version.repository;

import com.br.generate_token_new_version.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoditory extends JpaRepository<User, Long> {

    @Query(value = "select user from tbl_user user where user.username = ?1")
    public Optional<User> findByUsername(String username);

}
