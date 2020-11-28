package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Dependiente;
import org.springframework.samples.petclinic.repository.DependienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DependienteService {

	@Autowired
	DependienteRepository dependienteRepos;
	
	@Transactional
	public Iterable<Dependiente> findAll(){
		return dependienteRepos.findAll();
	}
	
}
