package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CocineroServiceTest {

	@Autowired
	private CocineroService cocineroService;
	
	@Test
	public void testCocineroCount() {
		int n = cocineroService.cocineroCount();
		assertEquals(n, 1);
	}
	
	@Test
	public void testFindAll() {
		String nombre = cocineroService.findAll().iterator().next().getNombre();
		assertTrue(nombre.equals("Cristian Andorra"));
	}
	
	@Test
	public void testFindCocineroById() {
		Cocinero cocinero = cocineroService.findCocineroById(1).get();
		assertThat(cocinero.getNombre()).isEqualTo("Cristian Andorra");
	}
	
	@Test
	public void testSave() {
		Cocinero cocinero = new Cocinero();
        cocinero.setNombre("Ismael");
        cocinero.setDni("49678645A");
        cocinero.setSueldo("2000");
        cocinero.setFechanacimiento(LocalDate.of(2001, 02, 12));
        this.cocineroService.saveCocinero(cocinero);
        assertThat(cocinero.getNombre()).isEqualTo("Ismael");
	}
	
	@Test
	public void testDelete() {
		Cocinero cocinero = this.cocineroService.findCocineroById(1).get();
		this.cocineroService.deleteCocinero(cocinero);
		assertThat(this.cocineroService.findAll().iterator().hasNext()).isEqualTo(false);
	}
	
}
