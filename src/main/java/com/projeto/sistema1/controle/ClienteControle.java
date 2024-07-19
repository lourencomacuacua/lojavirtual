package com.projeto.sistema1.controle;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Cliente;
import com.projeto.sistema1.repositorios.ClienteRepositorio;

@Controller
public class ClienteControle {
	@Autowired
	private ClienteRepositorio clienteRepositorio;

	
	@GetMapping("/cadastrarCliente")
	public ModelAndView cadastrar(Cliente cliente){
		ModelAndView mv= new ModelAndView("cliente/cadastro");
		mv.addObject("cliente",cliente);
		return mv;
	
	}
	
	@PostMapping("/salvarCliente")
	public ModelAndView salvar( Cliente cliente,BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(cliente);
		}
		//Temos que emcriptar a senha original do cliente antes de salvar
		//cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));//'e importante que no momento que estivarmos alterando um determinado funcionário, devo alterara sua senha se não avai encriptar uma senha encriptada e o funcinácrio nao vai conhecer mais a sua senha.Deficinencia do sistema mas vamos implementar uma funcionalidade de recuperar senha 
		clienteRepositorio.saveAndFlush(cliente);		
		return cadastrar(new Cliente());
		
	}
	
	@GetMapping("/editarCliente/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente=clienteRepositorio.findById(id);
		return cadastrar(cliente.get());
	}

	

}
