package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.model.AlergenoEnum;
import org.springframework.samples.petclinic.service.AlergenoService;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(controllers=AlergenoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AlergenoControllerTests {

	private static final int TEST_ALE_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AlergenoService alergenoService;
	

	private Alergeno ale;
	
	@BeforeEach
	void setup() {
		
		ale = new Alergeno();
		ale.setId(TEST_ALE_ID);
		ale.setAlergenotype(AlergenoEnum.Altramuces);
		
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testlistadoAlergenos() throws Exception {
		
		mockMvc.perform(get("/alergenos")).andExpect(status().isOk())
		.andExpect(view().name("alergenos/listadoAlergenos"));
		
	}
	
	
}
