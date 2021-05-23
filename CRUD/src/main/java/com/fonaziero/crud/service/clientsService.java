package com.fonaziero.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fonaziero.crud.dto.ClientsDTO;
import com.fonaziero.crud.entity.Clients;
import com.fonaziero.crud.repository.ClientsRepository;

@Service
public class clientsService {
	
	@Autowired
	ClientsRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientsDTO> findAll(PageRequest pageRequest) {	
		Page<Clients> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientsDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Optional<ClientsDTO> findAllById(Long id) {	
		Optional<Clients> list = repository.findById(id);
		return list.map(x -> new ClientsDTO(x));
	}
	
	@Transactional
	public ClientsDTO insert(ClientsDTO clients) {
		
		Clients entity = new Clients();
		
		convertDtoToEntity(clients, entity);
		
		entity = repository.save(entity);
		
		return new ClientsDTO(entity);
	}
	
	@Transactional
	public ClientsDTO update (Long id, ClientsDTO clients) {
		
		try {
			Clients entity = repository.getOne(id);
			convertDtoToEntity(clients, entity);
			entity = repository.save(entity);
			return new ClientsDTO(entity);
			
		} catch (Exception e) {
			throw new Error(e) ;
		}
	}
	

	public void delete (Long id) {
		try {
			repository.deleteById(id);
		}catch (Exception e) {
			throw new Error(e);
		}
	}
	
	
	public void convertDtoToEntity(ClientsDTO clients, Clients entity ) {
			
		entity.setName(clients.getName());
		entity.setCpf(clients.getCpf());
		entity.setIncome(clients.getIncome());
		entity.setBirthDate(clients.getBirthDate());
		entity.setChildren(clients.getChildren());	
		
	}
	
	
}
