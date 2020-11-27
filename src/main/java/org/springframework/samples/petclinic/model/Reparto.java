package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "repartos")
public class Reparto extends BaseEntity{

	@Column(name = "fecha")
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaHora;
	
	@Column(name = "horaInicio")
    @NotEmpty
    @DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime horaInicio;
	
	@Column(name = "horaFin")					// es opcional ponerla
    @DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime horaFin;
	
}
