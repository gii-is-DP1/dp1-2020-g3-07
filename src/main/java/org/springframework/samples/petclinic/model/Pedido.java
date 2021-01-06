package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	
	@Column(name = "horaEstimada")					// es opcional ponerla
    @DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime horaEstimada;
	
	@Column(name = "horaCliente")					// es opcional ponerla
    @DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime horaCliente;
	
	@Column(name = "comentario")
	private String comentario;
	
	@Column(name = "valoracion")
	private Integer valoracion;
	
	//@NotNull
	@Column(name="metodopago")
    @Enumerated(value = EnumType.STRING)
    private metodoPago metodopago;

	//@NotNull
	@Column(name="estadopedido")
    @Enumerated(value = EnumType.STRING)
    private estadoPedido estadopedido;

		
	//@NotNull
	@Column(name="tipopedido")
	@Enumerated(value = EnumType.STRING)
	private tipoPedido tipopedido;
	
	@ManyToOne
	@JoinColumn(name = "reparto_id")
	private Reparto reparto;
	
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
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	// quitado optional false
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<LineaPedido> lineaPedidos;
	
	
	
}
	
	
