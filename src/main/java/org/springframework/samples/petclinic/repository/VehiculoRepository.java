package org.springframework.samples.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer>{
	@Query("SELECT vehiculos FROM Vehiculo vehiculos ORDER BY vehiculos.matricula")
	List<Vehiculo> findVehiculo() throws DataAccessException;
	
	List<Vehiculo> findByTipovehiculo(TipoVehiculo tipovehiculo);
	
	Optional<Vehiculo> findByMatricula(String matricula);
}
