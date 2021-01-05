package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class Vehiculo extends BaseEntity{
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "tipovehiculo")
	@Enumerated(value = EnumType.STRING)
	private TipoVehiculo tipovehiculo;
	
//	@ManyToMany(mappedBy="vehiculos")			// CascadeType.ALL, 
//	private Set<Reparto> repartos;
	
	@OneToOne(optional = true)
	private Repartidor repartidor;

}
