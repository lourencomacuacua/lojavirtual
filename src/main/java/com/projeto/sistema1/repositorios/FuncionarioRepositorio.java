package com.projeto.sistema1.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario,Long> {//Essá interface é para quando nós quisermos fazer comandos sql interagindo com o banco de dados em relação a consultas salvar,editar... vamos precissa do estado repositório
	
	
}
