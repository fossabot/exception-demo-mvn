package com.pranay.exceptiondemomvn.exception.core;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6755731446456164831L;

	private Object[] args;

	public EntityNotFoundException(Object[] args) {
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}
}
