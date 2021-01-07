package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.model.Alergeno;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ConjuntoAlergenos {

	private List<Alergeno> alergenosAsignados;

}
