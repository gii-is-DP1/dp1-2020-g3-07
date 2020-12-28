package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido extends BaseEntity implements Comparable<Pedido>{

//	@Column(name = "idPedido")
//	@NotEmpty
//	private String idPedido;
	
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd :HH:mm:ss")
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
		
	@NotNull
	@Column(name="tipopedido")
	@Enumerated(value = EnumType.STRING)
	private tipoPedido tipopedido;
	public enum tipoPedido {
		aDomicilio,
		enLocal;
}
	@Override
	public int compareTo(Pedido o) {
		if ( fecha.isBefore(o.getFecha())) {
			return -1;
		}if(fecha.isAfter(o.getFecha())) {
			return 1;
		}
		return 0;
	}
	
	@ManyToOne
	private Cliente cliente;
	
	// quitado optional false
	
}
	
	
