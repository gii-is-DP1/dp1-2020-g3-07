package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	
	@Transactional(readOnly = true)
	public Optional<Vehiculo> findVehiculoById(int id) throws DataAccessException {
		return VehiculoRepo.findById(id);
	}

	
	@Transactional
	public void saveVehiculo(Vehiculo vehiculo) throws DataAccessException {
		VehiculoRepo.save(vehiculo);	
	}
	
	@Transactional
	public void deleteVehiculo(Vehiculo vehiculo) throws DataAccessException {
		VehiculoRepo.delete(vehiculo);	
	}
	
}