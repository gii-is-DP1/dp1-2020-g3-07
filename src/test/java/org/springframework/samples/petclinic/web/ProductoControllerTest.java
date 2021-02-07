package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.AlergenoService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers=ProductoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ProductoControllerTest {
	
	
	private static final int TEST_PRODUCT_ID = 7;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductoService prodService;
	
	@MockBean
	private AlergenoService alerService;
	
	@MockBean
	private AuthoritiesService authoritiesService; 
	
	@MockBean
	private UserService userService;
	
	
	
	private Pedido ped;
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
		prod.setId(TEST_PRODUCT_ID);
		prod.setName("Pollo Frito");
		prod.setDescripcion("Excelente POLLO FRITO");
		prod.setPrecio(12.5);
		prod.setAlergenos(listAler);
		Optional<Producto> prodOp = Optional.of(prod);
		
		

		// Todos los metodos de los servicios que se usaran estan a continuacion
		//given(this.prodService.findAll()).willReturn(Lists.newArrayList(prod, new Producto()));
		given(this.prodService.findProductoById(TEST_PRODUCT_ID)).willReturn(prodOp);
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
	

  	

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/productos/new").param("name", "Pollo Asado")
							.with(csrf())
							.param("precio", "14.5"))
				.andExpect(status().is3xxRedirection());
	}
 
	
	@WithMockUser(value = "spring")
    @Test
void testProcessCreationFormHasErrors() throws Exception {
	mockMvc.perform(post("/productos/new")
						.with(csrf())
						.param("name", "")
						.param("precio", "12.5"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("producto"))
			.andExpect(model().attributeHasFieldErrors("producto", "name"))
			.andExpect(view().name("productos/createOrUpdateProductoForm"));
}	
	
	
	
	
	@WithMockUser(value = "spring")
	@Test
	void testDeleteProducto() throws Exception {
		
		mockMvc.perform(get("/productos/delete/{productoID}", TEST_PRODUCT_ID)).andExpect(status().isFound())
		.andExpect(view().name("redirect:/productos"));
		
	}



    @WithMockUser(value = "spring")
    @Test
    void testInitUpdateProductForm() throws Exception {
    	mockMvc.perform(get("/productos/save/{productoID}", TEST_PRODUCT_ID)).andExpect(status().isOk())
    		.andExpect(view().name("productos/createOrUpdateProductoForm"))
			.andExpect(model().attributeExists("producto"))
			.andExpect(model().attribute("producto", hasProperty("name", is("Pollo Frito"))))
			.andExpect(model().attribute("producto", hasProperty("descripcion", is("Excelente POLLO FRITO"))))
			.andExpect(model().attribute("producto", hasProperty("precio", is(12.5))));
			//.andExpect(model().attribute("producto", hasProperty("alergenos", is(listAler))));
			
}



	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		
		mockMvc.perform(post("/productos/save/{productoID}", TEST_PRODUCT_ID)
					.with(csrf())
					.param("name", "Pizza española")
					.param("precio", "14.1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/productos"));
		
	}
	

	
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormFailure() throws Exception {
		
		mockMvc.perform(post("/productos/save/{productoID}", TEST_PRODUCT_ID)
					.with(csrf())
					.param("name", "")
					.param("precio", "10.2"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("producto", "name"))
		.andExpect(view().name("productos/createOrUpdateProductoForm"));
		
	}
	
	
	
	
	
	
	
	


	}