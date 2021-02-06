package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
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



@WebMvcTest(controllers=ProductoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ProductoControllerTest {
	
	
	private static final int TEST_PRODUCT_ID = 1;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductoService prodService;
	
	@MockBean
	private AlergenoService alerService;
	
	
	private Producto prod;
	private Set<Alergeno> listAler;	
	private Alergeno aler;

	@BeforeEach
	void setup() {
		
		Set<Alergeno> listAler= new HashSet<Alergeno>();	
		aler = new Alergeno();
		aler.setAlergenotype(AlergenoEnum.Altramuces);
		listAler.add(aler);
		prod = new Producto();
		prod.setName("Pollo Frito");
		prod.setDescripcion("Excelente POLLO FRITO");
		prod.setPrecio(12);
		prod.setAlergenos(listAler);
		prod.setId(TEST_PRODUCT_ID);
		
		
		
		// Todos los metodos de los servicios que se usaran estan a continuacion
		given(this.prodService.findAll()).willReturn(Lists.newArrayList(prod, new Producto()));
		
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoProductos() throws Exception {
		
		mockMvc.perform(get("/productos")).andExpect(status().isOk())
		.andExpect(view().name("productos/listadoProductos"))
		.andExpect(model().attributeExists("productos"));
		
	}
	
	
      @WithMockUser(value = "spring")
	          @Test
	  	void testInitCreationForm() throws Exception {
	  		mockMvc.perform(get("/productos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("producto"))
	  				.andExpect(view().name("productos/createOrUpdateProductoForm"));
	  	}
	
	
//  	@WithMockUser(value = "spring")
//    @Test
//void testProcessCreationFormHasErrors() throws Exception {
//	mockMvc.perform(post("/productos/new")
//						.with(csrf())
//						.param("nombre", "Pollo Frito")
//						.param("lastName", "Bloggs")
//						.param("city", "London"))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("owner"))
//			.andExpect(model().attributeHasFieldErrors("owner", "address"))
//			.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
//			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
//}	
//	
  	
  	

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/productos/new").param("name", "Pollo Asado")
							.with(csrf())
							.param("precio", "12"))
				.andExpect(status().is3xxRedirection());
	}
    
	
	@WithMockUser(value = "spring")
	@Test
	void testDeleteProducto() throws Exception {
		
		mockMvc.perform(get("/productos/delete/{productoID}", TEST_PRODUCT_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/productos"));
		
	}









	}