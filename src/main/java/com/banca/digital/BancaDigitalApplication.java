package com.banca.digital;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.entities.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.repository.ClienteRepository;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.repository.OperacionCuentaRepository;
import com.banca.digital.servicios.BancoService;
import com.banca.digital.servicios.CuentaBancariaService;

@SpringBootApplication
//@EnableJpaRepositories("com.banca.digital.repository")
//@EntityScan("com.banca.digital.entities")
public class BancaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancaDigitalApplication.class, args);
	}
/*
	//@Bean
	CommandLineRunner commandLineRunner(BancoService bancoService) {
		return arg -> {
			bancoService.cosultar();
		};
	}
	
	
	//@Bean se comenta pq ya se usaran services ya no es necesario llamar ademas de que ya  tenemos info en la base de datos
	//CommandLineRunner start(ClienteRepository clienteR, CuentaBancariaRepository cuentaBancariaRepository, OperacionCuentaRepository operacionCuentaRepository) {
		//@Bean
		CommandLineRunner start(CuentaBancariaService cuentaBancariaService) {
		return args ->{
			
			Stream.of("David","Angel","Karen").forEach(nombre -> {
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setEmail(nombre+"@gmail.com");
				cuentaBancariaService.saveCliente(cliente);
			});
			
				cuentaBancariaService.listCliente().forEach(cliente->{
					try {
							
						cuentaBancariaService.saveCuentaBancariaActual(Math.random()*90000, 9000, cliente.getId());
						cuentaBancariaService.saveCuentaBancariaAhorro(Math.random()*120000, 5.5, cliente.getId());
						List<CuentaBancaria> cuentaBancarias = cuentaBancariaService.listCuentaBancarias();
						
						for(CuentaBancaria cuentaBancaria:cuentaBancarias) {
							for(int i=0;i<10;i++) {
								cuentaBancariaService.credit(cuentaBancaria.getId(), 10000+Math.random()*120000, "Credito");
								cuentaBancariaService.debit(cuentaBancaria.getId(), 1000+Math.random()*90000, "Debito");
										
							}
						}
						
					}
					catch (Exception e) {
						// TODO: handle exception
					}
			
			});
		
			/*clienteR.findAll().forEach(cliente -> {
				
				CuentaActual cuentaActual = new CuentaActual();
				cuentaActual.setId(UUID.randomUUID().toString());
				cuentaActual.setBalance(Math.random()*90000);
				cuentaActual.setFechaCreacion(new Date());
				cuentaActual.setCliente(cliente);
				cuentaActual.setSobregiro(9000);
				cuentaBancariaRepository.save(cuentaActual);
				
				CuentaAhorro cuentaAhorro = new CuentaAhorro();
				cuentaAhorro.setId(UUID.randomUUID().toString());
				cuentaAhorro.setBalance(Math.random()*90000);
				cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
				cuentaAhorro.setCliente(cliente);
				cuentaAhorro.setTasaDeInteres(5.5);
				cuentaBancariaRepository.save(cuentaAhorro);
				
				
			});
			
			cuentaBancariaRepository.findAll().forEach(cuentaBancaria -> {
				
				for(int i=0; i<10;i++) {
					
					OperacionCuenta operacionCuenta =  new OperacionCuenta();
					operacionCuenta.setFecheOperacion(new Date());
					operacionCuenta.setMonto(Math.random()*12000);
					operacionCuenta.setTipoOperacion(Math.random()>05? TipoOperacion.DEBITO:TipoOperacion.CREDITO);
					operacionCuenta.setCuentaBancaria(cuentaBancaria);
					operacionCuentaRepository.save(operacionCuenta);
				}
				
			});*/
/*			
	  };
	}*/
	
	
	//Este metodo es para crear datos apratir de las clases DTO
	//@Bean
			CommandLineRunner start(CuentaBancariaService cuentaBancariaService) {
			return args ->{
				
				Stream.of("David","Angel","Karen","Pedro","Roberto","Denisse").forEach(nombre -> {
					ClienteDTO cliente = new ClienteDTO();
					cliente.setNombre(nombre);
					cliente.setEmail(nombre+"@gmail.com");
					cuentaBancariaService.saveClienteDTO(cliente);
				});
				
					cuentaBancariaService.listCliente().forEach(cliente->{
						try {
								
							cuentaBancariaService.saveCuentaBancariaActual(Math.random()*90000, 9000, cliente.getId());
							cuentaBancariaService.saveCuentaBancariaAhorro(Math.random()*120000, 5.5, cliente.getId());
							List<CuentaBancariaDTO> cuentaBancarias = cuentaBancariaService.listCuentaBancariasDTO();
							
							for(CuentaBancariaDTO cuentaBancaria:cuentaBancarias) {
								for(int i=0;i<10;i++) {
									String cuentaId;
									if(cuentaBancaria instanceof CuentaAhorroDTO) {
										cuentaId = ((CuentaAhorroDTO)cuentaBancaria).getId();
									}else {
										cuentaId = ((CuentaActualDTO)cuentaBancaria).getId();
									}
										
									cuentaBancariaService.credit(cuentaId, 10000+Math.random()*120000, "Credito");
									cuentaBancariaService.debit(cuentaId, 1000+Math.random()*90000, "Debito");
											
								}
							}
							
						}
						catch (Exception e) {
							// TODO: handle exception
						}
				
				});
			
				/*clienteR.findAll().forEach(cliente -> {
					
					CuentaActual cuentaActual = new CuentaActual();
					cuentaActual.setId(UUID.randomUUID().toString());
					cuentaActual.setBalance(Math.random()*90000);
					cuentaActual.setFechaCreacion(new Date());
					cuentaActual.setCliente(cliente);
					cuentaActual.setSobregiro(9000);
					cuentaBancariaRepository.save(cuentaActual);
					
					CuentaAhorro cuentaAhorro = new CuentaAhorro();
					cuentaAhorro.setId(UUID.randomUUID().toString());
					cuentaAhorro.setBalance(Math.random()*90000);
					cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
					cuentaAhorro.setCliente(cliente);
					cuentaAhorro.setTasaDeInteres(5.5);
					cuentaBancariaRepository.save(cuentaAhorro);
					
					
				});
				
				cuentaBancariaRepository.findAll().forEach(cuentaBancaria -> {
					
					for(int i=0; i<10;i++) {
						
						OperacionCuenta operacionCuenta =  new OperacionCuenta();
						operacionCuenta.setFecheOperacion(new Date());
						operacionCuenta.setMonto(Math.random()*12000);
						operacionCuenta.setTipoOperacion(Math.random()>05? TipoOperacion.DEBITO:TipoOperacion.CREDITO);
						operacionCuenta.setCuentaBancaria(cuentaBancaria);
						operacionCuentaRepository.save(operacionCuenta);
					}
					
				});*/
				
		  };
		}
	
	
	
}


