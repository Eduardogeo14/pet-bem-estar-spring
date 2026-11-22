package br.edu.ifrs.petbemestar.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.BichoDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Bicho;
import br.edu.ifrs.petbemestar.dominio.Tutor;

@Service
public class BichoService {

	private final TutorDAO tutorDAO;
	private final BichoDAO bichoDAO;
	
	public BichoService(TutorDAO tutorDAO, BichoDAO bichoDAO) {
		this.bichoDAO = bichoDAO;
		this.tutorDAO = tutorDAO;
	}
	
	
	public List<Bicho> listarTodos(){
		return bichoDAO.listarTodos();
	}
	
	public List<Bicho> buscarPorTutor(Long idTutor){
		return bichoDAO.buscarPorTutor(idTutor);
	}
	
	public Bicho buscarPorId(Long id) {
		return bichoDAO.buscarPorId(id);
	}
	
	public void salvar(Bicho bicho) {
		exigirTutorValido(bicho);
		bichoDAO.salvar(bicho);
	}
	
	public void atualizar(Bicho bicho) {
		exigirTutorValido(bicho);
		bichoDAO.atualizar(bicho);
	}
	
	public void remover(Long id) {
		bichoDAO.remover(id);
	}
	
	private void exigirTutorValido(Bicho bicho) {
		if(bicho.getTutor() == null || bicho.getTutor().getId() == null) {
			throw new RegraDeNegocioException("É necessário informar o tutor do bicho(animal)");
		}
		
		Tutor tutorEncontrado = tutorDAO.buscarPorId(bicho.getTutor().getId());
		
		if(tutorEncontrado == null) {
			throw new RegraDeNegocioException("Tutor não encontrado no sistema");

		}
		bicho.setTutor(tutorEncontrado);
	}
}
