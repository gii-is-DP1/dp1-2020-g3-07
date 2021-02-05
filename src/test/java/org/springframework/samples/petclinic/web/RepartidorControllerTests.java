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
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=RepartidorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class RepartidorControllerTests {

	private static final int TEST_REP_ID = 1;
	
	private static final int TEST_REPARTO_ID = 1;
	
	private static final int TEST_VEH_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RepartidorService repaService;
	
	@MockBean
	private PedidoService pediService;
	
	@MockBean
	private RepartoService repartoService;
	
	@MockBean
	private VehiculoService vehiculoService;
	
	private Repartidor rep;
	
	private Reparto reparto;
	
	private Vehiculo veh;
	
	@BeforeEach
	void setup() {
		
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
		reparto.setFecha(LocalDate.of(1998, 1, 1));
		reparto.setHoraInicio(LocalTime.of(12, 0));
		
		veh = new Vehiculo();
		veh.setId(TEST_VEH_ID);
		veh.setMatricula("0000ZZZ");
		veh.setTipovehiculo(TipoVehiculo.Coche);
		rep.setVehiculo(veh);
		Optional<Vehiculo> vehOp = Optional.of(veh);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.repaService.findRepartidorById(TEST_REP_ID)).willReturn(repOp);
		given(this.repaService.findRepartidores()).willReturn(Lists.newArrayList(rep, new Repartidor()));
		given(this.repaService.findAll()).willReturn(Lists.newArrayList(rep, new Repartidor()));
		
		given(this.repartoService.findByRepartidorId(TEST_REP_ID)).willReturn(Lists.newArrayList(reparto, new Reparto()));
		
		given(this.vehiculoService.findVehiculoById(TEST_VEH_ID)).willReturn(vehOp);
		given(this.vehiculoService.findVehiculo()).willReturn(Lists.newArrayList(veh, new Vehiculo()));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoRepartidores() throws Exception {

		mockMvc.perform(get("/repartidores")).andExpect(status().isOk())
		.andExpect(view().name("repartidores/listadoRepartidores"))
		.andExpect(model().attributeExists("repartidores"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowRepartidor() throws Exception {
		
		mockMvc.perform(get("/repartidores/{repartidorId}", TEST_REP_ID)).andExpect(status().isOk())
		.andExpect(view().name("repartidores/repartidorRepartos"))
		.andExpect(model().attributeExists("repartidor"))
		.andExpect(model().attributeExists("repartos"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarRepartidor() throws Exception {
		
		mockMvc.perform(get("/repartidores/delete/{repartidorID}", TEST_REP_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/empleados"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/repartidores/new")).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateRepartidorForm"))
		.andExpect(model().attributeExists("repartidor"))
		.andExpect(model().attributeExists("vehiculos"));
		
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormSuccess() throws Exception {
//		
//		mockMvc.perform(post("/repartidores/new")
//					.with(csrf())
//					.param("nombre", "Ismael")
//					.param("dni", "23441333T")
//					.param("sueldo", "1234")
//					.param("fechanacimiento", "1998/01/01"))
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(view().name("redirect:/empleados"));
//		
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormFailure() throws Exception {
		
		mockMvc.perform(post("/repartidores/new")
					.with(csrf())
					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
//		.andExpect(model().attributeHasFieldErrors("repartidor", "nombre"))
		.andExpect(model().attributeHasFieldErrors("repartidor", "vehiculo"))
		.andExpect(view().name("empleados/createOrUpdateRepartidorForm"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		
		mockMvc.perform(get("/repartidores/edit/{repartidorID}", TEST_REP_ID)).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateRepartidorForm"))
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attributeExists("repartidor"));
		
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormSuccess() throws Exception {
//		
//		mockMvc.perform(post("/repartidores/edit/{repartidorID}", TEST_REP_ID)
//					.with(csrf())
//					.param("nombre", "Ismael")
//					.param("dni", "23441333T")
//					.param("sueldo", "1234")
//					.param("fechanacimiento", "1998/01/01")
////					.param("vehiculo", "Moto")
////					.requestAttr("vehiculo", new Vehiculo())
////					.param("vehiculo", (new Vehiculo()).toString())
//					)
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(view().name("redirect:/empleados"));
//		
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormFailure() throws Exception {
		
		mockMvc.perform(post("/repartidores/edit/{repartidorID}", TEST_REP_ID)
					.with(csrf())
					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
//		.andExpect(model().attributeHasFieldErrors("repartidor", "nombre"))
		.andExpect(model().attributeHasFieldErrors("repartidor", "vehiculo"))
		.andExpect(view().name("empleados/createOrUpdateRepartidorForm"));
		
	}
	
}
