package com.banca.digital.servicios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banca.digital.entities.CuentaActual;
import com.banca.digital.entities.CuentaAhorro;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.entities.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.repository.CuentaBancariaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BancoService {
@Autowired
private CuentaBancariaRepository cuentaBancariaRepository;
public void cosultar() {
	CuentaBancaria cuentaBancariaBBDD=cuentaBancariaRepository.findById("1b08ace4-84f7-465b-bfed-614751276cc2").orElse(null);
		System.out.println("ID :" + cuentaBancariaBBDD.getId());
		System.out.println("Balance :" +	cuentaBancariaBBDD.getBalance());
		System.out.println("Estado :" +	cuentaBancariaBBDD.getEstadoCuenta());
		System.out.println("Cliente :" +	cuentaBancariaBBDD.getCliente().getNombre());
		System.out.println("Fecha de Creacion :" + cuentaBancariaBBDD.getFechaCreacion());
		System.out.println("Nombre de la clase:" +	cuentaBancariaBBDD.getClass().getSimpleName());
	
		if(cuentaBancariaBBDD instanceof CuentaActual) {
			System.out.println("sobregiro: " + ((CuentaActual)cuentaBancariaBBDD).getSobregiro());
		}
		else if(cuentaBancariaBBDD instanceof CuentaAhorro) {
			System.out.println("Tasa de interes: " + ((CuentaAhorro)cuentaBancariaBBDD).getTasaDeInteres());
				
		}
		
		cuentaBancariaBBDD.getOperacionesCuenta().forEach(operacionCuenta ->{
			
			System.out.println("------------------");
			System.out.println("Tipo de operacion: " + operacionCuenta.getTipoOperacion());
			System.out.println("Fecha de creacion: " + operacionCuenta.getFechaOperacion());
			System.out.println("Monto: " + operacionCuenta.getMonto());
			
		});
}
}
