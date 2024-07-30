package com.projeto.sistema1.controle;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Fornecedor;
import com.projeto.sistema1.repositorios.FornecedorRepositorio;

@Controller
public class FornecedorControle {
	@Autowired
	private FornecedorRepositorio fornecedorRepositorio;
	
	@GetMapping("cadastrarFornecedor")
	public ModelAndView cadastrar(Fornecedor fornecedor){
		ModelAndView mv= new ModelAndView("administrativo/fornecedoress/cadastro");
		mv.addObject("fornecedor",fornecedor);
		
		return mv;
	
	}
	
	@GetMapping("/listarFornecedor")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/fornecedoress/lista");
		mv.addObject("listaFornecedors",fornecedorRepositorio.findAll());
		return mv;

	}
	@PostMapping("/salvarFornecedor")
	public ModelAndView salvar( Fornecedor fornecedor,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return cadastrar(fornecedor);
		}
		//Temos que emcriptar a senha original do funionario antes de salvar
		//fornecedor.setSenha(new BCryptPasswordEncoder().encode(fornecedor.getSenha()));//'e importante que no momento que estivarmos alterando um determinado funcionário, devo alterara sua senha se não avai encriptar uma senha encriptada e o funcinácrio nao vai conhecer mais a sua senha.Deficinencia do sistema mas vamos implementar uma funcionalidade de recuperar senha 
		fornecedorRepositorio.saveAndFlush(fornecedor);
		String mensagem = "Fornecedor '" + fornecedor.getNome() + "' salvo com sucesso!";
		model.addAttribute("message", mensagem);
		return cadastrar(new Fornecedor());
		
	}
	
	@GetMapping("/editarFornecedor/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Fornecedor> fornecedor=fornecedorRepositorio.findById(id);
		return cadastrar(fornecedor.get());
	}
	
	@GetMapping("/removerFornecedor/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Fornecedor> fornecedor=fornecedorRepositorio.findById(id);
		fornecedorRepositorio.delete(fornecedor.get());
		return listar();
	}
	

}
