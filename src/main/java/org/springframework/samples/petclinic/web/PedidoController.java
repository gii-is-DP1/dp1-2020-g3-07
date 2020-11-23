package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
//	private static final String VIEWS_PEDIDOS_UPDATE_FORM = "pedidos/editPedidos";
	
		@Autowired
		private PedidoService pedidoService;

	
		@GetMapping()
		public String listadoPedidos(ModelMap modelMap) {
			String vista = "pedidos/listadoPedidos";
			Iterable<Pedido> pedidos = pedidoService.findAll();
			modelMap.addAttribute("pedidos", pedidos);
			return vista;
		}

}
