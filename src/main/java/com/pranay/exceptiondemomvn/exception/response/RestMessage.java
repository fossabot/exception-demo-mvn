package com.pranay.exceptiondemomvn.exception.response;

import java.util.List;

public class RestMessage {
	private String message;
	private List<String> messages;

	public RestMessage(String message, List<String> messages) {
		this.message = message;
		this.messages = messages;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getMessages() {
		return messages;
	}
}
