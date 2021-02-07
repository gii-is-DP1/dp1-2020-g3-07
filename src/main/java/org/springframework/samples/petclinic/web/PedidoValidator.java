package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;




public class PedidoValidator implements Validator {
	
	
	private PedidoService pedidoService;

	@Override
	public void validate(Object target, Errors errors) {
		Pedido pedido= (Pedido) target;
		String repartidor= pedido.getReparto().getRepartidor().getNombre();
		Collection<Pedido> repartos = pedidoService.findPedidos();
		for(Pedido a : repartos) {
			if(a.getReparto().getRepartidor()==pedido.getReparto().getRepartidor()) {
				if(a.getFecha()==pedido.getFecha()) {
					if(a.getReparto().getRepartidor().getVehiculo()==pedido.getReparto().getRepartidor().getVehiculo()) {
						errors.rejectValue(repartidor, repartidor, "Un pedido no puede ser repartido a la misma hora con el mismo vehículo por le mismo repartidor");
					}
				}
			}
			
		}
		
	}
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Pedido.class.isAssignableFrom(clazz);
	}

}
