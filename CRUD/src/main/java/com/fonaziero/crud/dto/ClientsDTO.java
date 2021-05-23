package com.fonaziero.crud.dto;

import java.time.Instant;

import com.fonaziero.crud.entity.Clients;

public class ClientsDTO {


	private Long id;
	private String name;
	private String cpf;
	private double income;
	private Instant birthDate;
	private Integer children;
	
	public ClientsDTO() {

	}

	public ClientsDTO(Long id, String name, String cpf, double income, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientsDTO(Clients clients) {
		this.id = clients.getId();
		this.name = clients.getName();
		this.cpf = clients.getCpf();
		this.income = clients.getIncome();
		this.birthDate = clients.getBirthDate();
		this.children = clients.getChildren();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}
	
	
}
