package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.RepartidorService;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

	private static final String VIEWS_REPARTIDOR_CREATE_OR_UPDATE_FORM = "empleados/createOrUpdateRepartidorForm";
	
	private RepartidorService repartidorService;
	private PedidoService pedidoService;
	
	@Autowired
	public RepartidorController(RepartidorService rs, PedidoService ps) {
		this.repartidorService = rs;
		this.pedidoService = ps;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping()
	public String listadoRepartidores(ModelMap modelMap) {
		String vista = "repartidores/listadoRepartidores";
		Iterable<Repartidor> repartidores = repartidorService.findAll();
		modelMap.addAttribute("repartidores", repartidores);
		return vista;
	}
	
	// hecho por gonzalo
//	@GetMapping("/{repartidorId}")
//	public ModelAndView showRepartidor(@PathVariable("repartidorId") int repartidorId) {
//		ModelAndView mav = new ModelAndView("repartidores/repartidorRepartos");
//		mav.addObject(this.repartidorService.findRepartidorById(repartidorId));
//		return mav;
//	}
	
	// corregido? por dani
	@GetMapping("/{repartidorId}")
	public String showRepartidor(@PathVariable("repartidorId") int repartidorId, ModelMap model) {
		Optional<Repartidor> repartidor = this.repartidorService.findRepartidorById(repartidorId);
		if(repartidor.isPresent()) {
			model.addAttribute("repartidor", repartidor.get());
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
			repartidorService.deleteRepartidor(d.get());
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
			this.repartidorService.saveRepartidor(dep);
			return "redirect:/empleados";
		}
	}
	
	@GetMapping(value = "/save/{repartidorID}")
	public String initUpdateForm(@PathVariable("repartidorID") int repartidorID, Model model) {
		Optional<Repartidor> d = this.repartidorService.findRepartidorById(repartidorID);
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
			this.repartidorService.saveRepartidor(d);
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
