package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private static final String VIEWS_CLIENTE_INSERT_FORM = "clientes/altaClienteForm";
	private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM = "clientes/createOrUpdateClienteForm";
	private static final String VIEWS_PEDIDO_VALORACION = "clientes/valoracionPedido";

	final ClienteService clienteService;
	private final PedidoService pedidoService;
	private final AuthoritiesService authSer;
	
	
	@Autowired
	public ClienteController(ClienteService clienteService, PedidoService pedidoService, AuthoritiesService as) {
		this.clienteService = clienteService;
		this.pedidoService = pedidoService;
		this.authSer = as;
	}
	
	
	
	@GetMapping()
	public String listadoCliente(ModelMap modelMap) {
		Iterable<Cliente> empleados = clienteService.findAll();
		modelMap.addAttribute("clientes", empleados);
		log.info("Mostrando listado de clientes");
		return "clientes/listadoClientes";
	}
	
	@GetMapping(value = "/perfil")
	public String clienteProfile(@AuthenticationPrincipal User user, ModelMap modelMap) {
		Cliente cliente = clienteService.findClienteByUsername(user.getUsername());
		modelMap.addAttribute("cliente", cliente);
		Set<Pedido> Setpedido = cliente.getPedidos();
		List<Pedido> res = new ArrayList<Pedido>();
		List<Integer> ids = new ArrayList<Integer>();
		ids = Setpedido.stream().map(p -> p.getId()).collect(Collectors.toList());
		for(int i=0; i<ids.size(); i++) {
			Pedido pedido = pedidoService.findPedidoById(ids.get(i)).get();
			res.add(pedido);
		}
		modelMap.addAttribute("pedidos", res);
		log.info("Mostrando perfil del cliente con nombre "+cliente.getNombre());
		return "clientes/perfilCliente";
	}
	
	@GetMapping(value = "/valorar/{pedidoId}")
	public String initValoracion(@PathVariable("pedidoId") int pedidoId, Model model) {
		Optional<Pedido> p = this.pedidoService.findPedidoById(pedidoId);
		if(p.isPresent()) {
			model.addAttribute("pedido", p.get());
			log.info("Solicitud para valorar pedido con id = "+pedidoId);
			return VIEWS_PEDIDO_VALORACION;
		} else {
			model.addAttribute("message", "Pedido no encontrado");
			return "redirect:/pedidos";
		}
	}

	@PostMapping(value = "/valorar/{pedidoId}")
	public String processValoracion(@Valid Pedido p, BindingResult result,
			@PathVariable("pedidoId") int pedidoId, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Error al validar");
			log.info("Error al realizar valoracion del pedido con id = "+pedidoId);
			return VIEWS_PEDIDO_VALORACION;
		}
		else {
			p.setId(pedidoId);
			pedidoService.savePedido(p);
//			Pedido pedido = pedidoService.findPedidoById(pedidoId).get();
//			BeanUtils.copyProperties(p, pedido, "comentario", "valoracion");
			log.info("Pedido con id = "+pedidoId+" asignado a una valoracion = "+p.getValoracion());
			return "redirect:/clientes/perfil";
		}
	}
	
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		log.info("Solicitud para dar de alta a un nuevo cliente");
		return VIEWS_CLIENTE_INSERT_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			log.info("Cliente no dado de alta debido a errores en validacion de entrada de datos");
			return VIEWS_CLIENTE_INSERT_FORM;
		}
		else {
			//creating owner, user and authorities
			cliente.getUser().setEnabled(true);
			this.clienteService.saveCliente(cliente);
			authSer.saveAuthorities(cliente.getUser().getUsername(), "cliente");
			log.info("Cliente de nombre "+cliente.getNombre()+" "+cliente.getApellidos()+" dado de alta con exito");
			return "redirect:/clientes";
		}
	}
	
	
	@GetMapping(value = "/edit/{clienteId}")
	public String initUpdateForm(@PathVariable("clienteId") int clienteId, Model model) {
		Optional<Cliente> c = this.clienteService.findClienteById(clienteId);
		if(c.isPresent()) {
			model.addAttribute("cliente", c.get());
			log.info("Solicitud de editar datos del cliente con id = "+clienteId);
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
			log.info("Cliente con id = "+clienteId+" no actualizado debido a errores en validacion de entrada de datos");
			return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
		}
		else {
			c.setId(clienteId);
			this.clienteService.saveCliente(c);
			log.info("Cliente con id = "+clienteId+" dado de alta con exito");
			return "clientes/perfilCliente";
		}
	}
	
}
