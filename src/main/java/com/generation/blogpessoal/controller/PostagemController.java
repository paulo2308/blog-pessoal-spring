package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	
	@Autowired
	PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		Optional<Postagem> op = postagemRepository.findById(id);
		return op.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletePost(@PathVariable Long id){
		
		return postagemRepository.findById(id)
				.map(resp -> { postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
				.orElse(ResponseEntity.notFound().build());
	}
}
