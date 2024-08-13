package com.banca.digital.exception;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends Exception  {
	private static final long serialVersionUID=1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ClienteNotFoundException(String message) {
		super(message);
	}
	public ClienteNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
