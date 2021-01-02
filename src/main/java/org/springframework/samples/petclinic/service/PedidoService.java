package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Transactional
	public int pedidoCount(){
		return (int)pedidoRepo.count();
	}
	
	@Transactional
	public Iterable<Pedido> findAll(){
		return pedidoRepo.findAll();
	}

	
	@Transactional(readOnly = true)
	public Optional<Pedido> findPedidoById(int id) throws DataAccessException {
		return pedidoRepo.findById(id);
	}

	
	@Transactional
	public void savePedido(Pedido pedido) throws DataAccessException {
		pedidoRepo.save(pedido);	
	}
	
	@Transactional
	public void deletePedido(Pedido pedido) throws DataAccessException {
		pedidoRepo.delete(pedido);	
	}
	
	@Transactional(readOnly = true)
	public Set<Pedido> findByEstadopedido(estadoPedido estadopedido){
		return this.pedidoRepo.findByEstadopedido(estadopedido);
	}
	
	@Transactional
	public Collection<Pedido> findPedidos(){
		Iterable<Pedido> pedidos = pedidoRepo.findAll();
		Collection<Pedido> res = StreamSupport.stream(pedidos.spliterator(), false).collect(Collectors.toList());
		return new ArrayList<Pedido>(res);
	}

}
