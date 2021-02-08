package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.samples.petclinic.model.LineaPedido;
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
	
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_LINEAPEDIDO_ID = 1;
	private static final int TEST_PRODUCTO_ID = 1;
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PedidoService pedidoService;
	
	@MockBean
	private ProductoService productoService;

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
	private LineaPedido lineaPedido;
	private Producto producto;
	
	@BeforeEach
	void setup() {
		
		pedido = new Pedido();
		pedido.setId(TEST_PEDIDO_ID);
		pedido.setComentario("Muy bueno con arro blanco");
		pedido.setFecha(LocalDateTime.of(2020, 11, 30, 20, 30));
		pedido.setValoracion(4);
		pedido.setMetodopago(metodoPago.efectivo);
		pedido.setEstadopedido(estadoPedido.pendiente);
		pedido.setTipopedido(tipoPedido.enLocal);
		pedido.setHoraEstimada(LocalTime.of(12, 25));
		
		lineaPedido = new LineaPedido();
		lineaPedido.setId(TEST_LINEAPEDIDO_ID);
		lineaPedido.setCantidad(1);
		
		producto = new Producto();
		producto.setId(TEST_PRODUCTO_ID);
		producto.setPrecio(10.0);
		producto.setName("Pizza");
		producto.setDescripcion("Buena");
		
		lineaPedido.setProducto(producto);
		Set<LineaPedido> conjuntoLineaPedido = new HashSet<>();
		conjuntoLineaPedido.add(lineaPedido);
		
		pedido.setLineaPedidos(conjuntoLineaPedido);
	
//		Integer tamLineaPedido = conjuntoLineaPedido.size();
		Optional<Pedido> pedOp = Optional.of(pedido);     
		Optional<Producto> prodOp = Optional.of(producto);   
		Optional<LineaPedido> lineaPedOp = Optional.of(lineaPedido);
		List<Integer> listaIDLineaPedido = new ArrayList<>(lineaPedido.getId());
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		//given(this.prodService.findAll()).willReturn(Lists.newArrayList(prod, new Producto()));
		given(pedidoService.resumenLineasPedido(TEST_PEDIDO_ID)).willReturn(listaIDLineaPedido);
		given(productoService.findProductoById(TEST_PRODUCTO_ID)).willReturn(prodOp);
		given(pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedOp);	
		given(lineaPedidoService.findLineaPedidoById(TEST_LINEAPEDIDO_ID)).willReturn(lineaPedOp);
//		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID).get().getLineaPedidos().size()).willReturn(tamLineaPedido);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarPedido() throws Exception {
		mockMvc.perform(get("/pedidos/delete/{pedidoID}", TEST_PEDIDO_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/pedidos"));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoPedidos() throws Exception {
		
		mockMvc.perform(get("/pedidos")).andExpect(status().isOk())
		.andExpect(model().attributeExists("pedidosEnLocal"))
		.andExpect(view().name("pedidos/listadoPedidos"));
	//	.andExpect(model().attributeExists("pedidosADomicilio"))
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBotonCrearPedido() throws Exception {
		mockMvc.perform(get("/pedidos/new")).andExpect(status().is3xxRedirection())
		.andExpect(view().name("pedidos/new/1"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testVistaSeleccionProductos() throws Exception {
		mockMvc.perform(get("/pedidos/new/{pedidoID}", TEST_PEDIDO_ID)).andExpect(status().isOk())
		.andExpect(view().name("pedidos/seleccionarProductos"))
		.andExpect(model().attributeExists("pedidos"));
	} 
	
	@WithMockUser(value = "spring")
	@Test
	void testCompruebaExistenciaProductosSeleccionados() throws Exception{
		mockMvc.perform(post("/pedidos/new/{pedidoID}", TEST_PEDIDO_ID)				
		.with(csrf()))
		.andExpect(status().is3xxRedirection())
		//.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("redirect:/pedidos/new/resumendelpedido/1"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCrearLineaPedido() throws Exception{
		mockMvc.perform(get("/pedidos/new/{pedidoID}/{productoID}", TEST_PEDIDO_ID, TEST_PRODUCTO_ID)).andExpect(status().isOk())
		.andExpect(view().name("pedidos/seleccionarCantidadDeProductoSeleccionado"))
		.andExpect(model().attributeExists("productos"))
		.andExpect(model().attributeExists("lineapedidos"));	
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcesarCrearLineaPedido() throws Exception{
		mockMvc.perform(post("/pedidos/new/{pedidoID}/{productoID}", TEST_PEDIDO_ID, TEST_PRODUCTO_ID)
				.with(csrf())
				.param("cantidad", "1"))				
		.andExpect(status().is3xxRedirection())
		//.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("redirect:/pedidos/new/1"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testResumenDelPedido() throws Exception{
		mockMvc.perform(get("/pedidos/new/resumendelpedido/{pedidoID}", TEST_PEDIDO_ID)).andExpect(status().isOk())
		.andExpect(view().name("pedidos/resumenDelPedido"))
		.andExpect(model().attributeExists("pedido"))
		.andExpect(model().attributeExists("lineapedido"))	
		.andExpect(model().attributeExists("precioTotal"));	
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcesarResumenDelPedido() throws Exception{
		mockMvc.perform(post("/pedidos/new/resumendelpedido/{pedidoID}", TEST_PEDIDO_ID)
				.with(csrf()))				
		.andExpect(status().is3xxRedirection())
		//.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("redirect:/pedidos/new/finalizarpedido/1"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarLineaPedido() throws Exception{
		mockMvc.perform(get("/pedidos/new/resumendelpedido/delete/{pedidoID}/{lineapedidoID}", TEST_PEDIDO_ID, TEST_LINEAPEDIDO_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/pedidos/new/1"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditarLineaPedido() throws Exception{
		mockMvc.perform(get("/pedidos/new/resumendelpedido/edit/{pedidoID}/{lineapedidoID}/{productoID}", TEST_PEDIDO_ID, TEST_LINEAPEDIDO_ID, TEST_PRODUCTO_ID)).andExpect(status().isOk())
		.andExpect(view().name("pedidos/formularioModificarCantidad"))
		.andExpect(model().attributeExists("productos"))
		.andExpect(model().attributeExists("lineapedidos"));		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcesarEditarLineaPedido() throws Exception{
		mockMvc.perform(post("/pedidos/new/resumendelpedido/edit/{pedidoID}/{lineapedidoID}/{productoID}", TEST_PEDIDO_ID, TEST_LINEAPEDIDO_ID, TEST_PRODUCTO_ID)
				.with(csrf())
				.param("cantidad", "2"))				
		.andExpect(status().is3xxRedirection())
		//.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("redirect:/pedidos/new/resumendelpedido/1"));
	}
	
	

}
