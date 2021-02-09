package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaPedidoService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=RepartoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class RepartoControllerTests {
	
	
	private static final int TEST_REP_ID = 1;
	
	private static final int TEST_REPARTO_ID = 1;
	
	private static final int TEST_PED_ID = 1;
	
	private static final int TEST_LINPED_ID = 1;
	
	private static final int TEST_CLI_ID = 1;
	
	private static final int TEST_AU_ID = 1;
	
	@Autowired
	private RepartoController repartoController;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RepartoService repartoService;
	
	@MockBean
    private RepartidorService repartidorService;
	
	@MockBean
    private PedidoService pedidoService;
	
	@MockBean
    private VehiculoService vehiculoService;
	
	@MockBean
    private LineaPedidoService lineaPedidoService;
	
	@MockBean
    private ClienteService clienteService;
	
	private Repartidor repartidor;
	
	private Reparto reparto;
	
	private Pedido pedido;
	
	private LineaPedido linPed;
	
	private Cliente cliente;
	
	private User user;
	
	private Authorities au;
	
	private ConjuntoPedidos cp;
	
	private List<Pedido> pedidosList;
	
	@BeforeEach
	void setup() {
		
		user = new User();
		user.setEnabled(true);
		user.setUsername("curro");
		user.setPassword("curro");
		
		au = new Authorities();
		au.setId(TEST_AU_ID);
		au.setUser(user);
		au.setAuthority("admin");
		
		repartidor = new Repartidor();
		repartidor.setId(TEST_REP_ID);
		repartidor.setNombre("Tomas");
		repartidor.setSueldo("2300");
		repartidor.setDni("12345678W");
		repartidor.setFechanacimiento(LocalDate.of(1991, 2, 2));
		repartidor.setCuentabancaria("8741 1406 33 2598326978");
		repartidor.setUser(user);
		Optional<Repartidor> repOp = Optional.of(repartidor);
		
		reparto = new Reparto();
		reparto.setId(TEST_REPARTO_ID);
		reparto.setFecha(LocalDate.of(1998, 1, 1));
		reparto.setHoraInicio(LocalTime.of(12, 0));
		Optional<Reparto> repartoOp = Optional.of(reparto);
		
		cliente = new Cliente();
		cliente.setId(TEST_CLI_ID);
		cliente.setNombre("Javier");
		cliente.setApellidos("Gonzalez Machado");
		cliente.setDireccion("avda el Pantano 13");
		cliente.setTelefono(666666666);
		cliente.setFechanacimiento(LocalDate.of(1998, 1, 1));
		cliente.setUser(user);
		Optional<Cliente> cliOp = Optional.of(cliente);
		
		linPed = new LineaPedido();
		linPed.setId(TEST_LINPED_ID);
		linPed.setCantidad(5);
		Optional<LineaPedido> linPedOp = Optional.of(linPed);
		
		estadoPedido ep = estadoPedido.pendiente;
		tipoPedido tp = tipoPedido.aDomicilio;
		metodoPago mp = metodoPago.tarjeta;
		pedido = new Pedido();
		pedido.setId(TEST_PED_ID);
		pedido.setCliente(cliente);
		pedido.setComentario("Bueno");
		pedido.setEstadopedido(ep);
		pedido.setFecha(LocalDateTime.of(2020, 1, 1, 12, 0));
//		pedido.setHoraCliente(LocalTime.of(12, 0));
		pedido.setHoraEstimada(LocalTime.of(12, 30));
		pedido.setMetodopago(mp);
		pedido.setReparto(reparto);
		pedido.setTipopedido(tp);
		pedido.setValoracion(4);
		pedido.setLineaPedidos(Sets.newSet(linPed));
		Optional<Pedido> pedOp = Optional.of(pedido);
		
		cp = new ConjuntoPedidos();
		cp.setPedidosAsignados(Lists.newArrayList(pedido, new Pedido()));
		
		pedidosList = new ArrayList<Pedido>();
		pedidosList.add(pedido);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.repartidorService.findRepartidorById(TEST_REP_ID)).willReturn(repOp);
		
		given(this.repartoService.findRepartoById(TEST_REPARTO_ID)).willReturn(repartoOp);
		
		given(this.clienteService.findClienteById(TEST_CLI_ID)).willReturn(cliOp);
		
		given(this.lineaPedidoService.findLineaPedidoById(TEST_LINPED_ID)).willReturn(linPedOp);
		
//		given(this.pedidoService.findByEstadopedidoAndTipopedido(ep, tp)).willReturn(Sets.newSet(pedido, new Pedido()));
		given(this.pedidoService.findPedidoById(TEST_PED_ID)).willReturn(pedOp);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/new", TEST_REP_ID))//.andExpect(status().isOk())
		.andExpect(model().attributeExists("repartidor"))
		.andExpect(model().attributeExists("pedidosList"))
		.andExpect(model().attributeExists("command"))
		.andExpect(view().name("repartidores/listadoPedidosRepartidor"));
		
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testInitCreationFormSuccess() throws Exception {
//		
//		mockMvc.perform(post("/repartidores/{repartidorId}/repartos/new", TEST_REP_ID)
//					.with(csrf())
//					.param("pedidosAsignados", "1"))
//		.andExpect(status().is3xxRedirection())
//		.andExpect(view().name("redirect:/empleados"));
//		
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationFormFailure() throws Exception {
		
		mockMvc.perform(post("/repartidores/1/repartos/new")
					.with(csrf())
				.param("pedidos", "1")	
				)
//		.andExpect(status().is3xxRedirection());
//		.andExpect(model().attributeHasErrors("pedidosAsignados"))
//		.andExpect(view().name("repartidores/listadoPedidosRepartidor"));
//		.andExpect(model().attributeDoesNotExist("command"));
		.andExpect(status().isOk());
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowRepartos() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}", TEST_REP_ID, TEST_REPARTO_ID)).andExpect(status().isOk())
		.andExpect(view().name("repartos/infoReparto"))
		.andExpect(model().attributeExists("repartidor"))
		.andExpect(model().attributeExists("pedidosList"))
		.andExpect(model().attributeExists("reparto"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCambioaEntregado() throws Exception {

		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/{pedidoId}/entregado", TEST_REP_ID, TEST_REPARTO_ID, TEST_PED_ID))
//		.andExpect(status().isOk())
		.andExpect(view().name("redirect:/repartidores/" + TEST_REP_ID + "/repartos/" + TEST_REPARTO_ID));
//		.andExpect(model().attributeExists("repartidores"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowDetallesPedido() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/detallesPedido/{pedidoId}", TEST_REP_ID, TEST_REPARTO_ID, TEST_PED_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("repartos/infoPedidoReparto"))
		.andExpect(model().attributeExists("pedido"))
		.andExpect(model().attributeExists("reparto"))
		.andExpect(model().attributeExists("lineapedido"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowInfoCliente() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}/repartos/{repartoId}/{pedidoId}/cliente/{clienteId}", TEST_REP_ID, TEST_REPARTO_ID, TEST_REP_ID, TEST_CLI_ID))
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
