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
//public class BasicConfiguration {
//	@Autowired
//	private DataSource dataSource;
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{//a partir do datasorce eu consigo fazer uma consulta no banco de dados para verificar se o usu'ario est'a no nosso banco de dados
//	//	auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").and().withUser("admin").password(new BCryptPasswordEncoder().encode("USER"),"ADMIN");
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//				"select email as username, senha as password, 1 as enable form funcionario where email=?")
//		.authoritiesByUsernameQuery(
//				"select funcionario.email as username, funcao.nome as authority from permissoes  inner join funcionario on funcionario.id=permissoes.funcionario_id inner join funcao on permissoes.funcao_id where funcionario.email=?")
//		.passwordEncoder(new BCryptPasswordEncoder());//Essa consulta vai retornar todas as permissões de um funcinário
//			
//	}
//@Override
//	protected void configure(HttpSecurity http)throws Exception {
//		http.authorizeRequests().antMatchers("/login").permitAll()
//		.antMatchers("/administrativo/cadastrar/**").hasAnyAuthority("gerente")//Quais sao as paginas que nos vamos bloquear e qual 'e a funcao necessaria para acessar essa p'aginas
//		.antMatchers("/administrativo/**").authenticated()//Para acessar ao adminstrativo nao vou especificar as fun'c~oes so vou informar que o usu'ario deve estar autenticado, mas temos que ter um pouco de cuidado pois s'o do cliente estar autenticad ele pode acessar a p'agina de login
//		.and().formLogin().loginPage("/login").failuredUrl("/login")
//		.loginProcessingUrl("/admin").dafaultSuccessUrl("/adminstrativo").usernameParameter("username")
//		.passwordParameter("password")
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/adminstrativo/logout"))
//		.logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
//		.and().exceptionHandling().accessDeniedPage("/negado")
//		.and().csrf().disable();
//	}
//	
//}
//


