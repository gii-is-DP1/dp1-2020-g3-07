package org.springframework.samples.petclinic.service;

import java.util.Optional;

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
	
	@Transactional(readOnly = true)
	public Optional<Producto> findProductoById(int id) throws DataAccessException {
		return productoRepo.findById(id);
	}

	
	@Transactional
	public void saveProducto(Producto producto) throws DataAccessException {
		productoRepo.save(producto);	
	}
	
	@Transactional
	public void deleteProducto(Producto producto) throws DataAccessException {
		productoRepo.delete(producto);	
	}
	
	
	

}
