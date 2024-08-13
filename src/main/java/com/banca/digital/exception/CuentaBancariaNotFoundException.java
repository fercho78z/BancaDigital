package com.banca.digital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaBancariaNotFoundException extends Exception {
private static final long serialVersionUID=1L;

public static long getSerialversionuid() {
	return serialVersionUID;
}
public CuentaBancariaNotFoundException() {
}
public CuentaBancariaNotFoundException(String message) {
	super(message);
}

public CuentaBancariaNotFoundException(String message, Throwable cause) {
	super(message,cause);
}

}

