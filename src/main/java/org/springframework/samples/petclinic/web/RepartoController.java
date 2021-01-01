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
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
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

	@Autowired
	public RepartoController(RepartoService repartoService, RepartidorService repartidorService,
			PedidoService pedidoService) {
		this.repartoService = repartoService;
        this.repartidorService = repartidorService;
        this.pedidoService = pedidoService;
	}
	
	@ModelAttribute("repartidor")
	public Repartidor findRepartidor(@PathVariable("repartidorId") int repartidorId) {
		return this.repartidorService.findRepartidorById(repartidorId).get();
	}

//	@ModelAttribute("types")
//	public Collection<PetType> populatePetTypes() {
//		return this.petService.findPetTypes();
//	}
//
//	@ModelAttribute("owner")
//	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
//		return this.ownerService.findOwnerById(ownerId);
//	}
//        
//        /*@ModelAttribute("pet")
//	public Pet findPet(@PathVariable("petId") Integer petId) {
//            Pet result=null;
//		if(petId!=null)
//                    result=this.clinicService.findPetById(petId);
//                else
//                    result=new Pet();
//            return result;
//	}*/
//                
//	@InitBinder("owner")
//	public void initOwnerBinder(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}
//
//	@InitBinder("pet")
//	public void initPetBinder(WebDataBinder dataBinder) {
//		dataBinder.setValidator(new PetValidator());
//	}
//
	@GetMapping(value = "/repartos/new")
	public String initCreationForm(Repartidor repartidor, ModelMap model) {
		Reparto reparto = new Reparto();
		LocalDate localDate = LocalDate.now();
		reparto.setFecha(localDate);
//		reparto.setRepartidor(repartidorId);
		repartidor.addReparto(reparto);
		model.put("reparto", reparto);
		
		estadoPedido ep = estadoPedido.pendiente;
		Set<Pedido> pedidosSinAsignar = pedidoService.findByEstadopedido(ep);
		
		model.addAttribute("pedidosSinAsignar", pedidosSinAsignar);
		
		return "pedidos/listadoPedidosRepartidor";
	}
//
//	@PostMapping(value = "/pets/new")
//	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {		
//		if (result.hasErrors()) {
//			model.put("pet", pet);
//			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//                    try{
//                    	owner.addPet(pet);
//                    	this.petService.savePet(pet);
//                    }catch(DuplicatedPetNameException ex){
//                        result.rejectValue("name", "duplicate", "already exists");
//                        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//                    }
//                    return "redirect:/owners/{ownerId}";
//		}
//	}
//
//	@GetMapping(value = "/pets/{petId}/edit")
//	public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model) {
//		Pet pet = this.petService.findPetById(petId);
//		model.put("pet", pet);
//		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//	}
//
//    /**
//     *
//     * @param pet
//     * @param result
//     * @param petId
//     * @param model
//     * @param owner
//     * @param model
//     * @return
//     */
//        @PostMapping(value = "/pets/{petId}/edit")
//	public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner,@PathVariable("petId") int petId, ModelMap model) {
//		if (result.hasErrors()) {
//			model.put("pet", pet);
//			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//                        Pet petToUpdate=this.petService.findPetById(petId);
//			BeanUtils.copyProperties(pet, petToUpdate, "id","owner","visits");                                                                                  
//                    try {                    
//                        this.petService.savePet(petToUpdate);                    
//                    } catch (DuplicatedPetNameException ex) {
//                        result.rejectValue("name", "duplicate", "already exists");
//                        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//                    }
//			return "redirect:/owners/{ownerId}";
//		}
//	}

}
