package br.edu.ifrs.petbemestar.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;

@Service
public class TutorService {
	
	private final TutorDAO tutorDAO;
	
	public TutorService(TutorDAO tutorDAO) {
		this.tutorDAO = tutorDAO;
	}
	
	public List<Tutor> listarTodos(){
		return tutorDAO.listarTodos();
	}
	
	public Tutor buscarPorId(Long id) {
		return tutorDAO.buscarPorId(id);
	}
	
	public void salvar(Tutor tutor) {
		if(tutor.getId() == null) {
			if(tutorDAO.telefoneJaCadastrado(tutor.getTelefone())) {
				throw new RegraDeNegocioException("Este telefone já pertence a outro cliente");
			}
			tutorDAO.salvar(tutor);
		} else {
			 if(tutorDAO.telefonePertenceAOutro(tutor.getId(), tutor.getTelefone())){
				throw new RegraDeNegocioException("Este telefone já pertence a outro cliente");

			}
			tutorDAO.atualizar(tutor);
		}
	}
	
	public void remover(Long id) {
	    if (tutorDAO.buscarPorId(id) == null) {
	        throw new RegraDeNegocioException("Tutor não encontrado.");
	    }
	    if (tutorDAO.verificarSeTemBichos(id)) {
	        throw new RegraDeNegocioException(
	            "Não é possível remover este tutor, pois ele tem bichos cadastrados.");
	    }
	    tutorDAO.remover(id);
	}
}
