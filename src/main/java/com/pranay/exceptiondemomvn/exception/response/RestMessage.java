package com.pranay.exceptiondemomvn.exception.response;

import java.util.List;

public class RestMessage {
	private String message;
	private List<String> details;

	public RestMessage(String message, List<String> details) {
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getMessages() {
		return details;
	}
}
