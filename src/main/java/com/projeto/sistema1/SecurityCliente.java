//package com.projeto.sistema1;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.AntPathRequestMatcherProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//@Order(1) Esse arquivo vai ser o primeiro a ser considerado pelo spring no processamento da nossa url
//public class BasicConfiguration {
//	@Autowired
//	private DataSource dataSource;
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{//a partir do datasorce eu consigo fazer uma consulta no banco de dados para verificar se o usu'ario est'a no nosso banco de dados
//	//	auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").and().withUser("admin").password(new BCryptPasswordEncoder().encode("USER"),"ADMIN");
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//				"select email as username, senha as password, 1 as enable form cliente where email=?")
//		.authoritiesByUsernameQuery(
//				"select email as username, 'cliente' as authority from cliente  where email=?")
//		.passwordEncoder(new BCryptPasswordEncoder());//Essa consulta vai retornar todas as permissões de um funcinário
//			
//	}
//	@Override
//	protected void configure(HttpSecurity http)throws Exception {
//		http.antMatcher("/finalizar/**").AuthorizeRequests().anyRequest().hasAnyAutority("cliente").and().csrf()//Temos uma regra que para acessar ao finalizar o usuario tem que ter autoridade de cliente e vamos desabiliatr csrf
//		.disable().formLogin().loginPage("/cliente/cadastrar").permitAll().failureUrl("/cliente/cadastrar")//a pagina de login que vamos usar vai ser a /cliente/cadastrar e vamos permitir acess a ela, e a p'agina de falha para caso o usua'ario tenha informacao incosistente vamos madar para p'agina de cadastrar
//		.loginProcessingUrl("/finalizar/login").defaultSuccessUrl("/finalizar").usernameParameter("username")//e qual vai ser a url que vai ser processada para a p'agina de login, ou seja alterar o action do nosso form mandodo para  finalizar/login(que vai processar) e dafaltSuccesUrs em caso de sucesso e usernamePaameter e password parametros servema para quando atribuirmos os nosso atributos na p'agina de login por padrao nos deixamos no nome padrao como username e password, mas nesse caso nao seria necess'ario adicionar essas 2 informa'coes
//		.passwordParameter("password").and().logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/finalizar/logout")).logoutSuccessUrl("/").permitAll())//para logout vai ser /finalinar/logout
//		.and().exceptionHandling().accessDeniedPage("/negadoCliente");//para negado ser'a /negado
//	}
//	
//}



