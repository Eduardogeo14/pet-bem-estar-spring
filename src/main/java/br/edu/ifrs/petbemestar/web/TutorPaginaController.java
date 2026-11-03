package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;
import jakarta.validation.Valid;

@Controller
public class TutorPaginaController {

	private final TutorDAO tutorDao;
	
	
	public TutorPaginaController(TutorDAO tutorDao) {
		this.tutorDao = tutorDao;
		
	}
	
	@GetMapping("/paginas/tutores")
	public String listar(Model model) {
		model.addAttribute("tutores", tutorDao.listarTodos());
		return "tutores/lista";
	}
	
	@GetMapping("/paginas/tutores/novo")
	public String formulario(Model model) {
		model.addAttribute("tutor", new Tutor());
		return "tutores/formulario";
	}
	
	@PostMapping("/paginas/tutores/novo")
	public String cadastrar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado) {
		if(resultado.hasErrors()) {
			return "tutores/formulario";
		}
		tutorDao.salvar(tutor);
		return "redirect:/paginas/tutores";
	}
	
	
	@GetMapping("/paginas/tutores/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("tutor", tutorDao.buscarPorId(id));
		return "tutores/edicao";
	}
	
	@PostMapping("/paginas/tutores/editar")
	public String atualizar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado) {
		if(resultado.hasErrors()) {
			return "tutores/edicao";
		}
		tutorDao.atualizar(tutor);
		return "redirect:/paginas/tutores";
	}
	
	@PostMapping("/paginas/tutores/{id}/remover")
	public String remover(@PathVariable Long id) {
		tutorDao.remover(id);
		return "redirect:/paginas/tutores";
	}
}
