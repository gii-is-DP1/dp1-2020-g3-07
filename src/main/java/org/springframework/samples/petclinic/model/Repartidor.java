package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repartidores")
public class Repartidor extends Empleado{

	@Column(name = "usuario")
    @NotEmpty
	String usuario;
	
	@Column(name = "contrasena")
    @NotEmpty
	String contrasena;
	
	@OneToMany
	//(cascade = CascadeType.ALL, mappedBy = "repartidor")
	private Set<Reparto> repartos;
	
}