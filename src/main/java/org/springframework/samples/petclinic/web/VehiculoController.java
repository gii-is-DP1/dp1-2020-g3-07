package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	@Autowired
	private static final String VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";
	private final VehiculoService vehiculoService;
	
	@Autowired
	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}
	
	@GetMapping()
	public String listadoVehiculo(ModelMap model) {
		Iterable<Vehiculo> vehiculos = vehiculoService.findAll();
		model.addAttribute("vehiculos", vehiculos);
		return "vehiculos/listadoVehiculos";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculos", vehiculo);
		return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Vehiculo vehiculo, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.vehiculoService.saveVehiculo(vehiculo);
			return "redirect:/vehiculos";
		}
	}
	
	
	@GetMapping(value="/delete/{vehiculoID}")
	public String borrarVehiculo(@PathVariable("vehiculoID") int vehiculoID, ModelMap modelMap) {
		String vista = "vehiculos/listadoVehiculos";
		Optional<Vehiculo> vehiculo = vehiculoService.findVehiculoById(vehiculoID);
		if(vehiculo.isPresent()) {
			vehiculoService.deleteVehiculo(vehiculo.get());
			modelMap.addAttribute("message", "Vehiculo borrado correctamente");
		} else {
			modelMap.addAttribute("message", "Vehiculo no encontrado");
		}
		return vista;
	}
	
	@GetMapping(value = "/save/{vehiculoID}")
	public String initUpdateForm(@PathVariable("vehiculoID") int vehiculoID, Model model) {
		Optional<Vehiculo> vehiculo = this.vehiculoService.findVehiculoById(vehiculoID);
		if(vehiculo.isPresent()) {
			model.addAttribute("vehiculos", vehiculo.get());
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Vehiculo no encontrado");
			return "redirect:/vehiculos";
		}
	}

	@PostMapping(value = "/save/{vehiculoID}")
	public String processUpdateForm(@Valid Vehiculo vehiculo, BindingResult result,
			@PathVariable("vehiculoID") int vehiculoID) {
		if (result.hasErrors()) {
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}
		else {
			vehiculo.setId(vehiculoID);
			this.vehiculoService.saveVehiculo(vehiculo);
			return "redirect:/vehiculos";
		}
	}

}