package org.springframework.samples.petclinic.web;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.estadoPedido;
import org.springframework.samples.petclinic.model.tipoPedido;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaPedidoService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.RepartoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
		private static final String VIEWS_SELECCION_CANTIDAD_PRODUCTO = "pedidos/seleccionarCantidadDeProductoSeleccionado";
		//private static final String VIEWS_PEDIDO_CREATE_OR_UPDATE_FORM = "pedidos/createOrUpdatePedidoForm";
		private static final String VIEWS_SELECCION_PRODUCTOS = "pedidos/seleccionarProductos";
		private static final String VIEWS_RESUMEN_DEL_PEDIDO = "pedidos/resumenDelPedido";
		private static final String VIEWS_FINALIZAR_PEDIDO = "pedidos/formularioFinalPedido";
		
		private PedidoService pedidoService;
		private ProductoService productoService;
		private LineaPedidoService lineaPedidoService;
		private RepartoService repartoService;
		private RepartidorService repartidorService;
		private ClienteService clienteService;

		@Autowired
		public PedidoController(PedidoService pedidoService, ProductoService productoService, LineaPedidoService lineaPedidoService, RepartoService repartoService, 
				ClienteService clienteService, RepartidorService repartidorService) {
			this.pedidoService = pedidoService;
			this.productoService = productoService;
			this.lineaPedidoService = lineaPedidoService;
			this.repartoService = repartoService;
			this.repartidorService = repartidorService;
			this.clienteService = clienteService;
		}
		
	
		@GetMapping()
		public String listadoPedidos(ModelMap modelMap) {
			String vista = "pedidos/listadoPedidos";
//			Iterable<Pedido> pedidosaDom = pedidoService.findAll();
//			Iterator<Pedido> itaDom = pedidosaDom.iterator();
//			Iterable<Pedido> pedidosenLoc = pedidoService.findAll();
//			Iterator<Pedido> itenLoc = pedidosenLoc.iterator();			
//			while(itaDom.hasNext()) {
//				Pedido elemento = itaDom.next();
//				
//				if(elemento.getEstadopedido() == null|| elemento.getMetodopago() == null || elemento.getTipopedido() == null) {
//					itaDom.remove();
//				}
//				else if(elemento.getTipopedido() == tipoPedido.enLocal) {
//					itaDom.remove();
//				}
//			}
//			while(itenLoc.hasNext()) {
//				Pedido elemento = itenLoc.next();
//				if(elemento.getEstadopedido() == null|| elemento.getMetodopago() == null || elemento.getTipopedido() == null) {
//					itenLoc.remove();
//				}
//				else if(elemento.getTipopedido() == tipoPedido.aDomicilio) {
//					itenLoc.remove();
//				}
//			}
////			modelMap.addAttribute("pedidos", pedidos);
//			modelMap.addAttribute("pedidosadom", pedidosaDom);
//			modelMap.addAttribute("pedidosenloc", pedidosenLoc);
			
			return vista;
		}
		
		@ModelAttribute("pedidosADomicilio")
        public List<Pedido> getPedidosList(){
            tipoPedido tp = tipoPedido.aDomicilio;
            Set<Pedido> pedidosadom = pedidoService.findBytTipopedido(tp);
            List<Pedido> res = new ArrayList<Pedido>(pedidosadom);
            res.sort(Comparator.comparing(p->(p.getFecha())));
            Collections.reverse(res);
//            Collections.sort(res, Collections.reverseOrder(Comparator.comparing(p->(p.getFecha()))));
            return res;
            
        }
		
		@ModelAttribute("pedidosEnLocal")
        public List<Pedido> getPedidosLocalList(){
            tipoPedido tp = tipoPedido.enLocal;
            Set<Pedido> pedidosadom = pedidoService.findBytTipopedido(tp);
            List<Pedido> res = new ArrayList<Pedido>(pedidosadom);
            res.sort(Comparator.comparing(p->(p.getFecha())));
            Collections.reverse(res);
//            Collections.sort(res, Collections.reverseOrder(Comparator.comparing(p->(p.getFecha()))));
            return res;
            
        }
		
		@GetMapping(value = "detalles/{pedidoId}")
		public String showDetallesPedido(@PathVariable("pedidoId") int pedidoId, ModelMap modelMap) {
			Pedido pedido = pedidoService.findPedidoById(pedidoId).get();
			modelMap.addAttribute("pedido", pedido);
			Set<LineaPedido> Setlineapedido = pedido.getLineaPedidos();
			List<LineaPedido> res = new ArrayList<LineaPedido>();
			List<Integer> ids = new ArrayList<Integer>();
			ids = Setlineapedido.stream().map(lp -> lp.getId()).collect(Collectors.toList());
			for(int i=0; i<ids.size(); i++) {
				LineaPedido lineapedido = lineaPedidoService.findLineaPedidoById(ids.get(i)).get();
				res.add(lineapedido);
			}
//			List<Integer> listaIDLineaPedido = pedidoService.resumenLineasPedido(pedidoId);
//			List<LineaPedido> listaLineaPedidos = new ArrayList<>();
//			//List<Producto> listaProductos = new ArrayList<>();
//			int i = 0;
//			while(i<listaIDLineaPedido.size()) {
//				listaLineaPedidos.add(lineaPedidoService.findLineaPedidoById(listaIDLineaPedido.get(i)).get());
//				//listaProductos.add(lineaPedidoService.findPedidoById(listaIDLineaPedido.get(i)).get().getProducto());
//				i++;
//			}	
//			Reparto reparto = repartoService.findRepartoById(repartoId).get();
//			modelMap.addAttribute("reparto", reparto);
//			modelMap.addAttribute("pedido", pedido);
			modelMap.addAttribute("lineapedido", res);
			return "pedidos/detallesPedido";
		}
		
/*		@GetMapping(value = "/new")
		public String initCreationForm(Map<String, Object> model) {
			Pedido pedido = new Pedido();
			model.put("pedidos", pedido);
			return VIEWS_PEDIDO_CREATE_OR_UPDATE_FORM;
		}
		
		@PostMapping(value = "/new")
		public String processCreationForm(@Valid Pedido pedido, BindingResult result) {
			if (result.hasErrors()) {
				return VIEWS_PEDIDO_CREATE_OR_UPDATE_FORM;
			}
			else {
				//creating owner, user and authorities
				pedido.setFecha(LocalDateTime.now());
				this.pedidoService.savePedido(pedido);
				return "redirect:/pedidos";
			}
		} */
	
//Crear pedido
		@GetMapping(value = "/new")
		public String botonCrearPedido(Map<String, Object> model, @AuthenticationPrincipal User user) {
			Pedido pedido = new Pedido(); 
		    if(user.getUsername().equals("curro") || user.getUsername().equals("dependiente")) {
		    	Cliente cliente = this.clienteService.findClienteById(1).get();
		    	pedido.setCliente(cliente);		    	
		    } else {
		    	Cliente cliente = this.clienteService.findClienteByUsername(user.getUsername());
		    	pedido.setCliente(cliente);
		    }
			
		    //Cliente cliente = this.clienteService.findClienteByUsername("juan@gmail.com");
		    
			pedidoService.savePedido(pedido);
			Integer idPedido = pedido.getId();
			return "redirect:/pedidos/new/" + idPedido;
		}
		
//Seleccionar productos
		@GetMapping(value = "/new/{pedidoID}")
		public String vistaSeleccionProductos(@PathVariable("pedidoID") int pedidoID, Map<String, Object> model) {
			Pedido pedido = pedidoService.findPedidoById(pedidoID).get();
			model.put("pedidos", pedido);
			Iterable<Producto> productos = productoService.findAll();
			model.put("productos", productos);
			return VIEWS_SELECCION_PRODUCTOS;
		}
		@PostMapping(value="/new/{pedidoID}")
		public String compruebaExistenciaProductosSeleccionados(@PathVariable("pedidoID") int pedidoID) {
			if(pedidoService.findPedidoById(pedidoID).get().getLineaPedidos().size() == 0) {
				return "redirect:/pedidos/new/" + pedidoID;
				// esto envia a un jsp, puedes meter model return "pedidos/new/" + pedidoID;
			} else {
				return "redirect:/pedidos/new/resumendelpedido/" + pedidoID;
			}
		}

//Crear linea de pedido a partir de los productos seleccionados
		@GetMapping(value = "/new/{pedidoID}/{productoID}")
		public String crearLineaPedido(@PathVariable("productoID") int productoID, @PathVariable("pedidoID") int pedidoID, Map<String, Object> model) {
			Producto producto = productoService.findProductoById(productoID).get();		
			model.put("productos", producto);
			LineaPedido lineaPedido = new LineaPedido();
			model.put("lineapedidos", lineaPedido);
			return VIEWS_SELECCION_CANTIDAD_PRODUCTO;
		}
		@PostMapping(value="/new/{pedidoID}/{productoID}")
		public String procesarCrearLineaPedido(@PathVariable("productoID") int productoID, @PathVariable("pedidoID") int pedidoID, @Valid LineaPedido lineaPedido, 
				BindingResult result) {
				if (result.hasErrors()) {
					return VIEWS_SELECCION_CANTIDAD_PRODUCTO;
				} else {
					lineaPedido.setProducto(productoService.findProductoById(productoID).get());
					this.lineaPedidoService.saveLineaPedido(lineaPedido);
					Pedido pedido = pedidoService.findPedidoById(pedidoID).get();
					Set<LineaPedido> set = pedido.getLineaPedidos();
					set.add(lineaPedido);
					pedido.setLineaPedidos(set);
					this.pedidoService.savePedido(pedido);
					return "redirect:/pedidos/new/" + pedidoID;
				}
		}
		
//Resumen del pedido		
		@GetMapping(value = "/new/resumendelpedido/{pedidoID}")
		public String resumenDelPedido(Map<String, Object> model, @PathVariable("pedidoID") int pedidoID) {
			List<Integer> listaIDLineaPedido = pedidoService.resumenLineasPedido(pedidoID);
			List<LineaPedido> listaLineaPedidos = new ArrayList<>();
			//List<Producto> listaProductos = new ArrayList<>();
			int i = 0;
			while(i<listaIDLineaPedido.size()) {
				listaLineaPedidos.add(lineaPedidoService.findLineaPedidoById(listaIDLineaPedido.get(i)).get());
				//listaProductos.add(lineaPedidoService.findPedidoById(listaIDLineaPedido.get(i)).get().getProducto());
				i++;
			}	
			model.put("pedido", pedidoService.findPedidoById(pedidoID).get());
			model.put("lineapedido", listaLineaPedidos);
			//model.put("producto", listaProductos);
			return VIEWS_RESUMEN_DEL_PEDIDO;
		}
		@PostMapping(value = "/new/resumendelpedido/{pedidoID}")
		public String procesarResumenDelPedido(@PathVariable("pedidoID") int pedidoID, @Valid Pedido pedido, BindingResult result) {
				if (result.hasErrors()) {
					return VIEWS_SELECCION_CANTIDAD_PRODUCTO;
				} else {
					return "redirect:/pedidos/new/finalizarpedido/" + pedidoID;
				}
		}

//En resumen del pedido, borrar linea de pedido		
		@GetMapping(value="/new/resumendelpedido/delete/{pedidoID}/{lineapedidoID}")
		public String borrarLineaPedido(@PathVariable("lineapedidoID") int lineapedidoID, @PathVariable("pedidoID") int pedidoID, ModelMap modelMap) {
			Pedido pedido = pedidoService.findPedidoById(pedidoID).get();
			Optional<LineaPedido> lineapedido = lineaPedidoService.findLineaPedidoById(lineapedidoID);
			if(lineapedido.isPresent()) {
				Iterator<LineaPedido> it = pedido.getLineaPedidos().iterator();
				while(it.hasNext()) {
					if(it.next().getId() == lineapedido.get().getId()) {
						it.remove();
					}
				}
				lineaPedidoService.deleteLineaPedido(lineapedido.get());
				modelMap.addAttribute("message", "Pedido borrado correctamente");
			} else {
				modelMap.addAttribute("message", "Pedido no encontrado");
			}
			if(pedido.getLineaPedidos().size() == 0) {
				return "redirect:/pedidos/new/" + pedidoID;
			} else {
				return "redirect:/pedidos/new/resumendelpedido/" + pedidoID;				
			}
		} 
		
//En resumen del pedido, editar cantidad de un producto
		@GetMapping(value="/new/resumendelpedido/edit/{pedidoID}/{lineapedidoID}/{productoID}")
		public String editarLineaPedido(@PathVariable("lineapedidoID") int lineapedidoID, @PathVariable("pedidoID") int pedidoID, 
				@PathVariable("productoID") int productoID, Map<String, Object> model) {
			LineaPedido lineaPedido = lineaPedidoService.findLineaPedidoById(lineapedidoID).get();
			Producto producto = lineaPedido.getProducto();		
			model.put("productos", producto);
			model.put("lineapedidos", lineaPedido);
			return "pedidos/formularioModificarCantidad";
		}
		@PostMapping(value="/new/resumendelpedido/edit/{pedidoID}/{lineapedidoID}/{productoID}")
		public String procesarEditarLineaPedido(@PathVariable("pedidoID") int pedidoID, @PathVariable("lineapedidoID") int lineapedidoID, 
				@PathVariable("productoID") int productoID, @Valid LineaPedido lineaPedido, BindingResult result) {
				if (result.hasErrors()) {
					return VIEWS_SELECCION_CANTIDAD_PRODUCTO;
				} else {
					lineaPedido.setId(lineapedidoID);
					lineaPedido.setProducto(productoService.findProductoById(productoID).get());
					this.lineaPedidoService.saveLineaPedido(lineaPedido);
					return "redirect:/pedidos/new/resumendelpedido/" + pedidoID;
				}
		}
			
//Formulario para finalizar pedido	
		@GetMapping(value = "/new/finalizarpedido/{pedidoID}")
		public String finalizarPedido(@PathVariable("pedidoID") int pedidoID, Map<String, Object> model) {
			Pedido pedido = pedidoService.findPedidoById(pedidoID).get();
			model.put("pedidos", pedido);

			return VIEWS_FINALIZAR_PEDIDO;
		}
		@PostMapping(value = "/new/finalizarpedido/{pedidoID}")
		public String procesarFinalizarPedido(@PathVariable("pedidoID") int pedidoID, Map<String, Object> model, @Valid Pedido pedido, 
				BindingResult result) {
			if (result.hasErrors()) {
				return VIEWS_FINALIZAR_PEDIDO;
			} else{
				pedido.setId(pedidoID);
				pedido.setFecha(LocalDateTime.now());
				//pedido.setHoraEstimada(LocalTime.now().plusMinutes(30));
				if(pedido.getHoraEstimada().equals(LocalTime.parse("03:00:00"))) {
                    pedido.setHoraEstimada(LocalTime.now().plusMinutes(30));
                } else if(pedido.getHoraEstimada().equals(LocalTime.parse("03:00:01"))){
                    pedido.setHoraEstimada(LocalTime.now().plusHours(1));
                } else if(pedido.getHoraEstimada().equals(LocalTime.parse("03:00:02"))) {
                    pedido.setHoraEstimada(LocalTime.now().plusHours(1).plusMinutes(30));
                } else if(pedido.getHoraEstimada().equals(LocalTime.parse("03:00:03"))) {
                    pedido.setHoraEstimada(LocalTime.now().plusHours(2));
                } 
				pedido.setEstadopedido(estadoPedido.pendiente);
				pedidoService.savePedido(pedido);
				return "redirect:/pedidos";
			}
		}
		
		@GetMapping(value="/delete/{pedidoID}")
		public String borrarPedido(@PathVariable("pedidoID") int pedidoID, ModelMap modelMap) {
			Optional<Pedido> pedido = pedidoService.findPedidoById(pedidoID);
			if(pedido.isPresent()) {
				pedidoService.deletePedido(pedido.get());
				modelMap.addAttribute("message", "Pedido borrado correctamente");
			} else {
				modelMap.addAttribute("message", "Pedido no encontrado");
			}
			return "redirect:/pedidos";
		} 

}

