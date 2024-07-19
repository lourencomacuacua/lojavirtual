package com.projeto.sistema1.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.ItensCompra;

public interface ItensCompraRepositorio extends JpaRepository<ItensCompra,Long> {//Essá interface é para quando nós quisermos fazer comandos sql interagindo com o banco de dados em relação a consultas salvar,editar... vamos precissa do estado repositório
	
	
}
