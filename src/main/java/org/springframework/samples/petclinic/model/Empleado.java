package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Empleado extends BaseEntity{
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    @Column(name = "dni")
    @NotEmpty
    @Pattern(regexp = "^[0-9]{8}[A-Z]{1}", message = "el valor debe ser un DNI")
    private String dni;
    @Column(name = "sueldo")
    @NotEmpty
    @Min(value = 0)
    private String sueldo;
    @Column(name = "fechanacimiento")
    @NotNull(message = "no puede estar vacío")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechanacimiento;
    
    @Column(name = "cuentabancaria")
    @CreditCardNumber
    private String cuentabancaria;

}

