package com.example.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unidade")
public class Apartamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "tamanho")
	private String tamanho;
	
	@Column(name = "vagas")
	private int vagas;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private Condominio condominio;
	
	public Apartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apartamento(String bloco, int numero, String tamanho, int vagas, Condominio condominio) {
		super();
		this.title = bloco;
		this.numero = numero;
		this.tamanho = tamanho;
		this.vagas = vagas;
		this.condominio = condominio;
	}

	public long getId() {
		return id;
	}

	public String getBloco() {
		return title;
	}

	public void setBloco(String bloco) {
		this.title = bloco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	@Override
	public String toString() {
		return "Apartamento [id=" + id + ", bloco=" + title + ", numero=" + numero + ", tamanho=" + tamanho + ", vagas="
				+ vagas + ", condominio=" + condominio.getNome() + "]";
	}
		

}
