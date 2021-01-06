package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EmpleadoServiceTest {
	
	@Autowired
	private CocineroService cocineroService;
	@Autowired
	private DependienteService dependienteService;
	@Autowired
	private RepartidorService repartidorService;
	
	@Test
	public void testCountWithInititalData() {
		int c1 = cocineroService.cocineroCount();
		int c2 = dependienteService.dependienteCount();
		int c3 = repartidorService.repartidorCount();
		int ctotal = c1+c2+c3;
		assertEquals(ctotal,3);
	}
	
	
	@Test
	public void shouldCocinero() {
        Cocinero cocinero = new Cocinero();
        cocinero.setNombre("Ismael");
        cocinero.setDni("49678645A");
        cocinero.setSueldo("2000");
        cocinero.setFechanacimiento(LocalDate.of(2001, 02, 12));
        this.cocineroService.saveCocinero(cocinero);
        assertThat(cocinero.getNombre()).isEqualTo("Ismael"); 

    }
	
	@Test
	public void shouldDependiente() {
        Dependiente dependiente = new Dependiente();
        dependiente.setNombre("Pedro");
        dependiente.setDni("63748261A");
        dependiente.setSueldo("1500");
        dependiente.setFechanacimiento(LocalDate.of(2000, 10, 12));

        this.dependienteService.saveDependiente(dependiente);
        assertThat(dependiente.getNombre()).isEqualTo("Pedro");    

    }
	
	@Test
	public void shouldRepartidor() {
		Repartidor repartidor = new Repartidor();
		repartidor.setNombre("Juan");
		repartidor.setDni("83591261A");
		repartidor.setSueldo("1300");
		repartidor.setFechanacimiento(LocalDate.of(1998, 10, 22));
//		repartidor.setUsuario("Isma");
//		repartidor.setContrasena("123456");
		User user = new User();
		user.setUsername("Isma");
		user.setPassword("123456");
		user.setEnabled(false);
		repartidor.setUser(user);

        this.repartidorService.saveRepartidor(repartidor);
        assertThat(repartidor.getNombre()).isEqualTo("Pedro");

    }
	
	@Test
    @Transactional
    public void shouldUpdateCocinero() {
        Optional<Cocinero> cocineros = this.cocineroService.findCocineroById(1);
        
        String sueldo = cocineros.get().getSueldo();
        Integer newSueldo = Integer.valueOf(sueldo) + 200;
        
        cocineros.get().setSueldo(newSueldo.toString());
        this.cocineroService.saveCocinero(cocineros.get());

        // retrieving new name from database
        cocineros = this.cocineroService.findCocineroById(1);
        assertThat(cocineros.get().getSueldo().equals(newSueldo));
    }
	
	@Test
    @Transactional
    public void shouldUpdateDependiente() {
        Optional<Dependiente> dependientes = this.dependienteService.findDependienteById(1);
        
        String sueldo = dependientes.get().getSueldo();
        Integer newSueldo = Integer.valueOf(sueldo) + 100;
        
        dependientes.get().setSueldo(newSueldo.toString());
        this.dependienteService.saveDependiente(dependientes.get());

        // retrieving new name from database
        dependientes = this.dependienteService.findDependienteById(1);
        assertThat(dependientes.get().getSueldo().equals(newSueldo));
    }
	
	@Test
    @Transactional
    public void shouldUpdateRepartidor() {
        Optional<Repartidor> repartidores = this.repartidorService.findRepartidorById(1);
        
        String sueldo = repartidores.get().getSueldo();
        Integer newSueldo = Integer.valueOf(sueldo) + 400;
        
        repartidores.get().setSueldo(newSueldo.toString());
        this.repartidorService.saveRepartidor(repartidores.get());

        // retrieving new name from database
        repartidores = this.repartidorService.findRepartidorById(1);
        assertThat(repartidores.get().getSueldo().equals(newSueldo));
    }


	
}
