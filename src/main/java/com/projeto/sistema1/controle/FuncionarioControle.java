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

import com.projeto.sistema1.modelos.Funcionario;
import com.projeto.sistema1.repositorios.FuncionarioRepositorio;

@Controller
public class FuncionarioControle {
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@GetMapping("/cadastrarFuncionarioo")
	public ModelAndView cadastrar(Funcionario funcionario){
		ModelAndView mv= new ModelAndView("/administrativo/funcionarioss/cadastro");
		mv.addObject("funcionario",funcionario);
		
		return mv;
	
	}
	
	@GetMapping("/listarFuncionarioo")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("/administrativo/funcionarioss/lista");
		mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
		return mv;

	}
	@PostMapping("/salvarFuncionario")
	public ModelAndView salvar( Funcionario funcionario,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}
		//Temos que emcriptar a senha original do funionario antes de salvar
		//funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));//'e importante que no momento que estivarmos alterando um determinado funcionário, devo alterara sua senha se não avai encriptar uma senha encriptada e o funcinácrio nao vai conhecer mais a sua senha.Deficinencia do sistema mas vamos implementar uma funcionalidade de recuperar senha 
		funcionarioRepositorio.saveAndFlush(funcionario);
		String mensagem = "Funcionario '" + funcionario.getNome() + "' salvo com sucesso!";
		model.addAttribute("message", mensagem);
		return cadastrar(new Funcionario());
		
	}
	
	@GetMapping("/editarFuncionario/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario=funcionarioRepositorio.findById(id);
		return cadastrar(funcionario.get());
	}
	
	@GetMapping("/removerFuncionario/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario=funcionarioRepositorio.findById(id);
		funcionarioRepositorio.delete(funcionario.get());
		return listar();
	}
	

}
