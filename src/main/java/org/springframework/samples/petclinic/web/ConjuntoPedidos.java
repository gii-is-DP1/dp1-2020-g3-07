package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Vehiculo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ConjuntoPedidos {

	private List<Pedido> pedidosAsignados;
	
//	private List<Vehiculo> vehiculo;
	
//	private Collection<Pedido> pedidosAsignados;
	
//	private int[] ids;
	
}
