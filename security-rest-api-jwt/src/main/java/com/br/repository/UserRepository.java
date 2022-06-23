package com.br.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username = ?1")
	public Optional<User> findByUsername(String username);
}
