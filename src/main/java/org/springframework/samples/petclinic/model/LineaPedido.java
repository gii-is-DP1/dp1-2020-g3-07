package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lineaPedidos")
public class LineaPedido extends BaseEntity{

	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne(optional = false)
	private Producto producto;
	
}
