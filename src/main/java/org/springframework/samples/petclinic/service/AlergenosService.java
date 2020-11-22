package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.repository.AlergenosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlergenosService {
	@Autowired
	private AlergenosRepository AlergenosRepos;
	
	@Transactional
	public Iterable<Alergenos> findAll(){
		return AlergenosRepos.findAll();
	}

	@Transactional
	public int AlergenosCount() {
		return (int)AlergenosRepos.count();
	}
	
}
