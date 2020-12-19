package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

	private static final String VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM = "empleados/createOrUpdateRepartidorForm";
	
	private RepartidorService repaService;
	
	@Autowired
	public RepartidorController(RepartidorService rs) {
		this.repaService = rs;
	}
	
	@GetMapping(value="/delete/{repartidorID}")
	public String borrarRepartidor(@PathVariable("repartidorID") int repartidorID, ModelMap modelMap) {
		Optional<Repartidor> d = repaService.findRepartidorById(repartidorID);
		if(d.isPresent()) {
			repaService.deleteRepartidor(d.get());
			modelMap.addAttribute("message", "Repartidor borrado correctamente");
		} else {
			modelMap.addAttribute("message", "Repartidor no encontrado");
		}
		return "redirect:/empleados";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Repartidor dep = new Repartidor();
		model.put("repartidor", dep);
		return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Repartidor dep, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.repaService.saveRepartidor(dep);
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/save/{repartidorID}")
	public String initUpdateForm(@PathVariable("repartidorID") int repartidorID, Model model) {
		Optional<Repartidor> d = this.repaService.findRepartidorById(repartidorID);
		if(d.isPresent()) {
			model.addAttribute("repartidor", d.get());
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Repartidor no encontrado");
			return "redirect:/empleados";
		}
	}

	@PostMapping(value = "/save/{repartidorID}")
	public String processUpdateForm(@Valid Repartidor d, BindingResult result,
			@PathVariable("repartidorID") int repartidorID) {
		if (result.hasErrors()) {
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			d.setId(repartidorID);
			this.repaService.saveRepartidor(d);
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/{repartidorID}/repartos")
	public String mostrarRepartos(Model model, @PathVariable("repartidorID") int repartidorID) {
		Optional<Repartidor> r = repaService.findRepartidorById(repartidorID);
		if(!r.isPresent()) {
			return "redirect: /empleados";
		}else {
			model.addAttribute("repartidor", r.get());
			Iterable<Reparto> repartos = r.get().getRepartos();
			model.addAttribute("repartos", repartos);
			return "repartidores/repartosRepartidor";
		}
	}
	
}
