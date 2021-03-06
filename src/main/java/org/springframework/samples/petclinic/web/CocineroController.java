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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			log.info("Cocinero con id = "+cocineroID+" eliminado con exito");
		} else {
			modelMap.addAttribute("message", "Cocinero no encontrado");
		}
		return "redirect:/empleados";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Cocinero dep = new Cocinero();
		model.put("cocinero", dep);
		log.info("Solicitud para dar de alta a un cocinero");
		return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Cocinero dep, BindingResult result) {
		if (result.hasErrors()) {
			log.info("El cocinero no se pudo dar de alta debido a errores de validacion de entrada de datos");
			return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.cociService.saveCocinero(dep);
			log.info("El cocinero de nombre "+dep.getNombre()+" se ha dado de alta con exito");
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/edit/{cocineroID}")
	public String initUpdateForm(@PathVariable("cocineroID") int cocineroID, Model model) {
		Optional<Cocinero> d = this.cociService.findCocineroById(cocineroID);
		if(d.isPresent()) {
			model.addAttribute("cocinero", d.get());
			log.info("Solicitud para editar cocinero con id = "+cocineroID);
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
			log.info("El cocinero con id = "+cocineroID+" no se pudo editar debido a errores de validacion de entrada de datos");
			return VIEWS_COCINERO_CREATE_OR_UPDATE_FORM;
		}
		else {
			d.setId(cocineroID);
			this.cociService.saveCocinero(d);
			log.info("El cocinero con id = "+cocineroID+" y nombre "+d.getNombre()+" se ha dado de alta con exito");
			return "redirect:/empleados";
		}
	}
	
}
