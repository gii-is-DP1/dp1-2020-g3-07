package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.AlergenoService;
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
@RequestMapping("/productos")
public class ProductoController {
	
	private static final String VIEWS_PRODUCTO_CREATE_OR_UPDATE_FORM = "productos/createOrUpdateProductoForm";
	private final ProductoService productoService;
	private final AlergenoService alergenoService;
	
	@Autowired
	public ProductoController(ProductoService productoService, AlergenoService alergenoService) {
		this.productoService = productoService;
		this.alergenoService = alergenoService;
	}
	
	@GetMapping()
	public String listadoProductos(ModelMap modelMap) {
		String vista = "productos/listadoProductos";
		Iterable<Producto> productos = productoService.findAll();
		modelMap.addAttribute("productos", productos);
		log.info("Se muestra el listado de productos");
		return vista;
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Producto producto = new Producto();
		
		Iterator<Alergeno> ita = alergenoService.findAll().iterator();	
		List<Alergeno> result = new ArrayList<>();
		while (ita.hasNext()) {
			result.add(ita.next());
		}
		model.put("producto", producto);
		model.put("alergenos", result);
		log.info("Solicitud para crear nuevo producto");
		return VIEWS_PRODUCTO_CREATE_OR_UPDATE_FORM;
	}
	

	
	

	
	
	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Producto producto, BindingResult result, Model model) {
		if (result.hasErrors()) {
	        Iterator<Alergeno> ita = alergenoService.findAll().iterator();    
	        List<Alergeno> resultt = new ArrayList<>();
	        while (ita.hasNext()) {
	            resultt.add(ita.next());
	        }
	        model.addAttribute("alergenos", resultt);
	        log.info("No se pudo crear el producto debido a errores de validacion de entrada de datos");
	        return VIEWS_PRODUCTO_CREATE_OR_UPDATE_FORM;
	    }
		else {
			//creating owner, user and authorities
			this.productoService.saveProducto(producto);
			log.info("Producto de nombre "+producto.getName()+" anadido con exito");
			return "redirect:/productos";
		}
	}
	
	
	@GetMapping(value="/delete/{productoID}")
	public String borrarProducto(@PathVariable("productoID") int productoID, ModelMap modelMap) {
		Optional<Producto> producto = productoService.findProductoById(productoID);
		if(producto.isPresent()) {
			productoService.deleteProducto(producto.get());
			log.info("Producto con id = "+productoID+" borrado con exito");
			modelMap.addAttribute("message", "Producto borrado correctamente");
		} else {
			modelMap.addAttribute("message", "Producto no encontrado");
		}
		return "redirect:/productos";
	}
	
	@GetMapping(value = "/save/{productoID}")
	public String initUpdateForm(@PathVariable("productoID") int productoID, Model model) {
		Optional<Producto> producto = this.productoService.findProductoById(productoID);
		if(producto.isPresent()) {

            model.addAttribute("producto", producto.get());
            Iterator<Alergeno> ita = alergenoService.findAll().iterator();    
            List<Alergeno> result = new ArrayList<>();
            while (ita.hasNext()) {
                result.add(ita.next());
            }            
            model.addAttribute("alergenos", result);
            log.info("Solicitud para asignar alergenos al producto de id = "+productoID);
            return VIEWS_PRODUCTO_CREATE_OR_UPDATE_FORM;
        } else {
            model.addAttribute("message", "Producto no encontrado");
            return "redirect:/productos";
        }

	}
	
	

	@PostMapping(value = "/save/{productoID}")
	public String processUpdateForm(@Valid Producto producto, BindingResult result,
			@PathVariable("productoID") int productoID, Model model) {
		if (result.hasErrors()) {
	        Iterator<Alergeno> ita = alergenoService.findAll().iterator();    
	        List<Alergeno> resultt = new ArrayList<>();
	        while (ita.hasNext()) {
	            resultt.add(ita.next());
	        }
	        model.addAttribute("alergenos", resultt);
	        log.info("No se pudo crear el producto debido a errores de validacion de entrada de datos");
	        return VIEWS_PRODUCTO_CREATE_OR_UPDATE_FORM;
	    }
		else {
			producto.setId(productoID);
			this.productoService.saveProducto(producto);
			log.info("Se asignaron los alergenos al producto con id = "+productoID+" con exito");
			return "redirect:/productos";
		}
	}

}
