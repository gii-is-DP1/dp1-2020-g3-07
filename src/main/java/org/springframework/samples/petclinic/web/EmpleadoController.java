package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.model.Empleados;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.DependienteService;
import org.springframework.samples.petclinic.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	
//	private static final String VIEWS_EMPLEADO_UPDATE_FORM = "empleados/editEmpleado";
	
//	@Autowired
//	private EmpleadoService empleadoService;
	
	
	
	@Autowired
	private DependienteService depeService;

	
	@GetMapping()
	public String listadoEmpleados(ModelMap modelMap) {
		String vista = "empleados/listadoEmpleados";
		
		Iterable<Dependiente> dependientes = depeService.findAll();
		// aqui se crearian tambien los iterables cocineros y repartidores
		
//		Iterable<Empleados> empleados = empleadoService.findAll();
//		modelMap.addAttribute("empleados", empleados);
		
		modelMap.addAttribute("dependientes", dependientes);
		// aqui se añadirian al modelMap los iterables cocineros y repartidores antes creados
		
		return vista;
	}
	
	
	
	
	
	
//	@PostMapping(path="/save/{empleadoId}")
//	public String salvarEvento(@Valid Empleados empleado, BindingResult result, ModelMap modelMap) {
//		String view = "empleados/listadoEmpleados";
//		if(result.hasErrors()) {
//			modelMap.addAttribute("empleado", empleado);
//		}else {
//			empleadoService.save(empleado);
//			modelMap.addAttribute("message", "Empleado succesfully saved!");
//		}
//		return view;
//	}
//	
//	@GetMapping(value = "/empleados/{empleadoId}/edit")
//    public String initUpdateOwnerForm(@PathVariable("empleadoId") int empleadoId, Model model) {
//        Empleados empleado = this.empleadoService.findEmpleadoById(empleadoId);
//        model.addAttribute(empleado);
//        return VIEWS_EMPLEADO_UPDATE_FORM;
//    }
//
//    @PostMapping(value = "/empleados/{empleadoId}/edit")
//    public String processUpdateOwnerForm(@Valid Empleados empleado, BindingResult result,
//            @PathVariable("empleadoId") int empleadoId) {
//        if (result.hasErrors()) {
//            return VIEWS_EMPLEADO_UPDATE_FORM;
//        }
//        else {
//            empleado.setId(empleadoId);
//            this.empleadoService.save(empleado);
//            return "redirect:/empleados/{empleadoId}";
//        }
//    }
	
	
	
	
	
	
	
	
	
	
	
	
}
