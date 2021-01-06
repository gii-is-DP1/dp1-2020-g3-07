package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cocineros")
public class CocineroController {

	private static final String VIEWS_COCINERO_CREATE_OR_UPDATE_FORM = "empleados/createOrUpdateCocineroForm";
	
	private CocineroService cociService;
	
	@Autowired
	public CocineroController(CocineroService rs) {
		this.cociService = rs;
	}
	
	@GetMapping(value="/delete/{cocineroID}")
	public String borrarCocinero(@PathVariable("cocineroID") int cocineroID, ModelMap modelMap) {
		Optional<Cocinero> d = cociService.findCocineroById(cocineroID);
		if(d.isPresent()) {
			cociService.deleteCocinero(d.get());
			modelMap.addAttribute("message", "Cocinero borrado correctamente");
		} else {
			modelMap.addAttribute("message", "Cocinero no encontrado");
		}
		return "redirect:/empleados";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Cocinero dep = new Cocinero();
		model.put("cocinero", dep);
		return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Cocinero dep, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.cociService.saveCocinero(dep);
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/edit/{cocineroID}")
	public String initUpdateForm(@PathVariable("cocineroID") int cocineroID, Model model) {
		Optional<Cocinero> d = this.cociService.findCocineroById(cocineroID);
		if(d.isPresent()) {
			model.addAttribute("cocinero", d.get());
			return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Cocinero no encontrado");
			return "redirect:/empleados";
		}
	}

	@PostMapping(value = "/edit/{cocineroID}")
	public String processUpdateForm(@Valid Cocinero d, BindingResult result,
			@PathVariable("cocineroID") int cocineroID) {
		if (result.hasErrors()) {
			return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
		}
		else {
			d.setId(cocineroID);
			this.cociService.saveCocinero(d);
			return "redirect:/empleados";
		}
	}
	
}
