package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.service.DependienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dependientes")
public class DependienteController {
	
	private static final String VIEWS_DEPENDIENTE_CREATE_OR_UPDATE_FORM = "empleados/createOrUpdateDependienteForm";
	
	private DependienteService depenService;
	
	@Autowired
	public DependienteController(DependienteService ds) {
		this.depenService = ds;
	}
	
	@GetMapping(value="/delete/{dependienteID}")
	public String borrarDependiente(@PathVariable("dependienteID") int dependienteID, ModelMap modelMap) {
		Optional<Dependiente> d = depenService.findDependienteById(dependienteID);
		if(d.isPresent()) {
			depenService.deleteDependiente(d.get());
			modelMap.addAttribute("message", "Dependiente borrado correctamente");
		} else {
			modelMap.addAttribute("message", "Dependiente no encontrado");
		}
		return "redirect:/empleados";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Dependiente dep = new Dependiente();
		model.put("dependiente", dep);
		return VIEWS_DEPENDIENTE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Dependiente dep, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DEPENDIENTE_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.depenService.saveDependiente(dep);
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/save/{dependienteId}")
	public String initUpdateForm(@PathVariable("dependienteId") int dependienteId, Model model) {
		Optional<Dependiente> d = this.depenService.findDependienteById(dependienteId);
		if(d.isPresent()) {
			model.addAttribute("dependiente", d.get());
			return VIEWS_DEPENDIENTE_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Dependiente no encontrado");
			return "redirect:/empleados";
		}
	}

	@PostMapping(value = "/save/{dependienteId}")
	public String processUpdateForm(@Valid Dependiente d, BindingResult result,
			@PathVariable("dependienteId") int dependienteId) {
		if (result.hasErrors()) {
			return VIEWS_DEPENDIENTE_CREATE_OR_UPDATE_FORM;
		}
		else {
			d.setId(dependienteId);
			this.depenService.saveDependiente(d);
			return "redirect:/empleados";
		}
	}
	
}
