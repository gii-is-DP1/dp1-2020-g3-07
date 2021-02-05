package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/carta")
public class CartaController {
	

	private final ProductoService productoService;
	
	@Autowired
	public CartaController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping()
	public String listadoCartas(ModelMap modelMap) {
		String vista = "carta/listadoCarta";
		Iterable<Producto> productos = productoService.findAll();
		modelMap.addAttribute("productos", productos);
		log.info("Mostrando carta");
		return vista;
	}
	

	
	
	
	
}
