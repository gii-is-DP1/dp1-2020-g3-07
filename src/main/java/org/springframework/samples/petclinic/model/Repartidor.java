package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "repartidores")
public class Repartidor extends Empleados{

	@Column(name = "usuario")
    @NotEmpty
	String usuario;
	
	@Column(name = "contraseña")
    @NotEmpty
	String contraseña;
	
}