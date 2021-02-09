package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Tamanopizza;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VehiculoServiceTest {
	@Autowired
	private VehiculoService vehiculoService;
	
	@Test
	public void testCountWithInititalData() {
		int count = vehiculoService.vehiculoCount();
		assertEquals(count, 5);
	}
	
	@Test
	@Transactional
	public void shouldInsertVehiculo() {
		Collection<Vehiculo> vehiculoCollection = (Collection) this.vehiculoService.findAll();
		int found = vehiculoCollection.size();
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMatricula("1772HZC");
		vehiculo.setTipovehiculo(TipoVehiculo.Coche);
		this.vehiculoService.saveVehiculo(vehiculo);
		
		vehiculoCollection = (Collection) this.vehiculoService.findAll();
		assertEquals(vehiculoCollection.size(), found+1);
	}
	
	@Test
	@Transactional
	public void shouldDeleteVehiculo() {
		Collection<Vehiculo> vehiculoCollection = (Collection) this.vehiculoService.findAll();
		int found = vehiculoCollection.size();
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMatricula("1772HZC");
		vehiculo.setTipovehiculo(TipoVehiculo.Coche);
		this.vehiculoService.saveVehiculo(vehiculo);
		
		vehiculoCollection = (Collection) this.vehiculoService.findAll();
		assertEquals(vehiculoCollection.size(), found+1);
		
		this.vehiculoService.deleteVehiculo(vehiculo);
		vehiculoCollection = (Collection) this.vehiculoService.findAll();
		assertEquals(vehiculoCollection.size(), found);	
	}
	
	@Test
	@Transactional
	public void testCountTipo() {
		TipoVehiculo tipovehiculo = TipoVehiculo.Coche;
		assertEquals(this.vehiculoService.countTipo(tipovehiculo), 2);
	}
	
	@Test
	@Transactional
	public void testFindByMatricula(){
		Optional<Vehiculo> v = this.vehiculoService.findByMatricula("4772HZC");
		assertNotNull(v.get());
	}
	
}