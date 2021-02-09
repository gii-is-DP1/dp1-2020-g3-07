package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class Vehiculo extends BaseEntity{
	
	@Column(name = "matricula")
//	@NotEmpty
//	@Pattern(regexp = "^[0-9]{4}[A-Z]{3}", message = "el formato de matricula no es correcto")
	private String matricula;
	
	@Column(name = "tipovehiculo")
//	@NotNull(message = "no puede estar vacío")
	@Enumerated(value = EnumType.STRING)
	private TipoVehiculo tipovehiculo;
	
//	@ManyToMany(mappedBy="vehiculos")			// CascadeType.ALL, 
//	private Set<Reparto> repartos;
	
	@OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "vehiculo")
	private Repartidor repartidor;

}
