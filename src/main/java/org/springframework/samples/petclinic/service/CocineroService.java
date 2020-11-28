package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CocineroService {

	@Autowired
	CocineroRepository cocineroRepos;
	
	@Transactional
	public Iterable<Cocinero> findAll(){
		return cocineroRepos.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Cocinero> findCocineroById(int id) throws DataAccessException {
		return cocineroRepos.findById(id);
	}
	
	@Transactional
	public void saveCocinero(Cocinero d) throws DataAccessException{
		cocineroRepos.save(d);
	}
	
	@Transactional
	public void deleteCocinero(Cocinero d) throws DataAccessException{
		cocineroRepos.delete(d);
	}
	
}
