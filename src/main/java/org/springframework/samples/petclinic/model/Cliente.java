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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente extends BaseEntity{

	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	@Column(name = "apellidos")
    @NotEmpty
    private String apellidos;
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 9)
	private Integer telefono;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "email")
	@NotEmpty
	private String email;
	
	@Column(name = "contrasena")
	@NotEmpty
	private String contrasena;
    @Column(name = "fechanacimiento")
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechanacimiento;
	
	@OneToMany(mappedBy="cliente")
	private Set<Pedido> pedidos;
	
}
