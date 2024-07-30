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

import com.projeto.sistema1.modelos.ItensCompra;
import com.projeto.sistema1.modelos.ItensCompra;
import com.projeto.sistema1.repositorios.ItensCompraRepositorio;

import jakarta.persistence.criteria.Path;

@Controller
public class ItensCompraControle {
	private static String caminhoImagens="C://Users//Penicela//Documents//imagens//";
	@Autowired
	private ItensCompraRepositorio itensCompraRepositorio;
	

	@GetMapping("/listarItensCompra")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/itensComprass/lista");
		mv.addObject("listaItensCompras",itensCompraRepositorio.findAll());
		return mv;

	}
	
	@GetMapping("/removerItensCompra/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<ItensCompra> itensItensCompra=itensCompraRepositorio.findById(id);
		itensCompraRepositorio.delete(itensItensCompra.get());
		return listar();
	}

	

}
