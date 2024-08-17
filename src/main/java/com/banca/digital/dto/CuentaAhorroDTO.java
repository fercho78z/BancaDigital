package com.banca.digital.dto;

import java.util.Date;

import com.banca.digital.enums.EstadoCuenta;

import lombok.Data;

@Data
public class CuentaAhorroDTO extends CuentaBancariaDTO{
	
	private String id;
	private double balance;
	private double tasaDeInteres;
	private ClienteDTO clienteDTO;
	private EstadoCuenta estadoCuenta;
	private Date fechaCreacion;
}
