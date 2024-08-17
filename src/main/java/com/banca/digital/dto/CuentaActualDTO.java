package com.banca.digital.dto;

import java.util.Date;

import com.banca.digital.entities.CuentaActual;
import com.banca.digital.enums.EstadoCuenta;

import lombok.Data;

@Data
public class CuentaActualDTO extends CuentaBancariaDTO{
	
	private String id;
	private double balance;
	private Date fechaDeCreacion;
	private EstadoCuenta estadoCuenta;
	private ClienteDTO clienteDTO;
	private double sobregiro;
}

