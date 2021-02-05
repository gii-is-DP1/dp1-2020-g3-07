package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.service.CocineroService;
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

@WebMvcTest(controllers=CocineroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class CocineroControllerTests {

	private static final int TEST_COC_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CocineroService cociService;
	
	private Cocinero coc;
	
	@BeforeEach
	void setup() {
		
		coc = new Cocinero();
		coc.setId(TEST_COC_ID);
		coc.setNombre("Tomas");
		coc.setSueldo("2300");
		coc.setDni("12345678W");
		coc.setFechanacimiento(LocalDate.of(1991, 2, 2));
		coc.setCuentabancaria("8741 1406 33 2598326978");
		Optional<Cocinero> cocOp = Optional.of(coc);
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.cociService.findCocineroById(TEST_COC_ID)).willReturn(cocOp);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarCocinero() throws Exception {
		
		mockMvc.perform(get("/cocineros/delete/{cocineroID}", TEST_COC_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/empleados"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/cocineros/new")).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateCocineroForm"))
		.andExpect(model().attributeExists("cocinero"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/cocineros/new")
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
		
		mockMvc.perform(post("/cocineros/new")
					.with(csrf())
//					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("cocinero", "nombre"))
		.andExpect(view().name("empleados/createOrUpdateCocineroForm"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		
		mockMvc.perform(get("/cocineros/edit/{cocineroID}", TEST_COC_ID)).andExpect(status().isOk())
		.andExpect(view().name("empleados/createOrUpdateCocineroForm"))
		.andExpect(model().attributeExists("cocinero"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		
		mockMvc.perform(post("/cocineros/edit/{cocineroID}", TEST_COC_ID)
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
		
		mockMvc.perform(post("/cocineros/edit/{cocineroID}", TEST_COC_ID)
					.with(csrf())
//					.param("nombre", "Ismael")
					.param("dni", "23441333T")
					.param("sueldo", "1234")
					.param("fechanacimiento", "1998/01/01"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("cocinero", "nombre"))
		.andExpect(view().name("empleados/createOrUpdateCocineroForm"));
		
	}
	
}
