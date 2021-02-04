package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.samples.petclinic.service.DependienteService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers=EmpleadoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class EmpleadoControllerTests {
	
	private static final int TEST_DEP_ID = 1;
	private static final int TEST_REP_ID = 1;
	private static final int TEST_COC_ID = 1;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DependienteService depeService;
	
	@MockBean
	private RepartidorService repaService;
	
	@MockBean
	private CocineroService cociService;
	
	private Dependiente dep;
	private Repartidor rep;
	private Cocinero coc;
	
	@BeforeEach
	void setup() {
		
		dep = new Dependiente();
		dep.setId(TEST_DEP_ID);
		dep.setNombre("Francisco");
		dep.setSueldo("1200");
		dep.setDni("12345678Q");
		dep.setFechanacimiento(LocalDate.of(1990, 1, 1));
		dep.setCuentabancaria("9741 1406 33 2598326977");
		Optional<Dependiente> depOp = Optional.of(dep);
		
		rep = new Repartidor();
		rep.setId(TEST_REP_ID);
		rep.setNombre("Tomas");
		rep.setSueldo("2300");
		rep.setDni("12345678W");
		rep.setFechanacimiento(LocalDate.of(1991, 2, 2));
		rep.setCuentabancaria("8741 1406 33 2598326978");
		Optional<Repartidor> repOp = Optional.of(rep);
		
		coc = new Cocinero();
		coc.setId(TEST_REP_ID);
		coc.setNombre("Tomas");
		coc.setSueldo("2300");
		coc.setDni("12345678W");
		coc.setFechanacimiento(LocalDate.of(1991, 2, 2));
		coc.setCuentabancaria("8741 1406 33 2598326978");
		Optional<Cocinero> cocOp = Optional.of(coc);
		
		given(this.depeService.findDependienteById(TEST_DEP_ID)).willReturn(depOp);
		given(this.repaService.findRepartidorById(TEST_REP_ID)).willReturn(repOp);
		given(this.cociService.findCocineroById(TEST_COC_ID)).willReturn(cocOp);
		
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoEmpleados() throws Exception {
		
		given(this.depeService.findAll()).willReturn(Lists.newArrayList(dep, new Dependiente()));
		given(this.repaService.findAll()).willReturn(Lists.newArrayList(rep, new Repartidor()));
		given(this.cociService.findAll()).willReturn(Lists.newArrayList(coc, new Cocinero()));
		
		mockMvc.perform(get("/empleados")).andExpect(status().isOk())
		.andExpect(view().name("empleados/listadoEmpleados"))
		.andExpect(model().attributeExists("dependientes"))
		.andExpect(model().attributeExists("repartidores"))
		.andExpect(model().attributeExists("cocineros"));
		
	}
	
}
