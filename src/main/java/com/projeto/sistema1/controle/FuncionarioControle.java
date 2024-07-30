
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
