package org.springframework.samples.petclinic.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;

public interface LineaPedidoRepository extends CrudRepository<LineaPedido, Integer>{

}
