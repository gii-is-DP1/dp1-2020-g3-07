package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.estadoPedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	
	Set<Pedido> findByEstadopedido(estadoPedido estadopedido);
	
}
