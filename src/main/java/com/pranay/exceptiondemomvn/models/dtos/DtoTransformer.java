package com.pranay.exceptiondemomvn.models.dtos;

public interface DtoTransformer<T> {
	T convertToEntity();
}
