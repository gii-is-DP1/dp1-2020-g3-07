package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

	private static final String VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM = "empleados/createOrUpdateRepartidorForm";
	
	private RepartidorService repartidorService;
	private PedidoService pedidoService;
	private RepartoService repartoService;
	private VehiculoService vehiculoService;
	
	@Autowired
	public RepartidorController(RepartidorService rs, PedidoService ps, RepartoService rss, VehiculoService vs) {
		this.repartidorService = rs;
		this.pedidoService = ps;
		this.repartoService = rss;
		this.vehiculoService = vs;
	}
	
	
	
	
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
//	@ModelAttribute("vehiculos")
//	public Collection<Vehiculo> getVehiculos(){
////		List<Vehiculo> vs = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
////		List<Vehiculo> res = new ArrayList<Vehiculo>();
////		for(Vehiculo v : vs) {
////			if(v.getRepartidor().getDni().isEmpty()) {
////				res.add(v);
////			}
////		}
////		return vs;
//		
//		List<Repartidor> repartidores = new ArrayList<Repartidor>(this.repartidorService.findRepartidores());
//		List<Vehiculo> vehiculosOcupados = repartidores.stream().map(r->r.getVehiculo()).collect(Collectors.toList());
//		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
//		vehiculos.removeAll(vehiculosOcupados);
//		
////		return this.vehiculoService.findVehiculo();
//		return vehiculos;
//	}
	
	
	@GetMapping()
	public String listadoRepartidores(ModelMap modelMap) {
		String vista = "repartidores/listadoRepartidores";
		Iterable<Repartidor> repartidores = repartidorService.findAll();
		modelMap.addAttribute("repartidores", repartidores);
		log.info("Se muestra un listado de los repartidores");
		return vista;
	}
	

//	@GetMapping("/{repartidorId}")
//	public ModelAndView showRepartidor(@PathVariable("repartidorId") int repartidorId) {
//		ModelAndView mav = new ModelAndView("repartidores/repartidorRepartos");
//		mav.addObject(this.repartidorService.findRepartidorById(repartidorId));
//		return mav;
//	}
	
	@GetMapping("/{repartidorId}")
	public String showRepartidor(@PathVariable("repartidorId") int repartidorId, ModelMap model) {
		Optional<Repartidor> repartidor = this.repartidorService.findRepartidorById(repartidorId);
		if(repartidor.isPresent()) {
			model.addAttribute("repartidor", repartidor.get());
			model.addAttribute("repartos", repartoService.findByRepartidorId(repartidor.get().getId()));
			log.info("Se muestra informacion del repartidor con id = "+repartidorId);
			return "repartidores/repartidorRepartos";
		}else {
//			model.addAttribute("message", "Repartidor no encontrado!");
			return "redirect:/repartidores";
		}
		
	}
	
	@GetMapping(value="/delete/{repartidorID}")
	public String borrarRepartidor(@PathVariable("repartidorID") int repartidorID, ModelMap modelMap) {
		Optional<Repartidor> d = repartidorService.findRepartidorById(repartidorID);
		if(d.isPresent()) {
			Repartidor r = d.get();
			Vehiculo v = r.getVehiculo();
			v.setRepartidor(null);
			vehiculoService.saveVehiculo(v);
			repartidorService.deleteRepartidor(r);
			modelMap.addAttribute("message", "Repartidor borrado correctamente");
			log.info("Repartidor con id = "+repartidorID+" dado de baja con exito");
		} else {
			modelMap.addAttribute("message", "Repartidor no encontrado");
		}
		return "redirect:/empleados";
	}
	
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		
		
		List<Repartidor> repartidores = new ArrayList<Repartidor>(this.repartidorService.findRepartidores());
		List<Vehiculo> vehiculosOcupados = repartidores.stream().map(r->r.getVehiculo()).collect(Collectors.toList());
		List<Vehiculo> vehiculosNoOcupados = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
		vehiculosNoOcupados.removeAll(vehiculosOcupados);
		
		model.put("vehiculos", vehiculosNoOcupados);
		
		
		Repartidor dep = new Repartidor();
		model.put("repartidor", dep);
		log.info("Solicitud para dar de alta a un nuevo repartidor");
		return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Repartidor dep, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			
			
			List<Repartidor> repartidores = new ArrayList<Repartidor>(this.repartidorService.findRepartidores());
			List<Vehiculo> vehiculosOcupados = repartidores.stream().map(r->r.getVehiculo()).collect(Collectors.toList());
			List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
			vehiculos.removeAll(vehiculosOcupados);
			
			model.addAttribute("vehiculos", vehiculos);
			
			log.info("Error al dar de alta al repartidor debido a errores en la validacion de entrada de datos");
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.repartidorService.saveRepartidor(dep);
			log.info("Repartidor de nombre "+dep.getNombre()+" dado de alta con exito");
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/edit/{repartidorID}")
	public String initUpdateForm(@PathVariable("repartidorID") int repartidorID, Model model) {
		Optional<Repartidor> d = this.repartidorService.findRepartidorById(repartidorID);
		if(d.isPresent()) {
			
			
			List<Repartidor> repartidores = new ArrayList<Repartidor>(this.repartidorService.findRepartidores());
			List<Vehiculo> vehiculosOcupados = repartidores.stream().map(r->r.getVehiculo()).collect(Collectors.toList());
			List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
			vehiculos.removeAll(vehiculosOcupados);
			vehiculos.add(d.get().getVehiculo());
			
			model.addAttribute("vehiculos", vehiculos);
			
			model.addAttribute("repartidor", d.get());
			log.info("Solicitud para editar el repartidor con id = "+repartidorID);
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Repartidor no encontrado");
			return "redirect:/empleados";
		}
	}

	@PostMapping(value = "/edit/{repartidorID}")
	public String processUpdateForm(@Valid Repartidor d, BindingResult result,
			@PathVariable("repartidorID") int repartidorID, ModelMap model) {
		if (result.hasErrors()) {
			
			
			List<Repartidor> repartidores = new ArrayList<Repartidor>(this.repartidorService.findRepartidores());
			List<Vehiculo> vehiculosOcupados = repartidores.stream().map(r->r.getVehiculo()).collect(Collectors.toList());
			List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(this.vehiculoService.findVehiculo());
			vehiculos.removeAll(vehiculosOcupados);
			vehiculos.add(d.getVehiculo());
			
			model.addAttribute("vehiculos", vehiculos);
			
			log.info("Error al editar el repartidor con id = "+repartidorID+" debido a errores de validacion de entrada de datos");
			return VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			d.setId(repartidorID);
			this.repartidorService.saveRepartidor(d);
			log.info("Repartidor con id = "+repartidorID+" editado con exito");
			return "redirect:/empleados";
		}
	}
	
//	@GetMapping(value = "/{repartidorID}/repartos")
//	public String mostrarRepartos(Model model, @PathVariable("repartidorID") int repartidorID) {
//		Optional<Repartidor> r = repaService.findRepartidorById(repartidorID);
//		if(!r.isPresent()) {
//			return "redirect: /empleados";
//		}else {
//			model.addAttribute("repartidor", r.get());
//			Iterable<Reparto> repartos = r.get().getRepartos();
//			model.addAttribute("repartos", repartos);
//			return "repartidores/repartosRepartidor";
//		}
//	}
	
}
