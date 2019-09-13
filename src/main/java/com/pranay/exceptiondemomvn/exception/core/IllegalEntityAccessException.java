package com.pranay.exceptiondemomvn.exception.core;

public class IllegalEntityAccessException extends RuntimeException {
	private static final long serialVersionUID = 923373875813840752L;

	private Object[] args;

	public IllegalEntityAccessException(Object[] args) {
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}
}
