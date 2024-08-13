package com.banca.digital.servicios;

import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.exception.BalanceInsuficienteException;
import com.banca.digital.exception.ClienteNotFoundException;
import com.banca.digital.exception.CuentaBancariaNotFoundException;

import java.util.List;

public interface CuentaBancariaService {
	
	Cliente saveCliente(Cliente cliente);
	CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundException;
	CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial,double tasaInteres, Long clienteId) throws ClienteNotFoundException;
	List<Cliente> listCliente();
	CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException;
	void debit(String cuentaId,double monto,String descripcion) throws CuentaBancariaNotFoundException,BalanceInsuficienteException;
	void credit(String cuentaId, double monto,String descrpcion) throws CuentaBancariaNotFoundException;
	void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	List<CuentaBancaria> listCuentaBancarias();
}
