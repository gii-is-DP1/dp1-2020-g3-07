package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido extends NamedEntity {

//	@Column(name = "idPedido")
//	@NotEmpty
//	private String idPedido;
	
//	@Column(name = "fechaHora")
//	@NotEmpty
//	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss Z z")
//	private LocalDate fechaHora;
	
	@Column(name = "comentario")
	private String comentario;
	
	@Column(name = "valoracion")
	private Integer valoracion;
	

	

//	@NotEmpty
//	@Embedded
//	private metodoPago metodoPago;
//
//	@NotNull
//	@Embedded
//	private estadoPedido estadoPedido;
	
}