package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clientServ;
	
	@GetMapping()
	public String listadoCliente(ModelMap model) {
		Iterable<Cliente> empleados = clientServ.findAll();
		model.addAttribute("clientes", empleados);
		return "clientes/listadoClientes";
	}
	
}
