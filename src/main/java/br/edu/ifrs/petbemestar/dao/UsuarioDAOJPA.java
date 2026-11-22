package br.edu.ifrs.petbemestar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.petbemestar.dominio.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;

@Repository
@Transactional
public class UsuarioDAOJPA implements UsuarioDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Usuario buscarPorEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }
}