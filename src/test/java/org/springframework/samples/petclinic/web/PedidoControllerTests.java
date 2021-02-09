package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@WebMvcTest(controllers=CocineroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class PedidoControllerTests {

	private static final int TEST_PED_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PedidoService pedService;
	
	private Pedido ped;
	
	@BeforeEach
	void setup() {
		
		ped = new Pedido();
		ped.setId(TEST_PED_ID);
		ped.setFecha(LocalDateTime.of(2000, 12, 29, 12, 25));
		ped.setHoraEstimada(LocalTime.of(12, 25));
		ped.setHoraCliente(LocalTime.of(12, 25));
		ped.setComentario("Muy bueno");
		ped.setValoracion(7);
		ped.setMetodopago(metodoPago.efectivo);
		ped.setEstadopedido(estadoPedido.enReparto);
		ped.setTipopedido(tipoPedido.aDomicilio);
		Optional<Pedido> pedOp = Optional.of(ped);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.pedService.findPedidoById(TEST_PED_ID)).willReturn(pedOp);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarPedido() throws Exception {
		
		mockMvc.perform(get("/pedidos/delete/{pedidoID}", TEST_PED_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/pedidos"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/pedidos/new")).andExpect(status().isOk())
		.andExpect(view().name("pedidos/createOrUpdatePedidoForm")) 
		.andExpect(model().attributeExists("pedido"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/pedidos/new")
					.with(csrf()) 
					.param("fecha", "2020/01/01")
					.param("metodoPago", "efectivo")
					.param("tipoPedido", "aDomicilio"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormFailure() throws Exception {
		
		mockMvc.perform(post("/pedidos/new")
					.with(csrf()) //****
	//				.param("fecha", "2020/01/01")
					.param("metodoPago", "efectivo")
					.param("tipoPedido", "aDomicilio"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("pedidos", "fecha"))
		.andExpect(view().name("pedidos/createOrUpdatePedidoForm"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		
		mockMvc.perform(get("/pedidos/edit/{pedidoID}", TEST_PED_ID)).andExpect(status().isOk())
		.andExpect(view().name("pedidos/createOrUpdatePedidoForm"))
		.andExpect(model().attributeExists("pedido"));
		
	}
	
}