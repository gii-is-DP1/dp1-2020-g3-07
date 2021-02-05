package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.service.DependienteService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=DependienteController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class DependienteControllerTests {

	private static final int TEST_DEP_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DependienteService depeService;
	
	private Dependiente dep;
	
	@BeforeEach
	void setup() {
		
		dep = new Dependiente();
		dep.setId(TEST_DEP_ID);
		dep.setNombre("Tomas");
		dep.setSueldo("2300");
		dep.setDni("12345678W");
		dep.setFechanacimiento(LocalDate.of(1991, 2, 2));
		dep.setCuentabancaria("8741 1406 33 2598326978");
		Optional<Dependiente> depOp = Optional.of(dep);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.depeService.findDependienteById(TEST_DEP_ID)).willReturn(depOp);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarCocinero() throws Exception {
		
		mockMvc.perform(get("/dependientes/delete/{dependienteID}", TEST_DEP_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/empleados"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/dependientes/new")).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateDependienteForm"))
		.andExpect(model().attributeExists("dependiente"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/dependientes/new")
					.with(csrf())
					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/empleados"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormFailure() throws Exception {
		
		mockMvc.perform(post("/dependientes/new")
					.with(csrf())
//					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("dependiente", "nombre"))
		.andExpect(view().name("empleados/createOrUpdateDependienteForm"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		
		mockMvc.perform(get("/dependientes/edit/{dependienteID}", TEST_DEP_ID)).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateDependienteForm"))
		.andExpect(model().attributeExists("dependiente"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		
		mockMvc.perform(post("/dependientes/edit/{dependienteID}", TEST_DEP_ID)
					.with(csrf())
					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/empleados"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormFailure() throws Exception {
		
		mockMvc.perform(post("/dependientes/edit/{dependienteID}", TEST_DEP_ID)
					.with(csrf())
//					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("dependiente", "nombre"))
		.andExpect(view().name("empleados/createOrUpdateDependienteForm"));
		
	}
	
}
