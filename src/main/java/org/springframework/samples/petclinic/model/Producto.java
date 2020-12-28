package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto extends NamedEntity{
	
	@Column(name = "precio")
	private Integer precio;
	
    @Column(name="tamanopizza")
    @Enumerated(value = EnumType.STRING)
    private Tamanopizza tamanopizza;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToMany
    private Set<Alergenos> alergenos;

}
