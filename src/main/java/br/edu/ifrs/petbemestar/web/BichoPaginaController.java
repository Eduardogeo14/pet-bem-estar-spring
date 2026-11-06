package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.petbemestar.dao.BichoDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Bicho;
import br.edu.ifrs.petbemestar.dominio.TamanhoEnum;
import br.edu.ifrs.petbemestar.dominio.TipoAnimalEnum;
import jakarta.validation.Valid;

@Controller
public class BichoPaginaController {

	private BichoDAO bichoDAO;
	private TutorDAO tutorDAO;
	
	
	public BichoPaginaController(BichoDAO bichoDAO, TutorDAO tutorDAO) {
		this.bichoDAO = bichoDAO;
		this.tutorDAO = tutorDAO;
	}
	
	
	private void adicionarListasNoModel(Model model) {
		model.addAttribute("tutores", tutorDAO.listarTodos());
		model.addAttribute("tipos", TipoAnimalEnum.values());
		model.addAttribute("tamanhos", TamanhoEnum.values());
	}
	
	@GetMapping("/paginas/bichos")
	public String listar(Model model) {
		model.addAttribute("bichos", bichoDAO.listarTodos());
		return "bichos/lista";
	}
	
	@GetMapping("/paginas/bichos/novo")
	public String formulario(Model model) {
		model.addAttribute("bicho", new Bicho());
		adicionarListasNoModel(model);
		return "bichos/formulario";
		
	}
	
	@PostMapping("/paginas/bichos/novo")
	public String cadastrar(@Valid @ModelAttribute Bicho bicho, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			adicionarListasNoModel(model);
			return "bichos/formulario";
		}
		bichoDAO.salvar(bicho);
		return "redirect:/paginas/bichos";
	}
	
	@GetMapping("/paginas/bichos/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("bicho", bichoDAO.buscarPorId(id));
		adicionarListasNoModel(model);
		return "bichos/edicao";
	}
	
	@PostMapping("/paginas/bichos/editar")
	public String atualizar(@Valid @ModelAttribute Bicho bicho, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			adicionarListasNoModel(model);
			return "bichos/edicao";
		}
		bichoDAO.atualizar(bicho);
		return "redirect:/paginas/bichos";
	}
	
	@PostMapping("/paginas/bichos/{id}/remover")
	public String remover(@PathVariable Long id) {
		bichoDAO.remover(id);
		return "redirect:/paginas/bichos";
	}
	
	
	
}
