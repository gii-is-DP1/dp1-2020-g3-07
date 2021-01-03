package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reparto;

public interface RepartoRepository extends CrudRepository<Reparto, Integer>{

	List<Reparto> findByRepartidorId(Integer repartidorId);
	
}