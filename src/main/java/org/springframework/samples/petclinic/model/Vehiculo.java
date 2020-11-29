package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo extends BaseEntity{
	
	@Column(name = "matricula")
	@NotEmpty
	private String matricula;
	
	@Column(name = "tipovehiculo")
	@NotEmpty
	@Enumerated(value = EnumType.STRING)
	private TipoVehiculo tipoVehiculo;
	public enum TipoVehiculo {
		Coche,
		Moto;
}	

}
