package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pedido.Estadopedido;
import org.springframework.samples.petclinic.model.Pedido.Metodopago;
import org.springframework.samples.petclinic.model.Pedido.tipoPedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PedidoServiceTest {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Test
	public void testCountWithInititalData() {
		int count = pedidoService.pedidoCount();
		assertEquals(count,1);
	}

	
	@Test
	@Transactional
	public void shouldInsertPedido() {
		Collection<Pedido> pedidos = (Collection) this.pedidoService.findAll();
		int found = pedidos.size();

		Pedido pedido = new Pedido();
		pedido.setComentario("Muy bueno con arro blanco");
		pedido.setFecha(LocalDateTime.of(2020, 11, 30, 20, 30));
		pedido.setValoracion(4);
		pedido.setMetodopago(Metodopago.efectivo);
		pedido.setEstadopedido(Estadopedido.enReparto);
		pedido.setTipopedido(tipoPedido.enLocal);
                            
                
		this.pedidoService.savePedido(pedido);
		assertThat(pedido.getId().longValue()).isNotEqualTo(0);

		pedidos = (Collection) this.pedidoService.findAll();
		assertThat(pedidos.size()).isEqualTo(found + 1);
	}
	

	
	@Test
	@Transactional
	public void shouldUpdatePedido() {
		Optional<Pedido> pedidos = this.pedidoService.findPedidoById(1);
		
		Integer oldValoracion = pedidos.get().getValoracion();
		Integer newValoracion = oldValoracion+1;
		
		pedidos.get().setValoracion(newValoracion);;
		this.pedidoService.savePedido(pedidos.get());

		// retrieving new name from database
		pedidos = this.pedidoService.findPedidoById(1);
		assertThat(pedidos.get().getValoracion().equals(newValoracion));
	}


}
