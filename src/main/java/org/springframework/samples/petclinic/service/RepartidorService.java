package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.repository.RepartidorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepartidorService {

	@Autowired
	RepartidorRepository repartidorRepos;
	
	@Transactional
	public int repartidorCount() {
		return (int) repartidorRepos.count();
	}
	
	@Transactional
	public Iterable<Repartidor> findAll(){
		return repartidorRepos.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Repartidor> findRepartidorById(int id) throws DataAccessException {
		return repartidorRepos.findById(id);
	}
	
	@Transactional
	public void saveRepartidor(Repartidor d) throws DataAccessException{
		repartidorRepos.save(d);
	}
	
	@Transactional
	public void deleteRepartidor(Repartidor d) throws DataAccessException{
		repartidorRepos.delete(d);
	}
	
}
