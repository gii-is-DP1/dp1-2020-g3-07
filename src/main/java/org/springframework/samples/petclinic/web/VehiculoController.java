package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@InitBinder("vehiculo")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new VehiculoValidator(vehiculoService));
	}
	
	@GetMapping()
	public String listadoVehiculo(ModelMap model) {
		Iterable<Vehiculo> vehiculos = vehiculoService.findAll();
		model.addAttribute("vehiculos", vehiculos);
		log.info("Se muestra el listado de vehiculos");
		return "vehiculos/listadoVehiculos";
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		TipoVehiculo moto = TipoVehiculo.Moto;
		TipoVehiculo coche = TipoVehiculo.Coche;
		List<TipoVehiculo> tiposVehiculo = new ArrayList<TipoVehiculo>(Arrays.asList(moto, coche));
		model.put("tiposVehiculo", tiposVehiculo);
		Vehiculo vehiculo = new Vehiculo();
//		vehiculo.setTipovehiculo(moto);
		model.put("vehiculo", vehiculo);
		log.info("Solicitud para anadir un nuevo vehiculo");
		return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Vehiculo vehiculo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			TipoVehiculo moto = TipoVehiculo.Moto;
			TipoVehiculo coche = TipoVehiculo.Coche;
			List<TipoVehiculo> tiposVehiculo = new ArrayList<TipoVehiculo>(Arrays.asList(moto, coche));
			model.addAttribute("tiposVehiculo", tiposVehiculo);
			log.info("El vehiculo no se pudo anadir debido a errores en la validacion de entrada de datos");
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.vehiculoService.saveVehiculo(vehiculo);
			log.info("El vehiculo de matricula "+vehiculo.getMatricula()+" se ha anadido con exito");
			return "redirect:/vehiculos";
		}
	}
	
	
	@GetMapping(value="/delete/{vehiculoID}")
	public String borrarVehiculo(@PathVariable("vehiculoID") int vehiculoID, ModelMap modelMap) {
		Optional<Vehiculo> vehiculo = vehiculoService.findVehiculoById(vehiculoID);
		if(vehiculo.isPresent()) {
			if(vehiculo.get().getRepartidor()!=null) return "redirect:/vehiculos";
			vehiculoService.deleteVehiculo(vehiculo.get());
			modelMap.addAttribute("message", "Vehiculo borrado correctamente");
			log.info("El vehiculo con id = "+vehiculoID+" se ha borrado con exito");
		} else {
			modelMap.addAttribute("message", "Vehiculo no encontrado");
		}
		return "redirect:/vehiculos";
	}
	
	@GetMapping(value = "/edit/{vehiculoID}")
	public String initUpdateForm(@PathVariable("vehiculoID") int vehiculoID, Model model) {
		Optional<Vehiculo> vehiculo = this.vehiculoService.findVehiculoById(vehiculoID);
		if(vehiculo.isPresent()) {
			model.addAttribute("vehiculo", vehiculo.get());
			TipoVehiculo moto = TipoVehiculo.Moto;
			TipoVehiculo coche = TipoVehiculo.Coche;
			List<TipoVehiculo> tiposVehiculo = new ArrayList<TipoVehiculo>(Arrays.asList(moto, coche));
			model.addAttribute("tiposVehiculo", tiposVehiculo);
			log.info("Solicitud para editar el vehiculo con id = "+vehiculoID);
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Vehiculo no encontrado");
			return "redirect:/vehiculos";
		}
	}

	@PostMapping(value = "/edit/{vehiculoID}")
	public String processUpdateForm(@Valid Vehiculo vehiculo, BindingResult result,
			@PathVariable("vehiculoID") int vehiculoID, Model model) {
		if (result.hasErrors()) {
			TipoVehiculo moto = TipoVehiculo.Moto;
			TipoVehiculo coche = TipoVehiculo.Coche;
			List<TipoVehiculo> tiposVehiculo = new ArrayList<TipoVehiculo>(Arrays.asList(moto, coche));
			model.addAttribute("tiposVehiculo", tiposVehiculo);
			log.info("No se pudo editar el vehiculo con id = "+vehiculoID+" debido a errores en la validacion de entrada de datos");
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}
		else {
			vehiculo.setId(vehiculoID);
			this.vehiculoService.saveVehiculo(vehiculo);
			log.info("El vehiculo con id = "+vehiculoID+" se ha editado con exito");
			return "redirect:/vehiculos";
		}
	}

}