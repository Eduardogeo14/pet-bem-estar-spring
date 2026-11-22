package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.petbemestar.dominio.Tutor;
import br.edu.ifrs.petbemestar.servico.BichoService;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;
import br.edu.ifrs.petbemestar.servico.TutorService;
import jakarta.validation.Valid;

@Controller
public class TutorPaginaController {

    private final TutorService tutorService;
    private final BichoService bichoService;

    public TutorPaginaController(TutorService tutorService, BichoService bichoService) {
        this.tutorService = tutorService;
        this.bichoService = bichoService;
    }

    @GetMapping("/paginas/tutores")
    public String listar(Model model) {
        model.addAttribute("tutores", tutorService.listarTodos());
        return "tutores/lista";
    }

    @GetMapping("/paginas/tutores/novo")
    public String formulario(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutores/formulario";
    }

    @PostMapping("/paginas/tutores/novo")
    public String cadastrar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            return "tutores/formulario";
        }
        try {
            tutorService.salvar(tutor);
            return "redirect:/paginas/tutores";
        } catch (RegraDeNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return "tutores/formulario";
        }
    }

    @GetMapping("/paginas/tutores/{id}/editar")
    public String edicao(@PathVariable Long id, Model model) {
        model.addAttribute("tutor", tutorService.buscarPorId(id));
        return "tutores/edicao";
    }

    @PostMapping("/paginas/tutores/editar")
    public String atualizar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            return "tutores/edicao";
        }
        try {
            tutorService.salvar(tutor);
            return "redirect:/paginas/tutores";
        } catch (RegraDeNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return "tutores/edicao";
        }
    }

    @PostMapping("/paginas/tutores/{id}/remover")
    public String remover(@PathVariable Long id, RedirectAttributes atributos) {
        try {
            tutorService.remover(id);
        } catch (RegraDeNegocioException e) {
            atributos.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/paginas/tutores";
    }
    
    @GetMapping("/paginas/tutores/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Tutor tutor = tutorService.buscarPorId(id);
        model.addAttribute("tutor", tutor);
        model.addAttribute("bichos", bichoService.buscarPorTutor(id));
        return "tutores/detalhes";
    }
}