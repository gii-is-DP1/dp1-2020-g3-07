package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleados extends NamedEntity{
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dni")
    private String dni;
    @Column(name = "sueldo")
    private Integer sueldo;
    @Column(name = "fechanacimiento")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechanacimiento;

}

