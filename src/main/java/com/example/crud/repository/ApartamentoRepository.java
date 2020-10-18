package com.example.crud.repository;

/*
 * Desenvolvido por Thiago da Silva Moraes
 * Referência: https://github.com/bezkoder/spring-boot-data-jpa-mysql
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Apartamento;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
	List<Apartamento> findByTitleContaining(String bloco);
}
