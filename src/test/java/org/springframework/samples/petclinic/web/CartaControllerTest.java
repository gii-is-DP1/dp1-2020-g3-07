package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Lists;
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
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.AlergenoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=CartaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)



public class CartaControllerTest {
	
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductoService prodService;
	
	@MockBean
	private AlergenoService alerService;
	
	
	private Producto prod;
	private List<Alergeno> listAler;	
	private Alergeno aler;

	@BeforeEach
	void setup() {
		
		List<Alergeno> listAler= new ArrayList<Alergeno>();	
		aler = new Alergeno();
		aler.setAlergenotype(AlergenoEnum.Altramuces);
		listAler.add(aler);
		prod = new Producto();
		prod.setName("Pollo Frito");
		prod.setDescripcion("Excelente POLLO FRITO");
		prod.setPrecio(12);
		prod.setAlergenos(listAler);
		
		
		
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.prodService.findAll()).willReturn(Lists.newArrayList(prod, new Producto()));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoCarta() throws Exception {
		
		mockMvc.perform(get("/carta")).andExpect(status().isOk())
		.andExpect(view().name("carta/listadoCarta"))
		.andExpect(model().attributeExists("productos"));
		
	}
	
	

}
