package br.edu.ifrs.petbemestar.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.petbemestar.dao.*;
import br.edu.ifrs.petbemestar.dominio.Tutor;


@RestController
public class TutorController {
	
	private final TutorDAO dao;
	
	public TutorController(TutorDAO dao) {
		this.dao = dao;
	}
	
	@PostMapping("/tutores")
	@ResponseStatus(HttpStatus.CREATED)
	public Tutor salvar(@RequestBody Tutor tutor) {
		dao.salvar(tutor);
		return tutor;
	}
	
	@GetMapping("/tutores/{id}")
	public Tutor buscarPorId(@PathVariable Long id) {
		return dao.buscarPorId(id);
	}
	
	@GetMapping("/tutores")
	public List<Tutor> listarTodos(){
		return dao.listarTodos();
	}
	
	@PutMapping("/tutores/{id}")
	public Tutor atualizar(@PathVariable Long id, @RequestBody Tutor dados) {
		Tutor tutor = dao.buscarPorId(id);
		
		tutor.setNome(dados.getNome());
		tutor.setEmail(dados.getEmail());
		tutor.setTelefone(dados.getTelefone());
		
		dao.atualizar(tutor);
		
		return tutor;
	}
	
	@DeleteMapping("/tutores/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		dao.remover(id);
	}
	
	
}
