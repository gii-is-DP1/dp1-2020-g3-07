package org.springframework.samples.petclinic.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Alergeno;
import org.springframework.samples.petclinic.repository.AlergenoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlergenoService {
	@Autowired
	private  AlergenoRepository AlergenoRepos;
	
	@Transactional
	public  Iterable<Alergeno> findAll(){
		return AlergenoRepos.findAll();
	}

	@Transactional
	public int AlergenosCount() {
		return (int)AlergenoRepos.count();
	}
	
	@Transactional(readOnly = true)
	public Collection<Alergeno> findAlergenoTypes() throws DataAccessException {
		return AlergenoRepository.findAlergenoTypes();
	}
	
}
