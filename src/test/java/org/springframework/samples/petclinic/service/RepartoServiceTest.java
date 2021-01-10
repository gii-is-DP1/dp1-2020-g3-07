package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RepartoServiceTest {

	@Autowired
	private RepartoService repartoService;
	@Autowired
	private RepartidorService repartidorService;
	
	@Test
	@Transactional
	public void testSave() {
		Reparto reparto = new Reparto();
		reparto.setId(2);	
		
		LocalDate fecha = LocalDate.of(2021, 01, 10);
		reparto.setFecha(fecha);
		
		LocalTime horaInicio = LocalTime.now();
		reparto.setHoraInicio(horaInicio);	
		
		Repartidor repartidor = repartidorService.findRepartidorById(1).get();
		reparto.setRepartidor(repartidor);
		
		reparto.setPedidos(null);
		
		this.repartoService.save(reparto);
		Reparto r = this.repartoService.findRepartoById(2).get();
		
		
		assertNotNull(r);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByRepartidorId() {
		List<Reparto> repartos = this.repartoService.findByRepartidorId(1);
		assertTrue(repartos.size()==1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindRepartoById() {
		Reparto reparto = this.repartoService.findRepartoById(1).get();
		assertTrue(reparto.getRepartidor().getId()==1);
	}
	
	
}
