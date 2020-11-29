package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo extends NamedEntity{
	
	@Column(name = "Matricula")
	private String matricula;
	
	@Column(name = "Tipo de Vehiculo")
	private TipoVehiculo tipoVehiculo;

}
