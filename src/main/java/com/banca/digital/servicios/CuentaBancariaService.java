package com.banca.digital.servicios;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.dto.HistorialCuentaDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.exception.BalanceInsuficienteException;
import com.banca.digital.exception.ClienteNotFoundException;
import com.banca.digital.exception.CuentaBancariaNotFoundException;

import java.util.List;

public interface CuentaBancariaService {
	
	
		
	ClienteDTO saveClienteDTO(ClienteDTO clienteDTO);
	ClienteDTO updateClienteDTO(ClienteDTO clienteDTO);
	void deleteClienteDTO(Long clienteId);
	CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundException;
	CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial,double tasaInteres, Long clienteId) throws ClienteNotFoundException;
	CuentaActualDTO saveCuentaBancariaActualDTO(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundException;
	CuentaAhorroDTO saveCuentaBancariaAhorroDTO(double balanceInicial,double tasaInteres, Long clienteId) throws ClienteNotFoundException;
	List<ClienteDTO> listCliente();
	ClienteDTO getCliente(Long clienteId) throws ClienteNotFoundException;
	CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException;
	CuentaBancariaDTO getCuentaBancariaDTO(String cuentaId) throws CuentaBancariaNotFoundException;
	void debit(String cuentaId,double monto,String descripcion) throws CuentaBancariaNotFoundException,BalanceInsuficienteException;
	void credit(String cuentaId, double monto,String descrpcion) throws CuentaBancariaNotFoundException;
	void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	void debitDTO(String cuentaId,double monto,String descripcion) throws CuentaBancariaNotFoundException,BalanceInsuficienteException;
	void creditDTO(String cuentaId, double monto,String descrpcion) throws CuentaBancariaNotFoundException;
	void transferDTO(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	List<CuentaBancaria> listCuentaBancarias();
	List<CuentaBancariaDTO> listCuentaBancariasDTO();
	List<OperacionCuentaDTO> listHistorialDeCuentas(String cuentaId);
	HistorialCuentaDTO getHistorialCuenta(String cuentaId,int page,int size )throws CuentaBancariaNotFoundException;
	List<ClienteDTO> searchClientes(String keyword);
	HistorialCuentaDTO getHistorialCuentaDesc(String cuentaId,int page,int size )throws CuentaBancariaNotFoundException;
}
