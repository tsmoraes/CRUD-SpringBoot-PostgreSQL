package com.example.crud.controller;

/*
 * Desenvolvido por Thiago da Silva Moraes
 * Referência: https://github.com/bezkoder/spring-boot-data-jpa-mysql
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Apartamento;
import com.example.crud.repository.ApartamentoRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApartamentoController {

	@Autowired
	ApartamentoRepository tutorialRepository;

	@GetMapping("/unidade")
	public ResponseEntity<List<Apartamento>> getAllTutorials(@RequestParam(required = false) String nome) {
		try {
			List<Apartamento> tutorials = new ArrayList<Apartamento>();

			if (nome == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(nome).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/unidade/{id}")
	public ResponseEntity<Apartamento> getTutorialById(@PathVariable("id") long id) {
		Optional<Apartamento> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/unidade")
	public ResponseEntity<Apartamento> createTutorial(@RequestBody Apartamento tutorial) {
		try {
			Apartamento _tutorial = tutorialRepository
					.save(new Apartamento(tutorial.getBloco(), tutorial.getNumero(), tutorial.getTamanho(), tutorial.getVagas(), tutorial.getCondominio()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/unidade/{id}")
	public ResponseEntity<Apartamento> updateTutorial(@PathVariable("id") long id, @RequestBody Apartamento tutorial) {
		Optional<Apartamento> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Apartamento _tutorial = tutorialData.get();
			_tutorial.setBloco(tutorial.getBloco());
			_tutorial.setNumero(tutorial.getNumero());
			_tutorial.setTamanho(tutorial.getTamanho());
			_tutorial.setVagas(tutorial.getVagas());
			_tutorial.setCondominio(tutorial.getCondominio());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/unidade/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/unidade")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
