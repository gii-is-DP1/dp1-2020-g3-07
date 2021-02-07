package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Tamanopizza;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.metodoPago;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PedidoServiceTest {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private LineaPedidoService lineaPedidoService;
	@Autowired
	private ProductoService ProductoService;
	
	@Test
	public void testCountWithInititalData() {
		int count = pedidoService.pedidoCount();
		assertEquals(count, 10);
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
		pedido.setMetodopago(metodoPago.efectivo);
		pedido.setEstadopedido(estadoPedido.enReparto);
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

	@Test
	@Transactional
	public void testResumenLineasPedido() {
		Pedido pedido = new Pedido();
		pedido.setComentario("Muy bueno");
		pedido.setFecha(LocalDateTime.of(2020, 11, 30, 20, 30));
		pedido.setValoracion(4);
		pedido.setMetodopago(metodoPago.efectivo);
		pedido.setEstadopedido(estadoPedido.enReparto);
		pedido.setTipopedido(tipoPedido.enLocal);
              
		Producto producto = new Producto();
		producto.setDescripcion("Pizza");
		producto.setName("Pizza");
		producto.setPrecio(14.);
		producto.setTamanopizza(Tamanopizza.mediana);
		this.ProductoService.saveProducto(producto);	
	
        LineaPedido lineaPedido1 = new LineaPedido();
		lineaPedido1.setCantidad(2);
		lineaPedido1.setProducto(producto);
		this.lineaPedidoService.saveLineaPedido(lineaPedido1);
		Set<LineaPedido> setLineaPedido = new HashSet<>();
		setLineaPedido.add(lineaPedido1);
		
		pedido.setLineaPedidos(setLineaPedido);
		this.pedidoService.savePedido(pedido);
		List<Integer> listaId = this.pedidoService.resumenLineasPedido(pedido.getId());
		//assertEquals(listaId.get(0), 17);
		Set<LineaPedido> set1 = pedidoService.findPedidoById(pedido.getId()).get().getLineaPedidos();
		Set<LineaPedido> set2 = new HashSet<>();
		set2.add(this.lineaPedidoService.findLineaPedidoById(listaId.get(0)).get());
		assertEquals(set1, set2);
	} 
	
	@Test
	@Transactional
	public void testfindByEstadopedido(){
		Set<Pedido> estadoDelPedido = this.pedidoService.findByEstadopedido(estadoPedido.Entregado);
		List<Pedido> resultEstadoDelPedido = new ArrayList<>();
		Iterator<Pedido> it = estadoDelPedido.iterator();
		while(it.hasNext()) {
			resultEstadoDelPedido.add(it.next());
		}	
		Pedido pedido = resultEstadoDelPedido.get(0);
		Pedido pedidoComparar = this.pedidoService.findPedidoById(1).get();
		assertEquals(pedido, pedidoComparar);
	} 
	
	@Test
	@Transactional
	public void findBytTipopedido(){
		Set<Pedido> tipoDelPedido = this.pedidoService.findBytTipopedido(tipoPedido.aDomicilio);
		List<Pedido> resultTipoDelPedido = new ArrayList<>();
		Iterator<Pedido> it = tipoDelPedido.iterator();
		while(it.hasNext()) {
			resultTipoDelPedido.add(it.next());
		}	
		Pedido pedido = resultTipoDelPedido.get(0);
		Pedido pedidoComparar = this.pedidoService.findPedidoById(4).get();
		assertEquals(pedido, pedidoComparar);
	} 
	
	@Test
	@Transactional
	public void findByEstadopedidoAndTipopedido(){
		Set<Pedido> tipoyEstadoDelPedido = this.pedidoService.findByEstadopedidoAndTipopedido(estadoPedido.Entregado, tipoPedido.aDomicilio);
		List<Pedido> resultTipoyEstadoDelPedido = new ArrayList<>();
		Iterator<Pedido> it = tipoyEstadoDelPedido.iterator();
		while(it.hasNext()) {
			resultTipoyEstadoDelPedido.add(it.next());
		}	
		Pedido pedido = resultTipoyEstadoDelPedido.get(0);
		Pedido pedidoComparar = this.pedidoService.findPedidoById(4).get();
		assertEquals(pedido, pedidoComparar);
	} 
	
	@Test
	@Transactional
	public void findPedidos(){
		Collection<Pedido> listaPedido = this.pedidoService.findPedidos();
		Pedido pedido = listaPedido.iterator().next();
		
		Iterable<Pedido> listaPedidoComparar = this.pedidoService.findAll();
		Pedido pedidoComparar = listaPedidoComparar.iterator().next();
		
		assertEquals(pedido, pedidoComparar);
	} 
	
	@Test
	public void testCountWithInititalDataLP() {
		int count = lineaPedidoService.pedidoCount();
		assertEquals(count,16);
	}

	@Test
	@Transactional
	public void shouldInsertLineaPedido() {
		Collection<LineaPedido> lineaPedidoCollection = (Collection) this.lineaPedidoService.findAll();
		int found = lineaPedidoCollection.size();
		
		Producto producto = new Producto();
		producto.setDescripcion("Pizza");
		producto.setName("Pizza");
		producto.setPrecio(14.);
		producto.setTamanopizza(Tamanopizza.mediana);
		this.ProductoService.saveProducto(producto);

		LineaPedido lineaPedido = new LineaPedido();
		lineaPedido.setCantidad(2);
		lineaPedido.setProducto(producto);
		this.lineaPedidoService.saveLineaPedido(lineaPedido);
		
		lineaPedidoCollection = (Collection) this.lineaPedidoService.findAll();
		assertEquals(lineaPedidoCollection.size(), found+1);
	}
	
	@Test
	@Transactional
	public void shouldDeleteLineaPedido() {
		Collection<LineaPedido> lineaPedidoCollection = (Collection) this.lineaPedidoService.findAll();
		int found = lineaPedidoCollection.size();
		
		Producto producto = new Producto();
		producto.setDescripcion("Pizza");
		producto.setName("Pizza");
		producto.setPrecio(14.);
		producto.setTamanopizza(Tamanopizza.mediana);
		this.ProductoService.saveProducto(producto);

		LineaPedido lineaPedido = new LineaPedido();
		lineaPedido.setCantidad(2);
		lineaPedido.setProducto(producto);
		this.lineaPedidoService.saveLineaPedido(lineaPedido);
		
		lineaPedidoCollection = (Collection) this.lineaPedidoService.findAll();
		assertEquals(lineaPedidoCollection.size(), found+1);
		
		this.lineaPedidoService.deleteLineaPedido(lineaPedido);
		lineaPedidoCollection = (Collection) this.lineaPedidoService.findAll();
		assertEquals(lineaPedidoCollection.size(), found);	
	}

}
