package com.banca.digital.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.OperacionCuenta;

@Service
public class CuentaBancariaMapperImpl {

	public ClienteDTO mapperDeCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		BeanUtils.copyProperties(cliente, clienteDTO);
		return clienteDTO;
	}
	
	public Cliente mapperDeClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente=new Cliente();
		BeanUtils.copyProperties(clienteDTO, cliente);
		return cliente;
	}
	
	public CuentaAhorroDTO mapperDeAhorro(CuentaAhorro cuentaAhorro) {
		CuentaAhorroDTO cuentaAhorroDTO= new CuentaAhorroDTO();
		BeanUtils.copyProperties(cuentaAhorro, cuentaAhorroDTO);
		cuentaAhorroDTO.setClienteDTO(mapperDeCliente(cuentaAhorro.getCliente()));
		cuentaAhorroDTO.setTipo(cuentaAhorro.getClass().getSimpleName());
		/*cuentaAhorro.setFechaCreacion(cuentaAhorroDTO.getFechaCreacion());
		cuentaAhorro.setEstadoCuenta(cuentaAhorroDTO.getEstadoCuenta());
		*/return cuentaAhorroDTO;
	}

	public CuentaAhorro mapperDeAhorroDTO(CuentaAhorroDTO cuentaAhorroDTO) {
		CuentaAhorro cuentaAhorro= new CuentaAhorro();
		BeanUtils.copyProperties(cuentaAhorroDTO, cuentaAhorro);
		cuentaAhorro.setCliente(mapperDeClienteDTO(cuentaAhorroDTO.getClienteDTO()));
		cuentaAhorro.setTipo(cuentaAhorroDTO.getClass().getSimpleName());
		//cuentaAhorroDTO.setFechaCreacion(cuentaAhorroDTO.getFechaCreacion());
		//cuentaAhorroDTO.setEstadoCuenta(cuentaAhorro.getEstadoCuenta());
		return cuentaAhorro;
	}
	
	
	public CuentaActualDTO mapperDeCuentaActual(CuentaActual cuentaActual) {
		CuentaActualDTO cuentaActualDTO= new CuentaActualDTO();
		BeanUtils.copyProperties(cuentaActual, cuentaActualDTO);
		cuentaActualDTO.setClienteDTO(mapperDeCliente(cuentaActual.getCliente()));
		cuentaActualDTO.setTipo(cuentaActual.getClass().getSimpleName());
		cuentaActualDTO.setFechaDeCreacion(cuentaActual.getFechaCreacion());
		cuentaActualDTO.setEstadoCuenta(cuentaActual.getEstadoCuenta());
		return cuentaActualDTO;
	}

	public CuentaActual mapperDeCuentaActualDTO(CuentaActualDTO cuentaActualDTO) {
		CuentaActual cuentaActual= new CuentaActual();
		BeanUtils.copyProperties(cuentaActualDTO, cuentaActual);
		cuentaActual.setCliente(mapperDeClienteDTO(cuentaActualDTO.getClienteDTO()));
		cuentaActual.setTipo(cuentaActualDTO.getClass().getSimpleName());
		//cuentaActualDTO.setFechaDeCreacion(cuentaActual.getFechaCreacion());
		cuentaActual.setEstadoCuenta(cuentaActualDTO.getEstadoCuenta());
		return cuentaActual;
	}
	
	public OperacionCuentaDTO mappearDeOperacionCuenta(OperacionCuenta operacionCuenta) {
		OperacionCuentaDTO operacionCuentaDTO=new OperacionCuentaDTO();
		BeanUtils.copyProperties(operacionCuenta, operacionCuentaDTO);
		operacionCuentaDTO.setFechaDeOperacion(operacionCuenta.getFechaOperacion());
		return operacionCuentaDTO;
	}
	
	public OperacionCuenta mappearDeOperacionCuentaDTO(OperacionCuentaDTO operacionCuentaDTO) {
		OperacionCuenta operacionCuenta=new OperacionCuenta();
		BeanUtils.copyProperties(operacionCuentaDTO, operacionCuenta);
		operacionCuenta.setFechaOperacion(operacionCuentaDTO.getFechaDeOperacion());
		return operacionCuenta;
	}
}
	

