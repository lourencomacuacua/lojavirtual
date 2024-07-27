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

import com.projeto.sistema1.modelos.Produto;
import com.projeto.sistema1.repositorios.ProdutoRepositorio;

import jakarta.persistence.criteria.Path;

@Controller
public class ProdutoControle {
	private static String caminhoImagens="imagens";

	/*
	
	C:\Users\Penicela\Desktop\loja5\lojavirtual\imagens
	*/ 
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping("/cadastrarProdutoo")
	public ModelAndView cadastrar(Produto produto){
		ModelAndView mv= new ModelAndView("/administrativo/produtoss/cadastro");
		mv.addObject("produto",produto);
		return mv;
	
	}
	
	@GetMapping("/listarProdutoo")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("/administrativo/produtoss/lista");
		mv.addObject("listaProdutos",produtoRepositorio.findAll());
		return mv;

	}
	@PostMapping("/salvarProduto")
	public ModelAndView salvar(Produto produto, BindingResult result, @RequestParam("file") MultipartFile arquivo,Model model) {
	    if (result.hasErrors()) {
	        return cadastrar(produto);
	    }
	    produtoRepositorio.saveAndFlush(produto);   
	    try {
	        if (!arquivo.isEmpty()) {
	            byte[] bytes = arquivo.getBytes();
	            java.nio.file.Path caminho = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
	            
	            // Escrevendo bytes no arquivo
	            Files.write(caminho, bytes);
	            
	            produto.setNomeImagenm(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());//na nosa view assim que nos renderizarmos a pagina vou chamar um determidado metodo no controle e esse metodo vai buscar a imagem na past e vai retornar e vamos para para esse métod o nome da imaem
	            produtoRepositorio.saveAndFlush(produto);
	    		String mensagem = "Produto '" + produto.getDescricao() + "' salvo com sucesso!";
	    		model.addAttribute("message", mensagem);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return cadastrar(new Produto());
	}
	
	
	
	@GetMapping("/editarProduto/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto=produtoRepositorio.findById(id);
		return cadastrar(produto.get());
	}
	
	@GetMapping("/removerProduto/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto=produtoRepositorio.findById(id);
		produtoRepositorio.delete(produto.get());
		return listar();
	}
	
	@GetMapping("/mostarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) {
		File imagemArquivo= new File(caminhoImagens+imagem);//o caminho e nome dela que voi gravado no banco de dados
		if(imagem!=null || imagem.trim().length() >0) {
			try {
				return Files.readAllBytes(imagemArquivo.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		

	}
	

}
