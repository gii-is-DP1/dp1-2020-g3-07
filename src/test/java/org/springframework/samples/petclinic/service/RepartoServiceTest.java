package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RepartoServiceTest {

	@Autowired
	private RepartoService repartoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testSave() {
		Reparto reparto = new Reparto();
		LocalDate fecha = LocalDate.now();
		reparto.setFecha(fecha);
		LocalTime horaInicio = LocalTime.now();
		reparto.setHoraInicio(horaInicio);
		
	}
	
}
