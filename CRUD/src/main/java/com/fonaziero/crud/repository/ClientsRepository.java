package com.fonaziero.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonaziero.crud.entity.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Long>{

}
