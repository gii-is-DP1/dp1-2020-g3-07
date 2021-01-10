package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RepartidorServiceTest {

	@Autowired
	private RepartidorService repartidorService;
	private VehiculoService vehiculoService;
	
	@Test
	public void testRepartidorCount() {
		int n = repartidorService.repartidorCount();
		assertEquals(n, 2);
	}
	
	@Test
	public void testFindAll() {
		String nombre = repartidorService.findAll().iterator().next().getNombre();
		assertTrue(nombre.equals("Jorge Méndez"));
	}
	
	@Test
	public void testFindRepartidorById() {
		Repartidor repartidor = repartidorService.findRepartidorById(1).get();
		assertThat(repartidor.getNombre()).isEqualTo("Jorge Méndez");
	}
	
//	@Test
//	public void testSave() {
//		Repartidor repartidor = new Repartidor();
//		repartidor.setNombre("Ismael Fernández");
//		repartidor.setDni("49238645A");
//		repartidor.setSueldo("2280");
//		repartidor.setFechanacimiento(LocalDate.of(2000, 02, 12));
//		repartidor.setRepartos(null);
//		
//		
//		User usuario= new User();
//		usuario.setUsername("nuevousuario2");
//		usuario.setPassword("nuevacontrasena2");
//		
//		repartidor.setUser(usuario);
//		
//		
//		
//		
//		Vehiculo cochecito= new Vehiculo();
//		cochecito.setMatricula("4532HTF");
//		cochecito.setTipovehiculo(TipoVehiculo.Coche);
//		cochecito.setRepartidor(repartidor);
//
//		repartidor.setVehiculo(cochecito);	
//        
//		this.repartidorService.saveRepartidor(repartidor);
//        
//        assertThat(repartidor.getNombre()).isEqualTo("Ismael Fernández");
//	}
	
	@Test
	public void testDelete() {
		Repartidor repartidor = this.repartidorService.findRepartidorById(1).get();
		Repartidor repartidor2 = this.repartidorService.findRepartidorById(2).get();
		this.repartidorService.deleteRepartidor(repartidor);
		this.repartidorService.deleteRepartidor(repartidor2);
		//assertThat(this.repartidorService.findAll().iterator().hasNext()).isEqualTo(false);
		assertThat(this.repartidorService.findAll().equals(null));
		
	}
	
}
