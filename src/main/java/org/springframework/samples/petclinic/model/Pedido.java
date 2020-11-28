package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido extends BaseEntity {

//	@Column(name = "idPedido")
//	@NotEmpty
//	private String idPedido;
	
	@Column(name = "fecha")
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
	private LocalDateTime fecha;
	
	
	@Column(name = "comentario")
	private String comentario;
	
	@Column(name = "valoracion")
	private Integer valoracion;
	
	@NotNull
	@Column(name="metodopago")
    @Enumerated(value = EnumType.STRING)
    private Metodopago metodopago;
	public enum Metodopago {
		efectivo,
		tarjeta;
}
	@NotNull
	@Column(name="estadopedido")
    @Enumerated(value = EnumType.STRING)
    private Estadopedido estadopedido;
	public enum Estadopedido {
		pendiente,
		enReparto,
		entregado;
}	
	
	
}