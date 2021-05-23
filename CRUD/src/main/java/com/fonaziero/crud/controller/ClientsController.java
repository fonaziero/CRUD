package com.fonaziero.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fonaziero.crud.dto.ClientsDTO;
import com.fonaziero.crud.entity.Clients;
import com.fonaziero.crud.service.clientsService;

@RestController
@RequestMapping("/clients")
public class ClientsController {

	@Autowired
	clientsService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientsDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10")Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		
		Page<ClientsDTO> list = service.findAll(pageRequest);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ClientsDTO>> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findAllById(id));
		
	}
	
	@PostMapping
	public ResponseEntity<ClientsDTO> insert(@RequestBody ClientsDTO clients) {
		
		clients = service.insert(clients);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clients.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clients);
	}
	
	
	
}
