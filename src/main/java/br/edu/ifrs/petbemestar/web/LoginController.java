package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.petbemestar.dominio.Usuario;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;
import br.edu.ifrs.petbemestar.servico.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Usuario usuarioLogado = usuarioService.autenticar(email, senha);

        if (usuarioLogado != null) {
            session.setAttribute("usuarioLogado", usuarioLogado.getEmail());
            return "redirect:/paginas/tutores";
        } else {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            model.addAttribute("email", email);
            return "login";
        }
    }

    @GetMapping("/cadastro")
    public String telaCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String fazerCadastro(@RequestParam String email, @RequestParam String senha, RedirectAttributes atributos, Model model) {
        try {
            Usuario novoUsuario = new Usuario(email, senha);
            usuarioService.cadastrar(novoUsuario);
            atributos.addFlashAttribute("mensagem", "Conta criada com sucesso! Faça login.");
            return "redirect:/login";
        } catch (RegraDeNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("email", email);
            return "cadastro";
        }
    }

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}