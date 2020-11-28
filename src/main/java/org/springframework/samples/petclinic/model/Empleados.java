package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleados extends BaseEntity{
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    @Column(name = "dni")
    @NotEmpty
    private String dni;
    @Column(name = "sueldo")
    @NotEmpty
    private Integer sueldo;
    @Column(name = "fechanacimiento")
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechanacimiento;

}

