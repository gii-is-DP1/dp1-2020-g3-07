package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;

	
	@GetMapping()
	public String listadoVehiculos(ModelMap modelMap) {
		String vista = "vehiculos/listadoVehiculos";
		Iterable<Vehiculo> vehiculos = vehiculoService.findAll();
		modelMap.addAttribute("vehiculos", vehiculos);
		return vista;
	}
}
