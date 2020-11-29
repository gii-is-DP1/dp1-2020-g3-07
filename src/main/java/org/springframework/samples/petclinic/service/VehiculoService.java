package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiculoService {
	
	@Autowired
	private VehiculoRepository VehiculoRepo;
	
	@Transactional
	public int vehiculoCount() {
		return (int) VehiculoRepo.count();
	}
	
	@Transactional
	public Iterable<Vehiculo> findAll(){
		return VehiculoRepo.findAll();
	}
}