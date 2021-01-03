package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

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
	@NotNull
	@Digits(fraction = 0, integer = 9)
	private Integer telefono;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
//	@Column(name = "email")
//	@NotEmpty
//	private String email;
	
//	@Column(name = "contrasena")
//	@NotEmpty
//	private String contrasena;
	
    @Column(name = "fechanacimiento")
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechanacimiento;
	
	@OneToMany(mappedBy="cliente")
	private Set<Pedido> pedidos;
	
	// asociacion con entidad User para inicio de sesion
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
}
