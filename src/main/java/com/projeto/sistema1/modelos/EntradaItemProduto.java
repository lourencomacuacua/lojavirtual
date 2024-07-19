package com.projeto.sistema1.modelos;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="entrada_item_produto")//cara caso mudarmos do servidor que ospeda o nosso site para linux nao termos problemas
public class EntradaItemProduto implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne//Muitos funcionarios para uma entrada de produtos
	private EntradaProduto entradaProduto;
	@ManyToOne
	private Produto produto;
	private Double quantidade=0.;
	private Double valorProduto=0.;
	private Double valorVenda=0.;
	
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public EntradaProduto getEntradaProduto() {
		return entradaProduto;
	}
	public void setEntradaProduto(EntradaProduto entradaProduto) {
		this.entradaProduto = entradaProduto;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	
	
}
