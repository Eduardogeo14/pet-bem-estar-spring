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

import br.edu.ifrs.petbemestar.dao.BichoDAO;
import br.edu.ifrs.petbemestar.dominio.Bicho;

@RestController
public class BichoController {

	private BichoDAO bichoDAO;
	
	
	public BichoController(BichoDAO bichoDAO) {
		this.bichoDAO = bichoDAO;
		
	}
	
	@PostMapping("/bichos")
	@ResponseStatus(HttpStatus.CREATED)
	public Bicho salvar(@RequestBody Bicho bicho) {
		bichoDAO.salvar(bicho);
		return bicho;
	}
	
	@GetMapping("/bichos/{id}")
	public Bicho buscarPorId(@PathVariable Long id) {
		return bichoDAO.buscarPorId(id);
	}
	
	@GetMapping("/bichos")
	public List<Bicho> listarTodos(){
		return bichoDAO.listarTodos();
	}
	
	@PutMapping("/bichos/{id}")
	public Bicho atualizar(@PathVariable Long id, @RequestBody Bicho dados) {
		Bicho bicho = bichoDAO.buscarPorId(id);
		
		bicho.setNome(dados.getNome());
		bicho.setTipo(dados.getTipo());
		bicho.setTamanho(dados.getTamanho());
		bicho.setDono(dados.getDono());
		
		bichoDAO.atualizar(bicho);
		return bicho;
	}
	
	@DeleteMapping("/bichos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		bichoDAO.remover(id);
	}
	
}
