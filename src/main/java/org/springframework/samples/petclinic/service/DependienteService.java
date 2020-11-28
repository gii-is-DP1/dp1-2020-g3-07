package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.DependienteRepository;
import org.springframework.stereotype.Service;

@Service
public class DependienteService {

	@Autowired
	DependienteRepository dependienteRepos;
	
}
