package com.projeto.sistema1.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="funcionario")//cara caso mudarmos do servidor que ospeda o nosso site para linux nao termos problemas
public class Funcionario  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cargo;
	private String nuit;
	private String numeroBi;
	private String salarioBruto;
	private String email;
	private String senha;
	@OneToMany(mappedBy = "funcionario",cascade=CascadeType.REMOVE)
	private List<User> users;
	
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSalarioBruto() {
		return salarioBruto;
	}
	public void setSalarioBruto(String salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNuit() {
		return nuit;
	}
	public void setNuit(String nuit) {
		this.nuit = nuit;
	}
	public String getNumeroBi() {
		return numeroBi;
	}
	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}

}