package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.repository.RepartoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepartoService {

	@Autowired
	RepartoRepository repRepos;
	
	@Transactional
	public void save(Reparto r) {
		this.repRepos.save(r);
	}

	@Transactional(readOnly = true)
	public List<Reparto> findByRepartidorId(Integer id){
		return this.repRepos.findByRepartidorId(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Reparto> findById(int id){
		return this.repRepos.findById(id);
	}

}