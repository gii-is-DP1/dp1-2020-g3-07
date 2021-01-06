package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repartidores")
public class Repartidor extends Empleado{

//	@Column(name = "usuario")
//    @NotEmpty
//	String usuario;
	
//	@Column(name = "contrasena")
//    @NotEmpty
//	String contrasena;
	
	@OneToMany(mappedBy = "repartidor")
	//(cascade = CascadeType.ALL, mappedBy = "repartidor")
	private Set<Reparto> repartos;
	
	@NotNull
	@OneToOne(optional = false)
	private Vehiculo vehiculo;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	protected Set<Reparto> getRepartosInternal() {
		if (this.repartos == null) {
			this.repartos = new HashSet<>();
		}
		return this.repartos;
	}
	
	protected void setRepartosInternal(Set<Reparto> repartos) {
		this.repartos = repartos;
	}
	
	public void addReparto(Reparto reparto) {
		getRepartosInternal().add(reparto);
		reparto.setRepartidor(this);
	}
	
}