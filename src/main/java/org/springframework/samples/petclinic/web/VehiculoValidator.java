package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VehiculoValidator implements Validator{

	private VehiculoService vehSer;
	
	@Autowired
	public VehiculoValidator(VehiculoService vS) {
		this.vehSer = vS;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Vehiculo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Vehiculo vehiculo = (Vehiculo)target;
		
		if(vehiculo.getMatricula().isEmpty()) {
			errors.rejectValue("matricula", "requerido_vehiculo", "El dato introducido no debe ser nulo");
		}else if(!vehiculo.getMatricula().matches("^[0-9]{4}[A-Z]{3}")) {
			errors.rejectValue("matricula", "requerido_vehiculo_matricula", "El dato introducido debe ser una matricula");
		}
//		else if(vehSer.findByMatricula(vehiculo.getMatricula()).isPresent()) {
//			errors.rejectValue("matricula", "requerido_vehiculo_matricula_repetida", "Ya existe un vehiculo con esa matricula");
//		}
		
		if(vehiculo.getTipovehiculo()==null) {
			errors.rejectValue("tipovehiculo", "requerido_vehiculo", "El dato introducido no debe ser nulo");
		}else if(vehiculo.getTipovehiculo().equals(TipoVehiculo.Coche) && vehSer.countTipo(vehiculo.getTipovehiculo())>=3) {
			errors.rejectValue("tipovehiculo", "requerido_vehiculo_tipo", "No puede haber mas de 3 coches");
		}
		
	}

	
	
}
