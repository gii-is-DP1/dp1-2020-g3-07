package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Empleados;
import org.springframework.samples.petclinic.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepo;
	
	@Transactional
	public int empleadoCount(){
		return (int)empleadoRepo.count();
	}
	
	@Transactional
	public Iterable<Empleados> findAll(){
		return empleadoRepo.findAll();
	}
	
	
	
	
	
	
//	@Transactional
//	public void save(Empleados empleado) {
//		empleadoRepo.save(empleado);
//	}
//	
//	@Transactional(readOnly = true)
//	public Empleados findEmpleadoById(int id) throws DataAccessException {
//		return empleadoRepo.findById(id);
//	}
	


	
}
