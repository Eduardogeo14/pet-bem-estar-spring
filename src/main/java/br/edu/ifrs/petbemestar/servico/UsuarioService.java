package br.edu.ifrs.petbemestar.servico;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.UsuarioDAO;
import br.edu.ifrs.petbemestar.dominio.Usuario;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void cadastrar(Usuario usuario) {
        if (usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
            throw new RegraDeNegocioException("Este e-mail já está em uso.");
        }
        
        if (usuario.getSenha() == null || usuario.getSenha().length() < 4) {
            throw new RegraDeNegocioException("A senha deve ter no mínimo 4 caracteres.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioDAO.salvar(usuario);
    }

    public Usuario autenticar(String email, String senhaDigitada) {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        
        if (usuario != null && passwordEncoder.matches(senhaDigitada, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }
}