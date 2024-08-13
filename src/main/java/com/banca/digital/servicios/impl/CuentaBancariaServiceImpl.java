package com.banca.digital.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.entities.OperacionCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.exception.BalanceInsuficienteException;
import com.banca.digital.exception.ClienteNotFoundException;
import com.banca.digital.exception.CuentaBancariaNotFoundException;
import com.banca.digital.repository.ClienteRepository;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.repository.OperacionCuentaRepository;
import com.banca.digital.servicios.CuentaBancariaService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class CuentaBancariaServiceImpl implements CuentaBancariaService{
	
	@Autowired
	private ClienteRepository clienteR;
	@Autowired
	private CuentaBancariaRepository cuentaBancariaR;
	@Autowired
	private OperacionCuentaRepository operacionCuentaR;

	@Override
	public Cliente saveCliente(Cliente cliente) {
		log.info("Guradando un nuevo cliente");
		Cliente clienteDB=clienteR.save(cliente);
		return clienteDB;
	}

	@Override
	public CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId)
			throws ClienteNotFoundException {
		Cliente cliente = clienteR.findById(clienteId).orElse(null);
		if(cliente == null) {
			throw new ClienteNotFoundException("Cliente No encontrado");
		}
		CuentaActual cuentaActual=new CuentaActual();
		cuentaActual.setId(UUID.randomUUID().toString());
		cuentaActual.setFechaCreacion(new Date());
		cuentaActual.setSobregiro(sobregiro);
		cuentaActual.setCliente(cliente);
		
		CuentaActual cuentaActualDB = cuentaBancariaR.save(cuentaActual);
		return cuentaActualDB;
	}

	@Override
	public CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId)
			throws ClienteNotFoundException {
		Cliente cliente = clienteR.findById(clienteId).orElse(null);
		if(cliente == null) {
			throw new ClienteNotFoundException("Cliente No encontrado");
		}
		CuentaAhorro cuentaAhorro=new CuentaAhorro();
		cuentaAhorro.setId(UUID.randomUUID().toString());
		cuentaAhorro.setFechaCreacion(new Date());
		cuentaAhorro.setTasaDeInteres(tasaInteres);
		cuentaAhorro.setCliente(cliente);
		
		CuentaAhorro cuentaAhorroDB = cuentaBancariaR.save(cuentaAhorro);
		return cuentaAhorroDB;
	}

	@Override
	public List<Cliente> listCliente() {
		// TODO Auto-generated method stub
		return clienteR.findAll();
	}

	@Override
	public CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {
		CuentaBancaria cuentaBancaria = cuentaBancariaR.findById(cuentaId).orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta Bancaria No encontrada"));
		
		return cuentaBancaria;
	}

	@Override
	public void debit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		
		CuentaBancaria cuentaBancaria=getCuentaBancaria(cuentaId);
		if(cuentaBancaria.getBalance()<monto) {
			throw new BalanceInsuficienteException("Balance insuficiente");
		}
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.DEBITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFecheOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaR.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance()-monto);
		cuentaBancariaR.save(cuentaBancaria);
		
		
	}

	@Override
	public void credit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException {
		CuentaBancaria cuentaBancaria=getCuentaBancaria(cuentaId);
		
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.CREDITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFecheOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaR.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance()+monto);
		cuentaBancariaR.save(cuentaBancaria);
	}

	@Override
	public void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		debit(cuentaIdPropietario,monto,"Transferencia a : " + cuentaIdDestinatario);
		credit(cuentaIdDestinatario, monto, "Transferencia a :"+cuentaIdDestinatario);
		
	}

	@Override
	public List<CuentaBancaria> listCuentaBancarias() {
		// TODO Auto-generated method stub
		return cuentaBancariaR.findAll();
	}

}
