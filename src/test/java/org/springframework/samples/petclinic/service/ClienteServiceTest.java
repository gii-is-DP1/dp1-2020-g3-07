package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTest {

	@Autowired
	private ClienteService clientServ;
	
	@Test
	public void testCountWithInitialData() {
		int count = clientServ.clienteCount();
		assertEquals(count,1);
	}
	
	@Test
	@Transactional
	public void insertCliente() {
		Cliente cliente = new Cliente();
		cliente.setContrasena("1234");
		cliente.setDireccion("Los Naranjos");
		//cliente.setName("Manuel");
		cliente.setTelefono(123456789);
		cliente.setEmail("Mperez");
		
		this.clientServ.saveCliente(cliente);
		assertThat(cliente.getId().intValue()).isEqualTo(2);
		}
	
	@Test
    @Transactional
	public void editCliente() {
	        Optional<Cliente> cliente = this.clientServ.findClienteById(1);
	        String oldName = cliente.get().getNombre();
	        String newName = oldName + "X";

	        cliente.get().setNombre(newName);
	        this.clientServ.saveCliente(cliente.get());

	        // retrieving new name from database
	        cliente = this.clientServ.findClienteById(1);
	        assertThat(cliente.get().getNombre()).isEqualTo(newName);
	}
}

