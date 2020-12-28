package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lineaPedidos")
public class LineaPedido extends BaseEntity{

	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne(optional = false)
	private Producto producto;
	
}
