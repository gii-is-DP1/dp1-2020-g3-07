package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.RepartoRepository;
import org.springframework.stereotype.Service;

@Service
public class RepartoService {

	@Autowired
	RepartoRepository repRepos;



}