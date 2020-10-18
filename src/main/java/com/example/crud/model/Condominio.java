package com.example.crud.model;

import java.util.List;

/*
 * Desenvolvido por Thiago da Silva Moraes
 * Referência: https://github.com/bezkoder/spring-boot-data-jpa-mysql
 */

import javax.persistence.*;

@Entity
@Table(name = "condominio")
public class Condominio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "endereco")
	private String endereco;

	@Column(name = "published")
	private boolean published;
	
	@OneToMany
	@JoinColumn(name = "id_apartamento")
	private List<Apartamento> apartamentos;

	public Condominio() {

	}

	public Condominio(String nome, String cnpj, String endereco, boolean published) {
		this.title = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return title;
	}

	public void setNome(String nome) {
		this.title = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}
	
	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", nome=" + title + ", cnpj=" + cnpj + ", endereco=" + endereco + ", published=" + published + "]";
	}

}
