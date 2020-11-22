package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Provider.Service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class AlergenosTest {
	@Autowired
	private AlergenosService AlergenosService;
	
	@Test
	public void testCountWithInititalData() {
		int count = AlergenosService.AlergenosCount();
		assertEquals(count,1);
	}
	
}
