package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

@Data
@Entity
@Table(name = "Alergenos")

public class Alergenos extends NamedEntity{
	@Column(name="Alergenos")
	@Enumerated(value=EnumType.STRING)
	private Alergeno Alergenos;
}
