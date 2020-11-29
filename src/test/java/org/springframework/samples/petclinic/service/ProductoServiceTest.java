package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Tamanopizza;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProductoServiceTest {
	
	@Autowired
	private ProductoService productoService;
	
	@Test
	public void testCountWithInititalData() {
		int count = productoService.productoCount();
		assertEquals(count,1);
	}
	
	@Test
	public void testFindById() {
		String nombre = productoService.findProductoById(1).get().getName();
		assertEquals(nombre, "Pizza Margarita");
	}
	
	@Test
	@Transactional
	public void shouldInsertProducto() {
		Producto producto = new Producto();
		producto.setName("Antonio");
		producto.setPrecio(15);
		producto.setTamanopizza(Tamanopizza.familiar);
		this.productoService.saveProducto(producto);
		assertThat(producto.getId().longValue()).isEqualTo(2);
	}

	@Test
	@Transactional
	void shouldUpdateProductos() {
		Optional<Producto> producto = this.productoService.findProductoById(1);
		String oldName = producto.get().getName();
		String newName = oldName + "X";

		producto.get().setName(newName);
		this.productoService.saveProducto(producto.get());

		// retrieving new name from database
		producto = this.productoService.findProductoById(1);
		assertThat(producto.get().getName()).isEqualTo(newName);
	}
}
