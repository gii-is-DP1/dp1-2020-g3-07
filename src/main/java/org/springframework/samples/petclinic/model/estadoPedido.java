package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class estadoPedido {
	
	private String pendiente;
	private String enReparto;
	private String Entregado;
	
	public estadoPedido() {
		super();
	}

}
