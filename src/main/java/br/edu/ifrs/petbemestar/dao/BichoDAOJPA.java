package br.edu.ifrs.petbemestar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.petbemestar.dominio.Bicho;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class BichoDAOJPA implements BichoDAO{

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void salvar(Bicho bicho) {
		em.persist(bicho);
	}
	
	@Override
	public Bicho buscarPorId(Long id) {
		return em.find(Bicho.class, id);
	}
	
	@Override
	public List<Bicho> listarTodos(){
		return em.createQuery("Select b from Bicho b JOIN FETCH b.dono ORDER BY b.nome", Bicho.class).getResultList();
	}
	
	@Override
	public void atualizar(Bicho bicho) {
		em.merge(bicho);
	}
	
	@Override
	public void remover(Long id) {
		
		Bicho bicho = em.find(Bicho.class, id);
		
		if(bicho != null) {
			em.remove(bicho);
		}
	}
}
