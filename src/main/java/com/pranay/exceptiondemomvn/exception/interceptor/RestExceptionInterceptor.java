package com.pranay.exceptiondemomvn.exception.interceptor;

import com.pranay.exceptiondemomvn.exception.core.EntityNotFoundException;
import com.pranay.exceptiondemomvn.exception.core.IllegalEntityAccessException;
import com.pranay.exceptiondemomvn.exception.response.RestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionInterceptor {
	private static final String UNEXPECTED_ERROR = "Exception.unexpected";
	private static final String NOT_FOUND_ERROR = "Exception.notFound";
	private static final String VALIDATION_ERROR = "Exception.validationError";
	private static final String ILLEGAL_ACCESS_ERROR = "Exception.illegalEntityAccess";

	private final MessageSource messageSource;

	@Autowired
	public RestExceptionInterceptor(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<RestMessage> handleEntityNotFound(EntityNotFoundException ex, Locale locale) {
		String errorMessage = messageSource.getMessage(NOT_FOUND_ERROR, ex.getArgs(), locale);
		return new ResponseEntity<>(new RestMessage(NOT_FOUND_ERROR, Collections.singletonList(errorMessage)), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalEntityAccessException.class)
	public ResponseEntity<RestMessage> handleEntityNotFound(IllegalEntityAccessException ex, Locale locale) {
		String errorMessage = messageSource.getMessage(ILLEGAL_ACCESS_ERROR, ex.getArgs(), locale);
		return new ResponseEntity<>(new RestMessage(ILLEGAL_ACCESS_ERROR, Collections.singletonList(errorMessage)), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestMessage> handleExceptions(Exception ex, Locale locale) {
		String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
		ex.printStackTrace();
		return new ResponseEntity<>(new RestMessage(errorMessage, Collections.singletonList(ex.getLocalizedMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
		BindingResult result = ex.getBindingResult();
		List<String> errorMessages = result.getAllErrors()
				.stream()
				.map(objectError -> messageSource.getMessage(objectError, locale))
				.collect(Collectors.toList());
		return new ResponseEntity<>(new RestMessage(VALIDATION_ERROR, errorMessages), HttpStatus.BAD_REQUEST);
	}
}
