package com.banca.digital;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.repository.ClienteRepository;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.repository.OperacionCuentaRepository;

@SpringBootApplication
@EnableJpaRepositories("com.banca.digital.repository")
@EntityScan("com.banca.digital.entities")
public class BancaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancaDigitalApplication.class, args);
	}

	
	@Bean
	CommandLineRunner start(ClienteRepository clienteR, CuentaBancariaRepository cuentaBancariaRepository, OperacionCuentaRepository operacionCuentaRepository) {
		
		return args ->{
			
			Stream.of("David","Angel","Karen").forEach(nombre -> {
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setEmail(nombre+"@gmail.com");
				clienteR.save(cliente);
			});
		
			clienteR.findAll().forEach(cliente -> {
				
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
				
			});
			
	  };
	}
}


