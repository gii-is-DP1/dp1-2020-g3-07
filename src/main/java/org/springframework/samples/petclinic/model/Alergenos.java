package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

@Data
@Entity
@Table(name = "Alergenos")

public class Alergenos extends NamedEntity{
	@Embedded
	private Alergeno Alergeno;
}
