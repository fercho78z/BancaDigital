package com.banca.digital.servicios.impl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
import com.banca.digital.entities.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.exception.BalanceInsuficienteException;
import com.banca.digital.exception.ClienteNotFoundException;
import com.banca.digital.exception.CuentaBancariaNotFoundException;
import com.banca.digital.mappers.CuentaBancariaMapperImpl;
import com.banca.digital.repository.ClienteRepository;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.repository.OperacionCuentaRepository;
import com.banca.digital.servicios.CuentaBancariaService;
import com.banca.digital.mappers.CuentaBancariaMapperImpl;

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

	@Autowired
	private CuentaBancariaMapperImpl cuentaBancariaMapperImpl; 
	
	/*
	public CuentaBancariaServiceImpl(CuentaBancariaRepository cuentaBancariaR) {
        super();
        System.out.println("Cuenta Bancaria Repo:= "+this.cuentaBancariaR);
        this.cuentaBancariaR = cuentaBancariaR;
    }*/

	
	@Override
	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
		log.info("Guradando un nuevo cliente");
		Cliente cliente=cuentaBancariaMapperImpl.mapperDeClienteDTO(clienteDTO);
		Cliente clienteDB = clienteR.save(cliente);
		
		return cuentaBancariaMapperImpl.mapperDeCliente(clienteDB);
	}

	@Override
	public ClienteDTO getCliente(Long clienteId) throws ClienteNotFoundException {
		
		Cliente cliente = clienteR.findById(clienteId).orElseThrow(()-> new ClienteNotFoundException("Cliente no encontrado"));
		
		return cuentaBancariaMapperImpl.mapperDeCliente(cliente);
	}

	@Override
	public ClienteDTO updateClienteDTO(ClienteDTO clienteDTO) {
	log.info("Actualizando cliente");
	Cliente cliente=cuentaBancariaMapperImpl.mapperDeClienteDTO(clienteDTO);
	Cliente clienteDB=clienteR.save(cliente);
	return cuentaBancariaMapperImpl.mapperDeCliente(clienteDB);
	}	
	
	@Override
	public void deleteClienteDTO(Long clienteId) {
	clienteR.deleteById(clienteId);
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
		cuentaActual.setEstadoCuenta(EstadoCuenta.CREADA);
		
		CuentaActual cuentaActualDB = cuentaBancariaR.save(cuentaActual);
		return cuentaActualDB;
	}

	
	@Override
	public CuentaActualDTO saveCuentaBancariaActualDTO(double balanceInicial, double sobregiro, Long clienteId)
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
		cuentaActual.setEstadoCuenta(EstadoCuenta.CREADA);
		
		CuentaActual cuentaActualDB = cuentaBancariaR.save(cuentaActual);
		return cuentaBancariaMapperImpl.mapperDeCuentaActual(cuentaActualDB);
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
		cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
		
		CuentaAhorro cuentaAhorroDB = cuentaBancariaR.save(cuentaAhorro);
		return cuentaAhorroDB;
	}
	
	@Override
	public CuentaAhorroDTO saveCuentaBancariaAhorroDTO(double balanceInicial, double tasaInteres, Long clienteId)
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
		cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
		
		CuentaAhorro cuentaAhorroDB = cuentaBancariaR.save(cuentaAhorro);
		return cuentaBancariaMapperImpl.mapperDeAhorro(cuentaAhorroDB);
		
	}

	@Override
	public List<ClienteDTO> listCliente() {
		List<Cliente> clientes = clienteR.findAll();
		List<ClienteDTO> clienteDTOs = clientes.stream().map(cliente -> cuentaBancariaMapperImpl.mapperDeCliente(cliente)).collect(Collectors.toList());
		return clienteDTOs;
	}

	@Override
	public CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {
		CuentaBancaria cuentaBancaria = cuentaBancariaR.findById(cuentaId).orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta Bancaria No encontrada"));
		
		return cuentaBancaria;
	}
	@Override
	public CuentaBancariaDTO getCuentaBancariaDTO(String cuentaId) throws CuentaBancariaNotFoundException {
		CuentaBancaria cuentaBancaria = cuentaBancariaR.findById(cuentaId).orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta Bancaria No encontrada"));
		
		if(cuentaBancaria instanceof CuentaAhorro) {
			CuentaAhorro cuentaAhorro=(CuentaAhorro)cuentaBancaria;
			return cuentaBancariaMapperImpl.mapperDeAhorro(cuentaAhorro);
		}
		else{
			CuentaActual cuentaActual=(CuentaActual)cuentaBancaria;
			return cuentaBancariaMapperImpl.mapperDeCuentaActual(cuentaActual);
		}
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
		operacionCuenta.setFechaOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaR.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance()-monto);
		cuentaBancariaR.save(cuentaBancaria);
		
		
	}
	@Override
	public void debitDTO(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		CuentaBancaria cuentaBancaria = cuentaBancariaR.findById(cuentaId).orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta Bancaria No encontrada"));
		
		if(cuentaBancaria.getBalance()<monto) {
			throw new BalanceInsuficienteException("Balance insuficiente");
		}
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.DEBITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFechaOperacion(new Date());
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
		operacionCuenta.setFechaOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaR.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance()+monto);
		cuentaBancariaR.save(cuentaBancaria);
	}
	
	@Override
	public void creditDTO(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException {
		CuentaBancaria cuentaBancaria = cuentaBancariaR.findById(cuentaId).orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta Bancaria No encontrada"));
		
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.CREDITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFechaOperacion(new Date());
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
	@Override
	public List<CuentaBancariaDTO> listCuentaBancariasDTO() {
		List<CuentaBancaria> cuentaBancarias=cuentaBancariaR.findAll();
		List<CuentaBancariaDTO> cuentaBancariaDTOs= cuentaBancarias.stream().map(cuentaBancaria ->{
			if(cuentaBancaria instanceof CuentaAhorro) {
				CuentaAhorro cuentaAhorro=(CuentaAhorro)cuentaBancaria;
				return cuentaBancariaMapperImpl.mapperDeAhorro(cuentaAhorro);
			}
			else{
				CuentaActual cuentaActual=(CuentaActual)cuentaBancaria;
				return cuentaBancariaMapperImpl.mapperDeCuentaActual(cuentaActual);
			}
		}).collect(Collectors.toList());
		return cuentaBancariaDTOs;
	}

	@Override
	public void transferDTO(String cuentaIdPropietario, String cuentaIdDestinatario, double monto)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<OperacionCuentaDTO> listHistorialDeCuentas(String cuentaId){
		List<OperacionCuenta> operacionCuentas = operacionCuentaR.findByCuentaBancariaId(cuentaId);
		return operacionCuentas.stream().map(operacionCuenta -> cuentaBancariaMapperImpl
				.mappearDeOperacionCuenta(operacionCuenta))
				.collect(Collectors.toList());
	}
	
	@Override
	public HistorialCuentaDTO getHistorialCuenta(String cuentaId, int page, int size) throws CuentaBancariaNotFoundException{ 
		CuentaBancaria cuentaBancaria=cuentaBancariaR.findById(cuentaId).orElse(null);
		if (cuentaBancaria==null) {
			throw new CuentaBancariaNotFoundException("cuenta no encontrada");
			
		}
		Page<OperacionCuenta> operacionesCuenta= operacionCuentaR.findByCuentaBancariaId(cuentaId,PageRequest.of(page, size));
		HistorialCuentaDTO historialCuentaDTO= new HistorialCuentaDTO();
		List<OperacionCuentaDTO> operacionCuentaDTO =operacionesCuenta.getContent()
				.stream()
				.map(operacionCuenta -> cuentaBancariaMapperImpl
						.mappearDeOperacionCuenta(operacionCuenta))
						.collect(Collectors.toList()
					);
		historialCuentaDTO.setOperacionCuentaDTO(operacionCuentaDTO);
		historialCuentaDTO.setCuentaId(cuentaBancaria.getId());
		historialCuentaDTO.setBalance(cuentaBancaria.getBalance());
		historialCuentaDTO.setCurrentPage(page);
		historialCuentaDTO.setPageSize(size);
		historialCuentaDTO.setTotalPages(operacionesCuenta.getTotalPages());
		return historialCuentaDTO;
		
	}

	@Override
	public HistorialCuentaDTO getHistorialCuentaDesc(String cuentaId, int page, int size) throws CuentaBancariaNotFoundException{ 
		CuentaBancaria cuentaBancaria=cuentaBancariaR.findById(cuentaId).orElse(null);
		if (cuentaBancaria==null) {
			throw new CuentaBancariaNotFoundException("cuenta no encontrada");
			
		}
		Page<OperacionCuenta> operacionesCuenta= operacionCuentaR.findByCuentaBancariaIdOrderByFechaOperacionAsc(cuentaId, PageRequest.of(page, size));
		HistorialCuentaDTO historialCuentaDTO= new HistorialCuentaDTO();
		List<OperacionCuentaDTO> operacionCuentaDTO =operacionesCuenta.getContent()
				.stream()
				.map(operacionCuenta -> cuentaBancariaMapperImpl
						.mappearDeOperacionCuenta(operacionCuenta))
						.collect(Collectors.toList()
					);
		historialCuentaDTO.setOperacionCuentaDTO(operacionCuentaDTO);
		historialCuentaDTO.setCuentaId(cuentaBancaria.getId());
		historialCuentaDTO.setBalance(cuentaBancaria.getBalance());
		historialCuentaDTO.setCurrentPage(page);
		historialCuentaDTO.setPageSize(size);
		historialCuentaDTO.setTotalPages(operacionesCuenta.getTotalPages());
		return historialCuentaDTO;
		
	}

	
	
	@Override
	public List<ClienteDTO> searchClientes(String keyword) {
		List<Cliente> clientes= clienteR.searchCliente(keyword);
		List<ClienteDTO> clienteDTOs = clientes.stream().map(cliente -> cuentaBancariaMapperImpl.mapperDeCliente(cliente)).collect(Collectors.toList());
		return clienteDTOs;
	}
	
}
