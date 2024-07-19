package com.projeto.sistema1.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sistema1.modelos.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail (String email);
	



}
