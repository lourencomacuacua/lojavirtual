package com.projeto.sistema1.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {//Essá interface é para quando nós quisermos fazer comandos sql interagindo com o banco de dados em relação a consultas salvar,editar... vamos precissa do estado repositório
//	@Query("from Cliente where email=?1")//esse ?1 dizer que 'e o primeiro element d paramentro
//	List<Cliente> buscarClientePorEmail(String email);
//	
}
