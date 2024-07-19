package com.projeto.sistema1.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //para identificar que é um controler da arquitetura mvc
public class PrincipalControle {
	@GetMapping("/administrativo")//vamos usar para redirecionar para algum lugar, e o nosso sistem vai executar a função a baixo
	public String acessarPrincipal() {	
		return "administrativo/home";//ele vai procuar esse caminho de pastas
		
	}

}