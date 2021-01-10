package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alergenos")

public class Alergeno extends BaseEntity{
	@Column(name="alergenotype")
	@Enumerated(value=EnumType.STRING)
	private AlergenoEnum alergenotype;
	
	@ManyToMany(mappedBy="alergenos", cascade = CascadeType.ALL)
	private Set<Producto> productos;
	
	
}
