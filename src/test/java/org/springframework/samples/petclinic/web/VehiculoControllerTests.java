package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
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
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(controllers=VehiculoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class VehiculoControllerTests {

	private static final int TEST_VEH_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehiculoService vehiculoService;
	
	private Vehiculo veh;
	
	@BeforeEach
	void setup() {
		
		veh = new Vehiculo();
		veh.setId(TEST_VEH_ID);
		veh.setMatricula("4567ABC");
		veh.setTipovehiculo(TipoVehiculo.Coche);
		Optional<Vehiculo> vehOp = Optional.of(veh);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.vehiculoService.findVehiculoById(TEST_VEH_ID)).willReturn(vehOp);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarVehiculo() throws Exception {
		
		mockMvc.perform(get("/vehiculos/delete/{vehiculoID}", TEST_VEH_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/vehiculos"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/vehiculos/new")).andExpect(status().isOk())
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"))
		.andExpect(model().attributeExists("vehiculo"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/vehiculos/new")
					.with(csrf())
					.param("matricula", "2356GUJ")
					.param("tipovehiculo", "Moto"))				
		.andExpect(status().is3xxRedirection())
		//.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("redirect:/vehiculos"));
		
	}
	
/*	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormFailure() throws Exception {
		
		mockMvc.perform(post("/vehiculos/new")
					.with(csrf())
//					.param("matricula", "2356GUJ")
					.param("tipoVehiculo", "Moto"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasErrors("vehiculo"))
		.andExpect(model().attributeHasFieldErrors("vehiculo", "matricula"))
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
		
	}*/
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		
		mockMvc.perform(get("/vehiculos/edit/{vehiculoID}", TEST_VEH_ID)).andExpect(status().isOk())
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"))
		.andExpect(model().attributeExists("vehiculo"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		
		mockMvc.perform(post("/vehiculos/edit/{vehiculoID}", TEST_VEH_ID)
					.with(csrf())
					.param("matricula", "2356GUJ")
					.param("tipovehiculo", "Moto"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos"));
		
	}
	
/*	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormFailure() throws Exception {
		
		mockMvc.perform(post("/vehiculos/edit/{vehiculoID}", TEST_VEH_ID)
					.with(csrf())
	//				.param("matricula", "2356GUJ")
					.param("tipovehiculo", "Moto"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("vehiculo", "matricula"))
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
		
	}*/
	
}