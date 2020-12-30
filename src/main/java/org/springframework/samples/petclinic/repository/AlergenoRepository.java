package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Alergeno;

public interface AlergenoRepository extends CrudRepository<Alergeno, Integer>{
	
	@Query("SELECT alergenotype FROM Alergeno alergenotype ORDER BY alergenotype.name")
	static List<Alergeno> findAlergenoTypes() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
