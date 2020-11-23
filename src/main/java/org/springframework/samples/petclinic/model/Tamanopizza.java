package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class Tamanopizza {
	private String pequena;
	private String mediana;
	private String familiar;
	
	public Tamanopizza() {
		super();
	}

}
