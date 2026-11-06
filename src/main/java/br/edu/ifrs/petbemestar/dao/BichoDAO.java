package br.edu.ifrs.petbemestar.dao;

import java.util.List;

import br.edu.ifrs.petbemestar.dominio.Bicho;

public interface BichoDAO {

	void salvar(Bicho bicho);
	Bicho buscarPorId(Long id);
	List<Bicho> listarTodos();
	void atualizar(Bicho bicho);
	void remover(Long id);
}
