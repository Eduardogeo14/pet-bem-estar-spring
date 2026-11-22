package br.edu.ifrs.petbemestar.dao;

import br.edu.ifrs.petbemestar.dominio.Usuario;

public interface UsuarioDAO {
    Usuario buscarPorEmail(String email);
    void salvar(Usuario usuario);
}