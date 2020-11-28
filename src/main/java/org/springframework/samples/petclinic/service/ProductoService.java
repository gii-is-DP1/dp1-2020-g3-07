package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository productoRepo;
	
	@Transactional
	public int productoCount() {
		return (int) productoRepo.count();
	}
	
	@Transactional
	public Iterable<Producto> findAll(){
		return productoRepo.findAll();
	}
	
	@Transactional
	public void saveProducto(Producto producto) throws DataAccessException {
		//creating owner
		productoRepo.save(producto);	
	}
	

}
