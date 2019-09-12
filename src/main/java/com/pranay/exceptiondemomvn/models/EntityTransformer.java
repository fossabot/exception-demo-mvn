package com.pranay.exceptiondemomvn.models;

public interface EntityTransformer<T> {
	T convertToDto();
}
