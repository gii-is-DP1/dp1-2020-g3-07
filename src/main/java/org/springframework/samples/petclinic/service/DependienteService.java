package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.repository.DependienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DependienteService {

	@Autowired
	DependienteRepository dependienteRepos;
	
	@Transactional
	public int dependienteCount() {
		return (int) dependienteRepos.count();
	}
	
	@Transactional
	public Iterable<Dependiente> findAll(){
		return dependienteRepos.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Dependiente> findDependienteById(int id) throws DataAccessException {
		return dependienteRepos.findById(id);
	}
	
	@Transactional
	public void saveDependiente(Dependiente d) throws DataAccessException{
		dependienteRepos.save(d);
	}
	
	@Transactional
	public void deleteDependiente(Dependiente d) throws DataAccessException{
		dependienteRepos.delete(d);
	}
	
}
