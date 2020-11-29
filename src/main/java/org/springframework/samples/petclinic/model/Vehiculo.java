package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo extends NamedEntity{
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "tipoDeVehiculo")
	private TipoVehiculo tipoVehiculo;

}
