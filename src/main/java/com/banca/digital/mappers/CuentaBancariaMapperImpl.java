package com.banca.digital.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entities.Cliente;

@Service
public class CuentaBancariaMapperImpl {

	public ClienteDTO mapperDeCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		BeanUtils.copyProperties(cliente, clienteDTO);
		return clienteDTO;
	}
	
	public Cliente mapperDeClienteDTO(ClienteDTO clienteDto) {
		Cliente cliente=new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		return cliente;
	}
}
