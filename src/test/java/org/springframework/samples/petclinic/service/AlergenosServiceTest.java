package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.model.AlergenoEnum;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class AlergenosServiceTest {
	@Autowired
	private AlergenoService alergenosService;
	
	@Test
	public void testCountWithInititalData() {
		int count = alergenosService.AlergenosCount();
		assertEquals(count,15);
	}
	
	
	@Test
	public void testFindAll() {
		Iterator<Alergeno> alergenos = alergenosService.findAll().iterator();
		
		List<Alergeno> listaAler = new ArrayList<>();
		while (alergenos.hasNext()) {
			listaAler.add(alergenos.next());
		}

		
		assertTrue(listaAler.size()==15);//15 es el numero de alergenos que tenemos registrados
	}
	

	@Test
	public void testFindAlergenosType() {
	Collection<Alergeno> tiposAler= alergenosService.findAlergenoTypes();
	

	assertTrue(tiposAler.size()==15);
	
	
	
	
	
	}	
	
}

	

