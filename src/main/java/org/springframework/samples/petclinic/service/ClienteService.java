package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clientRepos;
	
	
	@Transactional
	public int clienteCount() {
		return (int) clientRepos.count();
	}
	
	@Transactional
	public Iterable<Cliente> findAll(){
		return clientRepos.findAll();
	}
	
	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		//creating users
		clientRepos.save(cliente);	
	}
	
	
}
