package com.projeto.sistema1.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.sistema1.dto.UserDto;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.UserRepository;
import com.projeto.sistema1.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
    @Qualifier("customUserDetailsService") // Especifique o nome do bean desejado aqui
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, RedirectAttributes attributes) {
	    userService.save(userDto);
	    attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso.");

	    return "redirect:/registration"; // Redireciona para a página de registro novamente
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("user-page")
	public String userPage( Principal principal,Model model) {
		UserDetails userDetails= userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "index";
	}
	
	
	@GetMapping("admin-page")
	public String adminPage(Model model, Principal principal) {
		UserDetails userDetails= userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "administrativo/home";
	}
	
	
//////////////////
	@GetMapping("/cadastroUsuario") 
	public ModelAndView cadastrar(User user) {
		

		ModelAndView mv= new ModelAndView("administrativo/usuarios/cadastro");
		mv.addObject("user",user);
		return  mv;
		
	}
	

	
	@PostMapping("/salvarUsuario")
	public ModelAndView salvarUser(@ModelAttribute("user") UserDto userDto,Model model) {
		
		userService.save(userDto);
	    // Criando uma mensagem de sucesso
	    String mensagem = "Usuário " + userDto.getFullname() + " salvo com sucesso!";
		model.addAttribute("message", mensagem);
		return cadastrar(new User());
	}
	

	

	
	@GetMapping("/listarUsuarios")
	public ModelAndView listar() {
		ModelAndView mv= new ModelAndView("administrativo/usuarios/lista");
		mv.addObject("listaUsuarios",userRepository.findAll());
		return mv;
		
	}
	
	

	@GetMapping("/editarUsuario/{id}")//para chamar a função editar funcionario no front-end vamos isar o thymeleaf i vamos pasar o nome \editarFuncionario\{id} responsavel pelo método de editar funcionario com o parametro id
	public ModelAndView editar(@PathVariable("id") Long id) {//(@PathVariable é para receber o nosso parámetro
		Optional<User> user =userRepository.findById(id);//Option é para evitar questão de null quando nós caregamos as informações de funcionario para edição
		return cadastrar(user.get());
	}
	
	@GetMapping("/removerUsuario/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<User> user =userRepository.findById(id);
		userRepository.delete(user.get());
		return listar();
	}
	
}
