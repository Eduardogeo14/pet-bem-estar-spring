package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import br.edu.ifrs.petbemestar.dominio.Bicho;
import br.edu.ifrs.petbemestar.dominio.TamanhoEnum;
import br.edu.ifrs.petbemestar.dominio.TipoAnimalEnum;
import br.edu.ifrs.petbemestar.servico.BichoService;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;
import br.edu.ifrs.petbemestar.servico.TutorService;
import jakarta.validation.Valid;

@Controller
public class BichoPaginaController {

	private final BichoService bichoService;
	private final TutorService tutorService;
	
	
	public BichoPaginaController(BichoService bichoService, TutorService tutorService) {
		this.bichoService = bichoService;
		this.tutorService = tutorService;
	}
	
	
	private void adicionarListasNoModel(Model model) {
		model.addAttribute("tutores", tutorService.listarTodos());
		model.addAttribute("tipos", TipoAnimalEnum.values());
		model.addAttribute("tamanhos", TamanhoEnum.values());
	}
	
	@GetMapping("/paginas/bichos")
	public String listar(Model model) {
		model.addAttribute("bichos", bichoService.listarTodos());
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
		
		try {
			bichoService.salvar(bicho);
			return "redirect:/paginas/bichos";
		} catch (RegraDeNegocioException e) {
			model.addAttribute("erro", e.getMessage());
			adicionarListasNoModel(model);
			return "bichos/formulario";
			
		} 
		
	}
	
	@GetMapping("/paginas/bichos/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("bicho", bichoService.buscarPorId(id));
		adicionarListasNoModel(model);
		return "bichos/edicao";
	}
	
	@PostMapping("/paginas/bichos/editar")
	public String atualizar(@Valid @ModelAttribute Bicho bicho, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			adicionarListasNoModel(model);
			return "bichos/edicao";
		}
		
		try {
			bichoService.atualizar(bicho);
			return "redirect:/paginas/bichos";
		} catch (RegraDeNegocioException e) {
			model.addAttribute("erro", e.getMessage());
			adicionarListasNoModel(model);
			return "bichos/edicao";
			
		} 
	}
	
	@PostMapping("/paginas/bichos/{id}/remover")
	public String remover(@PathVariable Long id) {
		bichoService.remover(id);
		return "redirect:/paginas/bichos";
	}
	
	
	
}
