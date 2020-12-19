package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente extends NamedEntity{

//	@Column(name = "nombreApellidos")
//	@NotEmpty
//	private String nombreApellidos;
	
	@Column(name = "telefono")
	@Digits(fraction = 0, integer = 9)
	private Integer telefono;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "usuario")
	@NotEmpty
	private String usuario;
	
	@Column(name = "contrasena")
	@NotEmpty
	private String contrasena;
	
	@OneToMany(mappedBy="cliente")
	private Set<Pedido> pedidos;
	
}
