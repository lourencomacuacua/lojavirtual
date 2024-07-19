package com.projeto.sistema1.controle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Permissao;
import com.projeto.sistema1.repositorios.FuncaoRepositorio;
import com.projeto.sistema1.repositorios.FuncionarioRepositorio;
import com.projeto.sistema1.repositorios.PermissaoRepositorio;

import jakarta.persistence.criteria.Path;

@Controller
public class PermissaoControle {
	@Autowired
	private PermissaoRepositorio permissaoRepositorio;
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	@Autowired
	private FuncaoRepositorio funcaoRepositorio;
	
	@GetMapping("/cadastrarPermissaoo")
	public ModelAndView cadastrar(Permissao permissao){
		ModelAndView mv= new ModelAndView("/administrativo/permissoess/cadastro");
		mv.addObject("permissao",permissao);
		mv.addObject("ListaFuncionarios",funcionarioRepositorio.findAll());
		mv.addObject("listaFuncoes",funcaoRepositorio.findAll());
		
		return mv;
	
	}
	
	@GetMapping("/listarPermissaoo")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("/administrativo/permissoess/lista");
		mv.addObject("listaPermissoes",permissaoRepositorio.findAll());
		return mv;

	}
	@PostMapping("/salvarPermissao")
	public ModelAndView salvar(Permissao permissao, BindingResult result) {
	    if (result.hasErrors()) {
	        return cadastrar(permissao);
	    }
	    permissaoRepositorio.saveAndFlush(permissao);
		return cadastrar(new Permissao());   

	}
	
	
	
	@GetMapping("/editarPermissao/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao=permissaoRepositorio.findById(id);
		return cadastrar(permissao.get());
	}
	
	@GetMapping("/removerPermissao/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao=permissaoRepositorio.findById(id);
		permissaoRepositorio.delete(permissao.get());
		return listar();
	}
	

	

}
