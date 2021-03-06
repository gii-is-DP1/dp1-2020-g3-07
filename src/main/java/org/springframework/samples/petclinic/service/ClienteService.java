package org.springframework.samples.petclinic.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

//	@Autowired
	private ClienteRepository clientRepos;
	
	@Autowired
	public ClienteService(ClienteRepository cR) {
		this.clientRepos = cR;
	}
	
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

	public Optional<Cliente> findClienteById(int id)throws DataAccessException {
		return clientRepos.findById(id);
	}
	
	public Cliente findClienteByUsername(String username)throws DataAccessException {
		Cliente result = null;
		Iterator<Cliente> it = clientRepos.findAll().iterator();
		while(it.hasNext()) {
			Cliente elemento = it.next();
			if(elemento.getUser().getUsername().equals(username)) {
				return elemento;
			}
		}
		return result;
	} 
	
	
}
