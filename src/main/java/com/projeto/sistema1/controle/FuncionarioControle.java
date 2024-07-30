//package com.projeto.sistema1.controle;
//
//import java.util.Optional;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.projeto.sistema1.modelos.Funcionario;
//import com.projeto.sistema1.repositorios.FuncionarioRepositorio;
//
//@Controller
//public class FuncionarioControle {
//	@Autowired
//	private FuncionarioRepositorio funcionarioRepositorio;
//	
//	@GetMapping("/cadastrarFuncionarioo")
//	public ModelAndView cadastrar(Funcionario funcionario){
//		ModelAndView mv= new ModelAndView("/administrativo/funcionarioss/cadastro");
//		mv.addObject("funcionario",funcionario);
//		
//		return mv;
//	
//	}
//	
//	@GetMapping("/listarFuncionarioo")
//	public ModelAndView listar() {
//		ModelAndView mv= new ModelAndView("/administrativo/funcionarioss/lista");
//		mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
//		return mv;
//
//	}
//	@PostMapping("/salvarFuncionario")
//	public ModelAndView salvar( Funcionario funcionario,BindingResult result,Model model) {
//		if(result.hasErrors()) {
//			return cadastrar(funcionario);
//		}
//		//Temos que emcriptar a senha original do funionario antes de salvar
//		//funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));//'e importante que no momento que estivarmos alterando um determinado funcionário, devo alterara sua senha se não avai encriptar uma senha encriptada e o funcinácrio nao vai conhecer mais a sua senha.Deficinencia do sistema mas vamos implementar uma funcionalidade de recuperar senha 
//		funcionarioRepositorio.saveAndFlush(funcionario);
//		String mensagem = "Funcionario '" + funcionario.getNome() + "' salvo com sucesso!";
//		model.addAttribute("message", mensagem);
//		return cadastrar(new Funcionario());
//		
//	}
//	
//	@GetMapping("/editarFuncionario/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<Funcionario> funcionario=funcionarioRepositorio.findById(id);
//		return cadastrar(funcionario.get());
//	}
//	
//	@GetMapping("/removerFuncionario/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Funcionario> funcionario=funcionarioRepositorio.findById(id);
//		funcionarioRepositorio.delete(funcionario.get());
//		return listar();
//	}
//	
//
//}
//package com.projeto.sistema1.controle;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.projeto.sistema1.modelos.Funcionario;
//import com.projeto.sistema1.repositorios.FuncionarioRepositorio;
//
//@Controller
//public class FuncionarioControle {
//
//    @Autowired
//    private FuncionarioRepositorio funcionarioRepositorio;
//
//    @GetMapping("/cadastrarFuncionarioo")
//    public String cadastrar(Model model) {
//        model.addAttribute("funcionario", new Funcionario());
//        return "administrativo/funcionarioss/cadastro";
//    }
//
//    @GetMapping("/listarFuncionarioo")
//    public String listar(Model model) {
//        model.addAttribute("listaFuncionarios", funcionarioRepositorio.findAll());
//        return "administrativo/funcionarioss/lista";
//    }
//
//    @PostMapping("/salvarFuncionario")
//    public String salvar(Funcionario funcionario, BindingResult result, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "administrativo/funcionarioss/cadastro";
//        }
//
//        // Encriptar senha (se necessário)
//        // funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
//
//        funcionarioRepositorio.save(funcionario);
//        redirectAttributes.addFlashAttribute("message", "Funcionário '" + funcionario.getNome() + "' salvo com sucesso!");
//        return "redirect:/cadastrarFuncionarioo";
//    }
//
//    @GetMapping("/editarFuncionario/{id}")
//    public String editar(@PathVariable("id") Long id, Model model) {
//        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
//        if (funcionario.isPresent()) {
//            model.addAttribute("funcionario", funcionario.get());
//            return "administrativo/funcionarioss/cadastro";
//        } else {
//            return "redirect:/listarFuncionarioo";
//        }
//    }
//
//    @GetMapping("/removerFuncionario/{id}")
//    public String remover(@PathVariable("id") Long id) {
//        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
//        if (funcionario.isPresent()) {
//            funcionarioRepositorio.delete(funcionario.get());
//        }
//        return "redirect:/listarFuncionarioo";
//    }
//}
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
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("administrativo/funcionarioss/cadastro");
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @GetMapping("/listarFuncionarioo")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/funcionarioss/lista");
        mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return cadastrar(funcionario);
        }

        // Encriptar senha (se necessário)
        // funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));

        funcionarioRepositorio.saveAndFlush(funcionario);
        String mensagem = "Funcionário '" + funcionario.getNome() + "' salvo com sucesso!";
        model.addAttribute("message", mensagem);
        return cadastrar(new Funcionario());
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            return cadastrar(funcionario.get());
        } else {
            // Redirecione para a lista se o funcionário não for encontrado
            return listar();
        }
    }

    @GetMapping("/removerFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            funcionarioRepositorio.delete(funcionario.get());
        }
        return listar();
    }
}
