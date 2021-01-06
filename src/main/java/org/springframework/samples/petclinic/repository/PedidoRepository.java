package org.springframework.samples.petclinic.repository;



import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.tipoPedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	@Query(value = "SELECT * FROM PEDIDOS where ID = ?1", nativeQuery = true)
	Pedido resumenLineasPedido(int id);
  
	Set<Pedido> findByEstadopedido(estadoPedido estadopedido);
	
	Set<Pedido> findByEstadopedidoAndTipopedido(estadoPedido estadopedido, tipoPedido tipopedido);

	Set<Pedido> findByRepartoId(Integer repartoId);

}
