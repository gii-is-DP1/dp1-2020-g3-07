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
public class Vehiculo {

	@NotEmpty
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name="tipovehiculo")
    @Enumerated(value = EnumType.STRING)
    private Tipovehiculo tipovehiculo;
	
}
