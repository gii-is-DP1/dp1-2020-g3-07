package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto extends NamedEntity{
	
	@Column(name = "precio")
	@NotNull(message="No puede estar vacío")
	private Double precio;
	
    @Column(name="tamanopizza")
    @Enumerated(value = EnumType.STRING)
    private Tamanopizza tamanopizza;
    
    
    @Column(name = "descripcion")
    @NotNull
    @NotEmpty(message="No puede estar vacío")
    private String descripcion;
    
    @Column(name = "alergenos")
    @ManyToMany
    private List<Alergeno> alergenos;

}
