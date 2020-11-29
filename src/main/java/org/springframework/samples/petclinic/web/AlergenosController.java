package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Empleados;
import org.springframework.samples.petclinic.service.AlergenosService;
import org.springframework.samples.petclinic.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
	
	@Controller
	@RequestMapping("/Alergenos")
	public class AlergenosController {
		
		@Autowired
		private AlergenosService AlergenosService;

		
		@GetMapping()
		public String listadoAlergenos(ModelMap modelMap) {
			String vista = "alergenos/listadoAlergenos";
			Iterable<Alergenos> alergenos = AlergenosService.findAll();
			modelMap.addAttribute("alergenos", alergenos);
			return vista;
		}
}
