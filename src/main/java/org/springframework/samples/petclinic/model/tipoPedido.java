package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class tipoPedido {
	
	private String aDomicilio;
	private String enLocal;
	
	
	public tipoPedido() {
		super();
	}

}