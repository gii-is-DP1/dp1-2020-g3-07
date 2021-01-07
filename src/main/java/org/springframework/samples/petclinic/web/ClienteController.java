package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private static final String VIEWS_CLIENTE_INSERT_FORM = "clientes/altaClienteForm";
	private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM = "clientes/createOrUpdateClienteForm";

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
	
	@GetMapping(value = "/perfil")
	public String clienteProfile(@AuthenticationPrincipal User user, ModelMap modelMap) {
		Cliente cliente = clienteService.findClienteByUsername(user.getUsername());
		modelMap.addAttribute("cliente", cliente);
		return "clientes/perfilCliente";
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
	
	@GetMapping(value = "/edit/{clienteId}")
	public String initUpdateForm(@PathVariable("clienteId") int clienteId, Model model) {
		Optional<Cliente> c = this.clienteService.findClienteById(clienteId);
		if(c.isPresent()) {
			model.addAttribute("cliente", c.get());
			return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "Cliente no encontrado");
			return "welcome";
		}
	}

	@PostMapping(value = "/edit/{clienteId}")
	public String processUpdateForm(@Valid Cliente c, BindingResult result,
			@PathVariable("clienteId") int clienteId) {
		if (result.hasErrors()) {
			return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
		}
		else {
			c.setId(clienteId);
			this.clienteService.saveCliente(c);
			return "clientes/perfilCliente";
		}
	}
	
}
