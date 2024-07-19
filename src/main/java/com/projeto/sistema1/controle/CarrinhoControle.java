package com.projeto.sistema1.controle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema1.modelos.Cliente;
import com.projeto.sistema1.modelos.Compra;
import com.projeto.sistema1.modelos.ItensCompra;
import com.projeto.sistema1.modelos.Produto;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.ClienteRepositorio;
import com.projeto.sistema1.repositorios.CompraRepositorio;
import com.projeto.sistema1.repositorios.ItensCompraRepositorio;
import com.projeto.sistema1.repositorios.ProdutoRepositorio;
import com.projeto.sistema1.repositorios.UserRepository;

import jakarta.persistence.criteria.Path;

@Controller
public class CarrinhoControle {
	private List<ItensCompra> listaItensCompras= new ArrayList<ItensCompra>();
	private Compra compra=new Compra();
	private Cliente cliente;
	private User user;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private CompraRepositorio compraRepositorio;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItensCompraRepositorio itensCompraRepositorio;
	

	
	
	
	//isso tinha que estar um um service
	public void calcularTotal() {
		compra.setValorTotal(0.);
		for(ItensCompra it: listaItensCompras) {//vamos percorer a lista
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
		
	}

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv= new ModelAndView("cliente/carrinho");
		calcularTotal();
		mv.addObject("listaItens",listaItensCompras);
		mv.addObject("compra",compra);
		return mv;


	}
	
	@PostMapping("/finaliazar/confirmar")
	public ModelAndView condirmarCompra(String formaPagamento, @AuthenticationPrincipal UserDetails userDetails) {
		User user = userRepository.findByEmail(userDetails.getUsername()); // Busca o usuário pelo email
		ModelAndView mv=new ModelAndView("cliente/mensagemFinalizou");
        compra.setUser(user); // Associa o usuário à compra
		compra.setFormaPagamento(formaPagamento);
		compraRepositorio.saveAndFlush(compra);
		for(ItensCompra it: listaItensCompras) {
			it.setCompra(compra);
			itensCompraRepositorio.saveAndFlush(it);
			
			//Para decrementar o estoque quando comprar os produtos
			Optional<Produto> produtoOptional=produtoRepositorio.findById(it.getProduto().getId());
			Produto produto=produtoOptional.get();
			if(produto.getQuantidadeEstoque() >= it.getQuantidade()) {
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - it.getQuantidade());
				produtoRepositorio.saveAndFlush(produto);
			}
			else {
				produto.setQuantidadeEstoque(0.0);				
				produtoRepositorio.saveAndFlush(produto);
			}

					
		}
		listaItensCompras= new ArrayList<>();
		compra = new Compra();
		return mv;
		
		
	}
//	versao gpt
	
	
//	// Método para confirmar a compra
//    @PostMapping("/finalizar/confirmar")
//    public ModelAndView confirmarCompra(String formaPagamento, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userRepository.findByEmail(userDetails.getUsername()); // Busca o usuário pelo email
//        ModelAndView mv = new ModelAndView("cliente/mensagemFinalizou");
//
//        compra.setUser(user); // Associa o usuário à compra
//        compra.setFormaPagamento(formaPagamento);
//
//        compraRepositorio.saveAndFlush(compra); // Salva a compra no banco de dados
//
//        for (ItensCompra it : listaItensCompras) {
//            it.setCompra(compra); // Associa a compra aos itens de compra
//            itensCompraRepositorio.saveAndFlush(it); // Salva os itens de compra no banco de dados
//
//            // Para decrementar o estoque
//            Optional<Produto> produtoOptional = produtoRepositorio.findById(it.getProduto().getId());
//            Produto produto = produtoOptional.orElseThrow(); // Lança exceção se não encontrar o produto
//            if (produto.getQuantidadeEstoque() >= it.getQuantidade()) {
//                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - it.getQuantidade());
//            } else {
//                produto.setQuantidadeEstoque(0.0);
//            }
//            produtoRepositorio.saveAndFlush(produto); // Salva o produto no banco de dados
//        }
//
//        listaItensCompras.clear(); // Limpa a lista de itens de compra
//        compra = new Compra(); // Cria uma nova instância de compra
//
//        return mv;
//    }
//


	
	@GetMapping("/finalizar")
	public ModelAndView finalizaCompra(@AuthenticationPrincipal UserDetails userDetails) {
		//buscarUsuarioLogado();
		User user = userRepository.findByEmail(userDetails.getUsername()); // Busca o usuário pelo email
		ModelAndView mv= new ModelAndView("cliente/finalizar");
		calcularTotal();
		mv.addObject("listaItens",listaItensCompras);
		mv.addObject("compra",compra);
		mv.addObject("user",user);
		return mv;


	}
//	
//	//versao chatgpt
//    @GetMapping("/finalizar")
//    public ModelAndView finalizaCompra(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userRepository.findByEmail(userDetails.getUsername()); // Busca o usuário pelo email
//        ModelAndView mv = new ModelAndView("cliente/finalizar");
//        mv.addObject("listaItens", listaItensCompras); // Adiciona a lista de itens de compra
//        mv.addObject("compra", compra); // Adiciona o objeto de compra
//        mv.addObject("user", user); // Adiciona o usuário autenticado
//        return mv;	
//    }
    
	@GetMapping("/alterarQuantidade/{id}/{acao}")//Uma vez que nós temos 2 botoes sendo um apra adicionar e outro para remover
	public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
		//ModelAndView mv= new ModelAndView("cliente/carrinho");
		
		for(ItensCompra it: listaItensCompras) {//vamos percorer a lista
			if(it.getProduto().getId().equals(id)) {//Se o item que estivar no carinho for igual ao item que eu procurei no banco de dados nao vai adiconar ou diminuir a quantidade dependendo da acao do botao que for clicado, eu vou definir como 1 para adicionar e 0 para remover(acao dos bostes adicionar e remover respectivamente)
				if(acao==1) {
					it.setQuantidade(it.getQuantidade()+1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				}
				if(acao==0) {
					final int num=1;
					if(it.getQuantidade()==1) {//Isso para evitar quantidades negativas
						it.setQuantidade(num);
					}
					if(it.getQuantidade() > 1) {
						it.setQuantidade(it.getQuantidade()-1);
						it.setValorTotal(0.);
						it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
						
					}
					
				}

				break;
			}
			
		}
		//mv.addObject("listaItens",listaItensCompras);
		return "redirect:/carrinho"; 


	}
	
	@GetMapping("/removerProdutoCarrinho/{id}")//Uma vez que nós temos 2 botoes sendo um apra adicionar e outro para remover
	public String removerProdutoCarrinho(@PathVariable Long id) {
		//ModelAndView mv= new ModelAndView("cliente/carrinho");
		
		for(ItensCompra it: listaItensCompras) {//vamos percorer a lista
			if(it.getProduto().getId().equals(id)) {//Se o item que estivar no carinho for igual ao item que eu procurei no banco de dados nao vai adiconar ou diminuir a quantidade dependendo da acao do botao que for clicado, eu vou definir como 1 para adicionar e 0 para remover(acao dos bostes adicionar e remover respectivamente)
				listaItensCompras.remove(it);
				break;
			}
			
		}
		//mv.addObject("listaItens",listaItensCompras);
		return "redirect:/carrinho";


	}
	
	
	@GetMapping("/adicionarCarrinho/{id}")
	public String chamarCarrinho(@PathVariable Long id) {//Antigamente retornava ModelAndView para evitar redundância de codigo
		//ModelAndView mv= new ModelAndView("cliente/carrinho");
		Optional<Produto> produtoOptional=produtoRepositorio.findById(id);
		Produto produto=produtoOptional.get();
		
		int controle=0;
		for(ItensCompra it: listaItensCompras) {
			if(it.getProduto().getId().equals(produto.getId())) {//Se o item que estivar no carinho for igual ao item que eu procurei no banco de dados nao vai adiconar novo item apenas vai increventar a quantidade no carrinho
				it.setQuantidade(it.getQuantidade()+1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				controle=1;
				break;
			}
			
		}
		if(controle==0) {//so vamos adicionar novo item caso nao tenha no carrinho
		
			ItensCompra item=new ItensCompra();
			item.setProduto(produto);
			
			item.setValorUnitario(produto.getValorVenda());
			
			item.setQuantidade(item.getQuantidade()+1);
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario()));
			listaItensCompras.add(item);
			//é possivel que já tenha esse produto esse produto ao invez de adicionar só vamos incrementar uma unidade desse produto na lista de item de compra
			
		}
		//mv.addObject("listaItens",listaItensCompras);
		return "redirect:/carrinho";



	}
	

}
