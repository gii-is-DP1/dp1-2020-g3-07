package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Reparto;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.RepartidorRepository;
import org.springframework.samples.petclinic.repository.RepartoRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepartidorService {

	private RepartidorRepository repartidorRepository;
	
	private RepartoRepository repartoRepository;
	
	@Autowired
	public RepartidorService(RepartidorRepository repartidorRepository,
			RepartoRepository repartoRepository) {
		this.repartidorRepository = repartidorRepository;
		this.repartoRepository = repartoRepository;
	}
	
	@Transactional
	public int repartidorCount() {
		return (int) repartidorRepository.count();
	}
	
	@Transactional
	public Iterable<Repartidor> findAll(){
		return repartidorRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Repartidor> findRepartidorById(int id) throws DataAccessException {
		return repartidorRepository.findById(id);
	}
	
	@Transactional
	public void saveRepartidor(Repartidor d) throws DataAccessException{
		repartidorRepository.save(d);
	}
	
	@Transactional
	public void deleteRepartidor(Repartidor d) throws DataAccessException{
		repartidorRepository.delete(d);
	}
	
	@Transactional(readOnly = true)
	public Collection<Repartidor> findRepartidores(){
		Iterable<Repartidor> repartidores = repartidorRepository.findAll();
		Collection<Repartidor> res = StreamSupport.stream(repartidores.spliterator(), false).collect(Collectors.toList());
		return res;
	}
	
}
