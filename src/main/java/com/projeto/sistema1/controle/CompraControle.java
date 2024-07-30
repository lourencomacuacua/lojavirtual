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

import com.projeto.sistema1.modelos.Compra;
import com.projeto.sistema1.modelos.Compra;
import com.projeto.sistema1.repositorios.CompraRepositorio;

import jakarta.persistence.criteria.Path;

@Controller
public class CompraControle {
	private static String caminhoImagens="src/main/resources/static/imagens";
	@Autowired
	private CompraRepositorio compraRepositorio;
	

	@GetMapping("/listarCompra")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/comprass/lista");
		mv.addObject("listaCompras",compraRepositorio.findAll());
		return mv;

	}
	
	@GetMapping("/removerCompra/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Compra> compra=compraRepositorio.findById(id);
		compraRepositorio.delete(compra.get());
		return listar();
	}

	

}
