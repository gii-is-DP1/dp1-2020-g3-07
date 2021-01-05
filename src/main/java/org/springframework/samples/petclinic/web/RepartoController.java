/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaPedidoService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/repartidores/{repartidorId}")
public class RepartoController {

//	private static final String VIEWS_REPARTOS_CREATE_OR_UPDATE_FORM = "repartos/createOrUpdateRepartoForm";

	private final RepartoService repartoService;
    private final RepartidorService repartidorService;
    private final PedidoService pedidoService;
    private final VehiculoService vehiculoService;
    private final LineaPedidoService lineaPedidoService;
    private final ClienteService clienteService;
    
	@Autowired
	public RepartoController(RepartoService repartoService, RepartidorService repartidorService,
			PedidoService pedidoService, VehiculoService vehiculoService, LineaPedidoService lineaPedidoService,
				ClienteService clienteService) {
		this.repartoService = repartoService;
        this.repartidorService = repartidorService;
        this.pedidoService = pedidoService;
        this.vehiculoService = vehiculoService;
        this.lineaPedidoService = lineaPedidoService;
        this.clienteService = clienteService;
	}
	
//	@ModelAttribute("vehiculos")
//	public Collection<Vehiculo> populateVehiculos() {
//		return this.vehiculoService.findVehiculo();
//	}
	
	@ModelAttribute("repartidor")
	public Repartidor findRepartidor(@PathVariable("repartidorId") int repartidorId) {
		return this.repartidorService.findRepartidorById(repartidorId).get();
	}
	
	@InitBinder("vehiculo")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping(value = "/repartos/new")
	public String initCreationForm(ModelMap model) {
		ConjuntoPedidos cp = new ConjuntoPedidos();
		model.addAttribute("command", cp);
		
		
		return "repartidores/listadoPedidosRepartidor";
	}
	
	@PostMapping(value = "/repartos/new")
	public String initCreationForm(@ModelAttribute("SpringWeb")ConjuntoPedidos cp, ModelMap model, Repartidor repartidor) {
		
		if(cp.getPedidosAsignados().size()>4 || cp.getPedidosAsignados().size()<1) {
			model.addAttribute("command", cp);
			model.addAttribute("message", "Elija entre 1 y 4 pedidos");
			return "repartidores/listadoPedidosRepartidor";
		}else {
		
			Reparto reparto = new Reparto();
			LocalDate localDate = LocalDate.now();
			reparto.setFecha(localDate);
			LocalTime lt = LocalTime.now();
			reparto.setHoraInicio(lt);
			
			Set<Pedido> pedidos = new HashSet<Pedido>(cp.getPedidosAsignados());
			estadoPedido ep = estadoPedido.enReparto;
			pedidos.stream().forEach(p->p.setEstadopedido(ep));
			reparto.setPedidos(pedidos);
			reparto.setRepartidor(repartidor);
			
			repartoService.save(reparto);
			
	//		return "redirect:/repartidores";
			return "redirect:/repartidores/" + repartidor.getId();
			
		}

	}
	
	@ModelAttribute("pedidosList")
	public List<Pedido> getPedidosList(){
		estadoPedido ep = estadoPedido.pendiente;
		tipoPedido tp = tipoPedido.aDomicilio;
		Set<Pedido> pedidosSinAsignar = pedidoService.findByEstadopedidoAndTipopedido(ep, tp);
		return new ArrayList<Pedido>(pedidosSinAsignar);
	}
	
	
	@GetMapping("repartos/{repartoId}")
	public String showRepartos(@PathVariable("repartoId") int repartoId, ModelMap model, Repartidor repartidor) {
		
		Optional<Reparto> r = repartoService.findRepartoById(repartoId);
		if(r.isPresent()) {
			model.addAttribute("reparto", r.get());
			return "repartos/infoReparto";
		}else {
			return "redirect:/repartidores";
		}
		
	}
	
	
	@GetMapping(value = "repartos/{repartoId}/{pedidoId}/entregado")
	public String cambioaEntregado(@PathVariable("repartidorId") int repartidorId, @PathVariable("repartoId") int repartoId, @PathVariable("pedidoId") int pedidoId, ModelMap modelMap) {
		Pedido pedido = pedidoService.findPedidoById(pedidoId).get();
		pedido.setEstadopedido(estadoPedido.Entregado);
		pedidoService.savePedido(pedido);
//		modelMap.put("pedido", pedido);
		return "redirect:/repartidores/" + repartidorId + "/repartos/" + repartoId;
	}
	
	@GetMapping(value = "repartos/{repartoId}/cliente/{clienteId}")
	public String showInfoCliente(@PathVariable("clienteId") int clienteId, @PathVariable("repartoId") int repartoId, ModelMap modelMap) {
		Cliente cliente = clienteService.findClienteById(clienteId).get();
		modelMap.addAttribute("cliente", cliente);
		Reparto reparto = repartoService.findRepartoById(repartoId).get();
		modelMap.addAttribute("reparto", reparto);
		return "clientes/infoCliente";
	}
	
	@GetMapping(value = "repartos/{repartoId}/volver")
	public String backToReparto(@PathVariable("repartoId") int repartoId, @PathVariable("repartidorId") int repartidorId, ModelMap model) {

		return "redirect:/repartidores/" + repartidorId + "/repartos/" + repartoId;

	}

}
