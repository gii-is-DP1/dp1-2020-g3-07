package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DependienteServiceTest {

	@Autowired
	private DependienteService dependienteService;
	
	@Test
	public void testDependienteCount() {
		int n = dependienteService.dependienteCount();
		assertEquals(n, 1);
	}
	
	@Test
	public void testFindAll() {
		String nombre = dependienteService.findAll().iterator().next().getNombre();
		assertTrue(nombre.equals("Paco Lastre"));
	}
	
	@Test
	public void testFindCocineroById() {
		Dependiente dependiente = dependienteService.findDependienteById(1).get();
		assertThat(dependiente.getNombre()).isEqualTo("Paco Lastre");
	}
	
	@Test
	public void testSave() {
		Dependiente dependiente = new Dependiente();
		dependiente.setNombre("Pedro");
		dependiente.setDni("49632645A");
		dependiente.setSueldo("1800");
		dependiente.setFechanacimiento(LocalDate.of(2000, 04, 12));
		User usuario= new User();
		usuario.setUsername("nuevousuario");
		usuario.setPassword("nuevacontrasena");
		dependiente.setUser(usuario);
		
		
        this.dependienteService.saveDependiente(dependiente);
        assertThat(dependiente.getNombre()).isEqualTo("Pedro");
	}
	
	@Test
	public void testDelete() {
		Dependiente dependiente = this.dependienteService.findDependienteById(1).get();
		this.dependienteService.deleteDependiente(dependiente);
		assertThat(this.dependienteService.findAll().iterator().hasNext()).isEqualTo(false);
	}
	
}
