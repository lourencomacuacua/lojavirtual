package com.projeto.sistema1.dto;

import com.projeto.sistema1.modelos.Funcionario;
import com.projeto.sistema1.modelos.User;

public class UserDto {
	private Long id;
	private String email;
	private String password;
	private String role;
	private String fullname;
	private Funcionario funcionario;

	

	
	public UserDto(Long id, String email, String password, String role, String fullname, Funcionario funcionario) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.funcionario = funcionario;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	

}