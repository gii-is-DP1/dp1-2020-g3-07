package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
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
	
	private static final int TEST_PEDIDO_ID = 1;


	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clientService;
	
	@MockBean
	private AuthoritiesService authoritiesService; 
	
	@MockBean
	private PedidoService pedidoService;
	
	
	private Cliente clien;
	
	private User user;
	
	private Pedido pedido;

	
	

	@BeforeEach
	void setup() {
		
		user = new User();
		user.setUsername("juanlop@gmail.com");
		
		clien = new Cliente();
		clien.setId(TEST_CLIENTE_ID);
		clien.setNombre("Juan");
		clien.setApellidos("Lopez Castro");
		clien.setTelefono(654652652);
		clien.setFechanacimiento(LocalDate.of(2000, 1, 1));
		clien.setDireccion("C/Diamela 5, Sevilla");
		clien.setUser(user);
		Optional<Cliente> cliOp = Optional.of(clien);
		
		pedido = new Pedido();
		pedido.setId(TEST_PEDIDO_ID);
		pedido.setCliente(clien);
		pedido.setEstadopedido(estadoPedido.enReparto);
		pedido.setFecha(LocalDateTime.of(2021, 02, 7, 14, 30));
		pedido.setMetodopago(metodoPago.efectivo);
		pedido.setTipopedido(tipoPedido.aDomicilio);
		Optional<Pedido> pedOp = Optional.of(pedido);
		
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.clientService.findAll()).willReturn(Lists.newArrayList(clien, new Cliente()));
		given(this.clientService.findClienteByUsername("juanlop@gmail.com")).willReturn(clien);
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedOp);
		given(this.clientService.findClienteById(TEST_CLIENTE_ID)).willReturn(cliOp);
		
		
	}


	@WithMockUser(value = "spring")
	@Test
	void testListadoClientes() throws Exception {
		
		mockMvc.perform(get("/clientes")).andExpect(status().isOk())
		.andExpect(view().name("clientes/listadoClientes"))
		.andExpect(model().attributeExists("clientes"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testClienteProfile() throws Exception {
		
		mockMvc.perform(get("/clientes/perfil")).andExpect(status().isOk())
		.andExpect(view().name("clientes/perfilCliente"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attributeExists("pedidos"));
		
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitValoracion() throws Exception {
		
		mockMvc.perform(get("/clientes/valorar/{pedidoId}", TEST_PEDIDO_ID)).andExpect(status().isOk())
		.andExpect(view().name("clientes/valoracionPedido"))
		.andExpect(model().attributeExists("pedido"));
		
		
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessValoracion() throws Exception {
		mockMvc.perform(post("/clientes/valorar/{pedidoId}", TEST_PEDIDO_ID)
					.param("comentario", "Todo genial")
					.param("valoracion", "5"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/clientes/perfil"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/clientes/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
			.andExpect(view().name("clientes/altaClienteForm"));
	}

	@WithMockUser(value = "spring")
	    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/new").param("nombre", "Pepe").param("apellidos", "Lopez")
						.with(csrf())
						.param("telefono", "988399399")
						.param("direccion", "calle estacada del rosario"))
			.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/clientes/edit/{clienteId}", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("cliente"))
				.andExpect(model().attribute("cliente", hasProperty("apellidos", is("Lopez Castro"))))
				.andExpect(model().attribute("cliente", hasProperty("nombre", is("Juan"))))
				.andExpect(model().attribute("cliente", hasProperty("telefono", is(654652652))))
				.andExpect(model().attribute("cliente", hasProperty("direccion", is("C/Diamela 5, Sevilla"))))
				.andExpect(model().attribute("cliente", hasProperty("fechanacimiento", is(LocalDate.of(2000, 1, 1)))))
				.andExpect(view().name("clientes/createOrUpdateClienteForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateForm() throws Exception {
		mockMvc.perform(post("/clientes/edit/{clienteId}", TEST_CLIENTE_ID)
							.with(csrf())
							.param("apellidos", "Lopez Dominguez")
							.param("nombre", "Luis")
							.param("telefono", "666444999")
							.param("direccion", "calle avenida del pantano"))
				.andExpect(status().isOk());
	}


}
