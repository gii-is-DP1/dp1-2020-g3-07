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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */

@Slf4j
@Controller
public class UserController {

//	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";
	private static final String VIEWS_CLIENTE_INSERT_FORM = "clientes/altaClienteForm";

	//private final OwnerService ownerService;
	private final ClienteService cliSer;
	
	private final AuthoritiesService authSer;

//	@Autowired
//	public UserController(OwnerService clinicService) {
//		this.ownerService = clinicService;
//	}
	@Autowired
	public UserController(ClienteService clinicService, AuthoritiesService as) {
		this.cliSer = clinicService;
		this.authSer = as;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

//	@GetMapping(value = "/users/new")
//	public String initCreationForm(Map<String, Object> model) {
//		Owner owner = new Owner();
//		model.put("owner", owner);
//		return VIEWS_OWNER_CREATE_FORM;
//	}
	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente c = new Cliente();
		model.put("cliente", c);
		log.info("Solicitud para dar de alta a un nuevo cliente");
		return VIEWS_CLIENTE_INSERT_FORM;
	}

//	@PostMapping(value = "/users/new")
//	public String processCreationForm(@Valid Owner owner, BindingResult result) {
//		if (result.hasErrors()) {
//			return VIEWS_OWNER_CREATE_FORM;
//		}
//		else {
//			//creating owner, user, and authority
//			this.ownerService.saveOwner(owner);
//			return "redirect:/";
//		}
//	}
	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Cliente c, BindingResult result) {
		if (result.hasErrors()) {
			log.info("El cliente no se pudo dar de alta debido a errores en la validacion de entrada de datos");
			return VIEWS_CLIENTE_INSERT_FORM;
		}
		else {
			//creating owner, user, and authority
			c.getUser().setEnabled(true);
			this.cliSer.saveCliente(c);
			authSer.saveAuthorities(c.getUser().getUsername(), "cliente");
			log.info("Se ha dado de alta el cliente de nombre "+c.getNombre()+" con exito");
			return "redirect:/";
		}
	}

}
