package com.projeto.sistema1;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class configuracaoBancoDadoDeDados {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.postgresql.Driver");//declara as configurações de acesso
		dataSource.setUrl("jdbc:postgresql://localhost:5432/loja8");
		dataSource.setUsername("postgres");//quanto configuramos o postgress
		dataSource.setPassword("postgres");
		
		return dataSource;	
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {//configuração do jpa do hibernate para podeder funcionar com o nosso banco de dados postegres(existem 2 conceitos o jpa e o hibernate sendo que o jpa é como se fosse um conjunto de regras para abstrair e faciliatar a iterção com o banco de dados(nao implementa em si apenas é uma facilitação), enquanto, hibernate vai emplementar essa regras em acção
		HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);//driver do banco
		adapter.setShowSql(true);//mostra no console o sql
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}
	

	



}
