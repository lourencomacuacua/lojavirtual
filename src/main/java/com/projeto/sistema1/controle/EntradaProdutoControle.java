package com.projeto.sistema1.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.EntradaProduto;
import com.projeto.sistema1.modelos.Produto;
import com.projeto.sistema1.modelos.EntradaItemProduto;
import com.projeto.sistema1.repositorios.EntradaItemRepositorio;
import com.projeto.sistema1.repositorios.EntradaProdutoRepositorio;
import com.projeto.sistema1.repositorios.FornecedorRepositorio;
import com.projeto.sistema1.repositorios.FuncionarioRepositorio;
import com.projeto.sistema1.repositorios.ProdutoRepositorio;

@Controller
public class EntradaProdutoControle {
	private List<EntradaItemProduto> listaEntradaItens = new ArrayList<EntradaItemProduto>();
	
	@Autowired
	private EntradaProdutoRepositorio entradaProdutoRepositorio;
	@Autowired
	private EntradaItemRepositorio entradaItemRepositorio;
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	@Autowired
	private FornecedorRepositorio fornecedorRepositorio;
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	
	@GetMapping("/cadastrarEntradaProdutoo")
	public ModelAndView cadastrar(EntradaProduto entradaProduto,EntradaItemProduto entradaItem){// A entrada a lista e um item específico. Esses iens de emntrada vamos adicionar na lista de entrada, e aproveito essa lista para mostrar os dados na tabela
		ModelAndView mv= new ModelAndView("administrativo/entradaProdutoss/cadastro");
		mv.addObject("entradaProduto",entradaProduto);
		mv.addObject("listaEntradaItens",this.listaEntradaItens);
		mv.addObject("entradaItem",entradaItem);
		mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
		mv.addObject("listaFornecedores",fornecedorRepositorio.findAll());
		mv.addObject("listaProdutos",produtoRepositorio.findAll());
		return mv;
	
	}
	
	@GetMapping("/listarEntradaProdutoo")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/entradaProdutoss/lista");
		mv.addObject("listaEntradaProdutos",entradaProdutoRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/salvarEntradaProduto")
	public ModelAndView salvar(String acao,EntradaProduto entradaProduto, EntradaItemProduto entradaItemProduto,Model model) {
		//Esse método podemos aproveitar para diferentes ações, ou seja, em determinado momento podemos charmar esse método para adicionar um item na lista e em outro momento para salvar essa informações no banco de dados, porque  o usuarário vai adicionando os itens na lista e quando adicionar todos esses itens ele vai finalizar essa entrada
		
		if(acao.equals("itens")) {//Se o usuarário quiser adicionar itens
			this.listaEntradaItens.add(entradaItemProduto);	
		}else if(acao.equals("salvar")) {
			entradaProdutoRepositorio.saveAndFlush(entradaProduto);
			for(EntradaItemProduto it: listaEntradaItens) {
				it.setEntradaProduto(entradaProduto);
				entradaItemRepositorio.saveAndFlush(it);
				Optional<Produto> produtoOptional=produtoRepositorio.findById(it.getProduto().getId());
				Produto produto=produtoOptional.get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setValorVenda(it.getValorVenda());
				produtoRepositorio.saveAndFlush(produto);
	    		String mensagem = "Entrada de produtos realizada com sucesso!";
	    		model.addAttribute("message", mensagem);
				this.listaEntradaItens=new ArrayList<>();
								
			}
			return cadastrar(new EntradaProduto(), new EntradaItemProduto());
			
		}
		//System.out.println(this.listaEntradaItens.size());
		
		return cadastrar(entradaProduto,new EntradaItemProduto());//depois do usuário adicionar criamos nova isntância de entrada
		
	}
	
//	@GetMapping("/editarEntradaProduto/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<EntradaProduto> entradaProduto=entradaProdutoRepositorio.findById(id);
//		return cadastrar(entradaProduto.get());
//	}
//	
//	@GetMapping("/removerEntradaProduto/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<EntradaProduto> entradaProduto=entradaProdutoRepositorio.findById(id);
//		entradaProdutoRepositorio.delete(entradaProduto.get());
//		return listar();
//	}
	

}
