package com.fonaziero.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fonaziero.crud.dto.ClientsDTO;
import com.fonaziero.crud.entity.Clients;
import com.fonaziero.crud.repository.ClientsRepository;
import com.fonaziero.crud.service.execption.EntityNotFoundExecption;


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
	public ClientsDTO findAllById(Long id) {	
		
		Optional<Clients> client = repository.findById(id);
		Clients entity = client.orElseThrow(() -> new EntityNotFoundExecption("Entity Not Found"));
		
		return  new ClientsDTO(entity);
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
			
		} catch (EntityNotFoundExecption e) {
			throw new EntityNotFoundExecption("Id not found" + id);
		}
	}


	public void delete (Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundExecption("Id not found " + id);
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
