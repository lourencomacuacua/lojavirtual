package com.projeto.sistema1.controle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Funcao       ;
import com.projeto.sistema1.repositorios.FuncaoRepositorio;

import jakarta.persistence.criteria.Path;

@Controller
public class FuncaoControle {
	@Autowired
	private FuncaoRepositorio funcaoRepositorio;
	
	@GetMapping("/cadastrarFuncaoo")
	public ModelAndView cadastrar(Funcao funcao){
		ModelAndView mv= new ModelAndView("/administrativo/funcoess/cadastro");
		mv.addObject("funcao",funcao);
		return mv;
	
	}
	
	@GetMapping("/listarFuncaoo")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("/administrativo/funcoess/lista");
		mv.addObject("listaFuncoes",funcaoRepositorio.findAll());
		return mv;

	}
	@PostMapping("/salvarFuncao")
	public ModelAndView salvar(Model model,Funcao funcao, BindingResult result) {
	    if (result.hasErrors()) {
	        return cadastrar(funcao);
	    }
	    funcaoRepositorio.saveAndFlush(funcao);
	    model.addAttribute("mensagem","Salvo com sucesso");
		return cadastrar(new Funcao());   

	}
	
	
	
	@GetMapping("/editarFuncao/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcao> funcao=funcaoRepositorio.findById(id);
		return cadastrar(funcao.get());
	}
	
	@GetMapping("/removerFuncao/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcao> funcao=funcaoRepositorio.findById(id);
		funcaoRepositorio.delete(funcao.get());
		return listar();
	}
	

	

}
