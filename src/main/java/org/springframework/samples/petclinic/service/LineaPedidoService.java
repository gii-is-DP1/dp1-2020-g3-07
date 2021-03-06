package org.springframework.samples.petclinic.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.repository.LineaPedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LineaPedidoService {
	
//	@Autowired
	private LineaPedidoRepository lineaPedidoRepo;
	
	@Autowired
	public LineaPedidoService(LineaPedidoRepository lineaPedidoR) {
		this.lineaPedidoRepo = lineaPedidoR;
	}
	
	@Transactional
	public int pedidoCount(){
		return (int) lineaPedidoRepo.count();
	}
	
	@Transactional
	public Iterable<LineaPedido> findAll(){
		return lineaPedidoRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<LineaPedido> findLineaPedidoById(int id) throws DataAccessException {
		return lineaPedidoRepo.findById(id);
	}
	
	
	@Transactional
	public void saveLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
		lineaPedidoRepo.save(lineaPedido);	
	}
	
	@Transactional
	public void deleteLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
		lineaPedidoRepo.delete(lineaPedido);	
	}
}
