package com.fonaziero.crud.service.execption;

public class EntityNotFoundExecption extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundExecption(String msg) {
		super(msg);
	}

}
