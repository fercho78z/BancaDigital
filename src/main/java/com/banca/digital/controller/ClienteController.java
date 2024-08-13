package com.banca.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.servicios.CuentaBancariaService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/clientes")
	public List<Cliente> listarClientes(){
		return cuentaBancariaService.listCliente();
	}

}
