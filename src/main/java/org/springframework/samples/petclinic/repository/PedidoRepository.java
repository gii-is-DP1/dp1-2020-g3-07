package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	@Query(value = "SELECT * FROM PEDIDOS where ID = ?1", nativeQuery = true)
	Pedido resumenLineasPedido(int id);
}
