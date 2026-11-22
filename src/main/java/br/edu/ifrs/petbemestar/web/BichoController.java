package br.edu.ifrs.petbemestar.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifrs.petbemestar.dominio.Bicho;
import br.edu.ifrs.petbemestar.servico.BichoService;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;

@RestController
public class BichoController {

	private BichoService bichoService;
	
	
	public BichoController(BichoService bichoService) {
		this.bichoService = bichoService;
		
	}
	
	@PostMapping("/bichos")
	@ResponseStatus(HttpStatus.CREATED)
	public Bicho salvar(@RequestBody Bicho bicho) {
		bichoService.salvar(bicho);
		return bicho;
		
	}
	
	@GetMapping("/bichos/{id}")
	public Bicho buscarPorId(@PathVariable Long id) {
		return obterOuFalhar(id);
	}
	
	@GetMapping("/bichos")
	public List<Bicho> listarTodos(){
		return bichoService.listarTodos();
	}
	
	@PutMapping("/bichos/{id}")
	public Bicho atualizar(@PathVariable Long id, @RequestBody Bicho dados) {
		Bicho bicho = obterOuFalhar(id);
		
		bicho.setNome(dados.getNome());
		bicho.setTipo(dados.getTipo());
		bicho.setTamanho(dados.getTamanho());
		bicho.setTutor(dados.getTutor());
		
		bichoService.atualizar(bicho);
		return bicho;
	}
	
	@DeleteMapping("/bichos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Bicho bicho = obterOuFalhar(id);
		bichoService.remover(bicho.getId());
	}
	
	
	private Bicho obterOuFalhar(Long id) {
		Bicho bicho = bichoService.buscarPorId(id);
		
		if(bicho == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bicho não encontrado");
		}
		return bicho;
	}
	
	@ExceptionHandler(RegraDeNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String tratarRegraDeNegocio(RegraDeNegocioException e) {
		return e.getMessage();
	}
}
