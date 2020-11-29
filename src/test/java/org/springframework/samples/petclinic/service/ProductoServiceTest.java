package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
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
}
