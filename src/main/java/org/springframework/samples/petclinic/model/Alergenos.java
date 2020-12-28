package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Alergenos")

public class Alergenos extends NamedEntity{
	@Column(name="Alergenos")
	@Enumerated(value=EnumType.STRING)
	private Alergeno Alergenos;
	
	@ManyToMany(mappedBy="alergenos")
	private Set<Producto> productos;
}
