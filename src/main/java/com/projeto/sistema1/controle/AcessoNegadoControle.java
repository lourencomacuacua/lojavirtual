package com.projeto.sistema1.controle;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.sistema1.dto.UserDto;
import com.projeto.sistema1.modelos.Funcionario;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.UserRepository;
import com.projeto.sistema1.service.UserService;


@Controller
public class AcessoNegadoControle {

	
	@GetMapping("/acessoNegado") //para nós obtemos o mateamento(Quando a pessoa executar isso nós vamos executar um método(chamada da função ou seja sempre que a pessoa chama cadastra estato ela quer cadastrar o  funcionario)
	public ModelAndView aceder() {//vai ser responsável por criar a nossa view ou seja a nossa visualização(uma vez que nós estamos utilizado o timelieaf
		ModelAndView mv= new ModelAndView("administrativo/acessoNegado");//esse é o caminho de pastas até chagar o nosso arquivo cadastro.html
		return  mv;
		
	}
	

}
