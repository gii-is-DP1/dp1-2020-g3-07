package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Empleado;
import org.springframework.samples.petclinic.model.Owner;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

	
//	@Query("SELECT empleado FROM Empleados owner WHERE empleado.id =:id")
//	public Empleados findById(@Param("id") int id);
}
