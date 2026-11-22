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


import br.edu.ifrs.petbemestar.dominio.Tutor;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;
import br.edu.ifrs.petbemestar.servico.TutorService;


@RestController
public class TutorController {
	
	private final TutorService tutorService;
	
	
	public TutorController(TutorService tutorService) {
		this.tutorService = tutorService;
		
	}
	
	@PostMapping("/tutores")
	@ResponseStatus(HttpStatus.CREATED)
	public Tutor salvar(@RequestBody Tutor tutor) {
		tutorService.salvar(tutor);
		return tutor;
	}
	
	@GetMapping("/tutores/{id}")
	public Tutor buscarPorId(@PathVariable Long id) {
		Tutor tutor =  tutorService.buscarPorId(id);
		
		if(tutor == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor não encontrado.");
        }
        return tutor;
    }
	
	@GetMapping("/tutores")
	public List<Tutor> listarTodos(){
		return tutorService.listarTodos();
	}
	
	@PutMapping("/tutores/{id}")
	public Tutor atualizar(@PathVariable Long id, @RequestBody Tutor dados) {
		Tutor tutor = tutorService.buscarPorId(id);
        if (tutor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor não encontrado.");
        }
		
		tutor.setNome(dados.getNome());
		tutor.setEmail(dados.getEmail());
		tutor.setTelefone(dados.getTelefone());
		
		tutorService.salvar(tutor);
		
		return tutor;
	}
	
	@DeleteMapping("/tutores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        tutorService.remover(id);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tratarRegraDeNegocio(RegraDeNegocioException e) {
        return e.getMessage();
    }
}
	

