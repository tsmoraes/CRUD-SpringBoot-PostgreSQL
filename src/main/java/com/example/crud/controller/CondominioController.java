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

import com.example.crud.model.Condominio;
import com.example.crud.repository.CondominioRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CondominioController {

	@Autowired
	CondominioRepository tutorialRepository;

	@GetMapping("/condominio")
	public ResponseEntity<List<Condominio>> getAllTutorials(@RequestParam(required = false) String nome) {
		try {
			List<Condominio> tutorials = new ArrayList<Condominio>();

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

	@GetMapping("/condominio/{id}")
	public ResponseEntity<Condominio> getTutorialById(@PathVariable("id") long id) {
		Optional<Condominio> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/condominio")
	public ResponseEntity<Condominio> createTutorial(@RequestBody Condominio tutorial) {
		try {
			Condominio _tutorial = tutorialRepository
					.save(new Condominio(tutorial.getNome(), tutorial.getCnpj(), tutorial.getEndereco(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/condominio/{id}")
	public ResponseEntity<Condominio> updateTutorial(@PathVariable("id") long id, @RequestBody Condominio tutorial) {
		Optional<Condominio> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Condominio _tutorial = tutorialData.get();
			_tutorial.setNome(tutorial.getNome());
			_tutorial.setCnpj(tutorial.getCnpj());
			_tutorial.setEndereco(tutorial.getEndereco());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/condominio/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/condominio")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/condominio/published")
	public ResponseEntity<List<Condominio>> findByPublished() {
		try {
			List<Condominio> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
