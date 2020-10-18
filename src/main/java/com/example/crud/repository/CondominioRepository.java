package com.example.crud.repository;

/*
 * Desenvolvido por Thiago da Silva Moraes
 * Referência: https://github.com/bezkoder/spring-boot-data-jpa-mysql
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Condominio;

public interface CondominioRepository extends JpaRepository<Condominio, Long> {
	List<Condominio> findByPublished(boolean published);
	List<Condominio> findByTitleContaining(String nome);
}
