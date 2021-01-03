package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private static final String VIEWS_CLIENTE_INSERT_FORM = "clientes/altaClienteForm";
	private final ClienteService clienteService;
	
	private final AuthoritiesService authSer;
	
	
	@Autowired
	public ClienteController(ClienteService clienteService, AuthoritiesService as) {
		this.clienteService = clienteService;
		this.authSer = as;
	}
	
	
	
	@GetMapping()
	public String listadoCliente(ModelMap modelMap) {
		Iterable<Cliente> empleados = clienteService.findAll();
		modelMap.addAttribute("clientes", empleados);
		return "clientes/listadoClientes";
	}
	
	
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return VIEWS_CLIENTE_INSERT_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_CLIENTE_INSERT_FORM;
		}
		else {
			//creating owner, user and authorities
			cliente.getUser().setEnabled(true);
			this.clienteService.saveCliente(cliente);
			authSer.saveAuthorities(cliente.getUser().getUsername(), "cliente");
			return "redirect:/clientes";
		}
	}
	
}
