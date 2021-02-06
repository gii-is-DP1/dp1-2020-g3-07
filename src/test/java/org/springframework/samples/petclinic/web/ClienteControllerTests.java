package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ClienteController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)


public class ClienteControllerTests {
	
	
	
	private static final int TEST_CLIENTE_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clientService;
	
	@MockBean
	private AuthoritiesService authoritiesService; 
	
	@MockBean
	private PedidoService pedidoService;
	
	
	private Cliente clien;

	
	

	@BeforeEach
	void setup() {
		
		clien = new Cliente();
		clien.setId(TEST_CLIENTE_ID);
		clien.setNombre("Juan");
		clien.setApellidos("Lopez Castro");
		clien.setTelefono(654652652);
		clien.setFechanacimiento(LocalDate.of(2000, 1, 1));
		clien.setDireccion("C/Diamela 5, Sevilla");
		
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.clientService.findAll()).willReturn(Lists.newArrayList(clien, new Cliente()));
		
		
	}


	@WithMockUser(value = "spring")
	@Test
	void testListadoClientes() throws Exception {
		
		mockMvc.perform(get("/clientes")).andExpect(status().isOk())
		.andExpect(view().name("clientes/listadoClientes"))
		.andExpect(model().attributeExists("clientes"));
		
	}

}
