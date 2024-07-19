package com.projeto.sistema1.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.projeto.sistema1.service.CustomSuccessHandler;

import com.projeto.sistema1.service.CustomUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	 @Bean
	 public static PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
		 
	 }
	 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http.csrf(c -> c.disable())
	         .authorizeRequests(requests -> requests
	             // Acesso permitido a esses caminhos
        		 .requestMatchers("/", "/carrinho", "/mostarImagem/{imagem}","/alterarQuantidade/{id}/{acao}","/removerProdutoCarrinho/{id}","/adicionarCarrinho/{id}","/finalizar","/registration") .permitAll()
	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
        		// .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "GEREN"
        		 .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "GEREN")
	             // Acesso negado aos caminhos para usuários com a autoridade "GEREN"
        		 .requestMatchers("/cadastrarProdutoo",  "editarProduto/{id}", "/removerProduto/{id}","/cadastrarEntradaProdutoo", "/salvarEntradaProduto","/removerItensCompra/{id}").hasAuthority("ADMIN")
	             // Todas as outras requisições precisam estar autenticadas
	             .anyRequest().authenticated()
	         )
	         .formLogin(form -> form
	             .loginPage("/login")
	             .loginProcessingUrl("/login")
	             .successHandler(customSuccessHandler)
	             .permitAll()
	         )
	         .logout(logout -> logout
	             .invalidateHttpSession(true)
	             .clearAuthentication(true)
	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	             .logoutSuccessUrl("/login?logout")
	             .permitAll()
	         )
	         .exceptionHandling(exceptions -> exceptions
	             .accessDeniedHandler((request, response, accessDeniedException) -> {
	                 response.sendRedirect("/acessoNegado");
	             })
	         );

	     return http.build();
	 }
	 
	 
	 @Bean
	 public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
	     http.csrf(c -> c.disable())
	         .authorizeRequests(requests -> requests
	             // Acesso permitido a esses caminhos
        		 .requestMatchers("/", "/carrinho", "/mostarImagem/{imagem}","/alterarQuantidade/{id}/{acao}","/removerProdutoCarrinho/{id}","/adicionarCarrinho/{id}","/finalizar") .permitAll()
	             // Acesso à raiz "/" para qualquer usuário autenticado ou com a autoridade "ADMIN"
        		// .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
	             // Acesso "/administrativo" para usuários com a autoridade "ADMIN" ou "USER"
        		 .requestMatchers("/administrativo").hasAnyAuthority("ADMIN", "USER")
	             // Acesso negado aos caminhos para usuários com a autoridade "USER"
        		 .requestMatchers("/cadastrarProdutoo",  "editarProduto/{id}", "/removerProduto/{id}","/cadastrarEntradaProdutoo", "/salvarEntradaProduto","/removerItensCompra/{id}").hasAuthority("ADMIN")
	             // Todas as outras requisições precisam estar autenticadas
	             .anyRequest().authenticated()
	         )
	         .formLogin(form -> form
	             .loginPage("/login")
	             .loginProcessingUrl("/login")
	             .successHandler(customSuccessHandler)
	             .permitAll()
	         )
	         .logout(logout -> logout
	             .invalidateHttpSession(true)
	             .clearAuthentication(true)
	             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	             .logoutSuccessUrl("/login?logout")
	             .permitAll()
	         )
	         .exceptionHandling(exceptions -> exceptions
	             .accessDeniedHandler((request, response, accessDeniedException) -> {
	                 response.sendRedirect("/acessoNegado");
	             })
	         );

	     return http.build();
	 }
	 
	 
	 
	 @Autowired
	 public void configure(AuthenticationManagerBuilder auth)throws Exception{
		 auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		 
	 }



}
