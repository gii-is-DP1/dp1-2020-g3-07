package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.model.AlergenoEnum;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.AlergenoService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaPedidoService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers=PedidoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)




public class PedidosControllerTest {
	
	private static final int TEST_PEDIDO_ID = 10;
	private static final int TEST_PRODUCT_ID = 7;
	

	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean
//	private ProductoService prodService;
//	
	@MockBean
	private PedidoService pedidoService;
	
	@MockBean
	private ProductoService productoService;
	
//	@MockBean
//	private AlergenoService alerService;
//	
	@MockBean
	private AuthoritiesService authoritiesService; 
	
	@MockBean
	private LineaPedidoService lineaPedidoService; 
	
	@MockBean
	private ClienteService clienteService; 
	
	@MockBean
	private RepartidorService repartidorService; 
	
	@MockBean
	private RepartoService repartoService; 
	
	@MockBean
	private UserService userService;
	
	
	private Pedido pedido;
	
	@BeforeEach
	void setup() {
		
		Pedido pedido = new Pedido();
		pedido.setComentario("Muy bueno con arro blanco");
		pedido.setFecha(LocalDateTime.of(2020, 11, 30, 20, 30));
		pedido.setValoracion(4);
		pedido.setMetodopago(metodoPago.efectivo);
		pedido.setEstadopedido(estadoPedido.pendiente);
		pedido.setTipopedido(tipoPedido.enLocal);
		Optional<Pedido> pedOp = Optional.of(pedido);     
		
		
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		//given(this.prodService.findAll()).willReturn(Lists.newArrayList(prod, new Producto()));
		//given(this.prodService.findProductoById(TEST_PRODUCT_ID)).willReturn(prodOp);
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedOp);
		
		
		
	}
	
	
	
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoPedidos() throws Exception {
		
		mockMvc.perform(get("/pedidos")).andExpect(status().isOk())
		.andExpect(model().attributeExists("pedidosEnLocal"))
		.andExpect(view().name("pedidos/listadoPedidos"));
	//	.andExpect(model().attributeExists("pedidosADomicilio"))
	
		
	}

}
