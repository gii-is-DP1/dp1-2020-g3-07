package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.AlergenoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
	
	@Slf4j
	@Controller
	@RequestMapping("/alergenos")
	public class AlergenoController {
		
		@Autowired
		private AlergenoService AlergenoService;

		
		@GetMapping()
		public String listadoAlergenos(ModelMap modelMap) {
			String vista = "alergenos/listadoAlergenos";
			Iterable<Alergeno> alergenos = AlergenoService.findAll();
			modelMap.addAttribute("alergenos", alergenos);
			log.info("Mostrando listado de alergenos");
			return vista;
		}
		
		@ModelAttribute("alergenos")
		public Collection<Alergeno> populateAlergenoTypes() {
			return this.AlergenoService.findAlergenoTypes();
		}
}
