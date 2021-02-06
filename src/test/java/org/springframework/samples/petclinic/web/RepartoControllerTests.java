package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaPedidoService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=RepartoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class RepartoControllerTests {

	private static final int TEST_PEDIDO_ID = 1;
	
	private static final int TEST_REP_ID = 1;
	
	private static final int TEST_REPARTO_ID = 1;
	
	private static final int TEST_LP_ID = 1;
	
	private static final int TEST_CLIENTE_ID = 1;
	
	private static final int TEST_PROD_ID = 1;
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RepartidorService repaService;
	
	@MockBean
	private PedidoService pediService;
	
	@MockBean
	private RepartoService repartoService;
	
	@MockBean
	private LineaPedidoService lineaPedService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ProductoService prodService;
	
	@MockBean
	private VehiculoService veService;
	
	
	private Pedido ped;
	
	private Repartidor rep;
	
	private Reparto reparto;
	
	private LineaPedido lineaPedido;
	
	private Cliente cliente;
	
	private Producto producto;
	
	
	
	@BeforeEach
	void setup() {
		
		ped = new Pedido();
		ped.setId(TEST_PEDIDO_ID);
		ped.setFecha(LocalDateTime.of(2021, 2, 6, 13, 30));
		ped.setHoraEstimada(LocalTime.of(14, 30));
		ped.setMetodopago(metodoPago.efectivo);
		ped.setTipopedido(tipoPedido.aDomicilio);
		ped.setEstadopedido(estadoPedido.enReparto);
		ped.setReparto(reparto);
		Optional<Pedido> pedOp = Optional.of(ped);
		
		rep = new Repartidor();
		rep.setId(TEST_REP_ID);
		rep.setNombre("Tomas");
		rep.setSueldo("2300");
		rep.setDni("12345678W");
		rep.setFechanacimiento(LocalDate.of(1991, 2, 2));
		rep.setCuentabancaria("8741 1406 33 2598326978");
		Optional<Repartidor> repOp = Optional.of(rep);
		
		reparto = new Reparto();
		reparto.setId(TEST_REPARTO_ID);
		reparto.setFecha(LocalDate.of(2021, 1, 1));
		reparto.setHoraInicio(LocalTime.of(12, 0));
		reparto.setRepartidor(rep);
		Optional<Reparto> repartoOp = Optional.of(reparto);
		
		producto = new Producto();
		producto.setId(TEST_PROD_ID);
		producto.setName("pizza peperoni");
		producto.setPrecio(10);
		producto.setDescripcion("Pizza muy famosa, con base de queso y tomate.");
		
		lineaPedido = new LineaPedido();
		lineaPedido.setId(TEST_LP_ID);
		lineaPedido.setCantidad(2);
		lineaPedido.setProducto(producto);
		
		Optional<LineaPedido> lpOp = Optional.of(lineaPedido);

		cliente = new Cliente();
		cliente.setId(TEST_CLIENTE_ID);
		Optional<Cliente> clienteOp = Optional.of(cliente);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.pediService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedOp);
		given(this.repartoService.findRepartoById(TEST_REPARTO_ID)).willReturn(repartoOp);
		given(this.repaService.findRepartidorById(TEST_REP_ID)).willReturn(repOp);
		given(this.lineaPedService.findLineaPedidoById(TEST_LP_ID)).willReturn(lpOp);
		given(this.clienteService.findClienteById(TEST_CLIENTE_ID)).willReturn(clienteOp);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCambioaEntregado() throws Exception {

		mockMvc.perform(get("/repartidores/{repartidorId}/reparto/{pedidoId}/entregado", TEST_REP_ID, TEST_PEDIDO_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("redirect:/repartidores/" + TEST_REP_ID + "/repartos/" + TEST_PEDIDO_ID));
//		.andExpect(model().attributeExists("repartidores"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowDetallesPedido() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/detallesPedido/{pedidoId}", TEST_REP_ID, TEST_REPARTO_ID, TEST_PEDIDO_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("repartos/infoPedidoReparto"))
		.andExpect(model().attributeExists("pedido"))
		.andExpect(model().attributeExists("reparto"))
		.andExpect(model().attributeExists("lineapedido"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowInfoCliente() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/cliente/{clienteId}", TEST_REP_ID, TEST_REPARTO_ID, TEST_CLIENTE_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/infoCliente"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attributeExists("reparto"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBackToReparto() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/volver", TEST_REP_ID, TEST_REPARTO_ID))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/repartidores/" + TEST_REP_ID + "/repartos/" + TEST_REPARTO_ID));
		
	}
}
