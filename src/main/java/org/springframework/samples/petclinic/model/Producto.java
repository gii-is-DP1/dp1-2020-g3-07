package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto extends NamedEntity{
	@Column(name = "precio")
	private Integer precio;
	
	@Embedded
	private Tamanopizza tamanopizza;
}
