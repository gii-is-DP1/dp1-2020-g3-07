package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "alergenos")
public class Alergeno {

	@Column(name="alergeno")
    @Enumerated(value = EnumType.STRING)
    private AlergenoEnum alergeno;
	
}
