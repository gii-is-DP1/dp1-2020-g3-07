package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Empleados;
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
	private static final String VIEWS_EMPLEADO_UPDATE_FORM = "empleados/editEmpleado";
	
	@Autowired
	private EmpleadoService empleadoService;

	
	@GetMapping()
	public String listadoEmpleados(ModelMap modelMap) {
		String vista = "empleados/listadoEmpleados";
		Iterable<Empleados> empleados = empleadoService.findAll();
		modelMap.addAttribute("empleados", empleados);
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
