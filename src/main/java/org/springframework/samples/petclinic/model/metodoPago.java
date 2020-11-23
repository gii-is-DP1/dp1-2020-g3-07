package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class metodoPago {
	
	private String efectivo;
	private String tarjeta;
	
	
	public metodoPago() {
		super();
	}

}
