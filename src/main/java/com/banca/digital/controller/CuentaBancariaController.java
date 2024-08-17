package com.banca.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banca.digital.dto.CreditoDTO;
import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.dto.DebitoDTO;
import com.banca.digital.dto.HistorialCuentaDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
import com.banca.digital.dto.TransferenciaRequestDTO;
import com.banca.digital.exception.BalanceInsuficienteException;
import com.banca.digital.exception.CuentaBancariaNotFoundException;
import com.banca.digital.servicios.CuentaBancariaService;

@RestController
@RequestMapping("/api/v1")
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/cuentas/{cuentaid}")
	public CuentaBancariaDTO  listarDatosBancariosDTO(@PathVariable String cuentaid) throws CuentaBancariaNotFoundException{
	return cuentaBancariaService.getCuentaBancariaDTO(cuentaid);
	}
	@GetMapping("/cuentas")
	public List<CuentaBancariaDTO> listarCuentaBancariasDTO(){
	return cuentaBancariaService.listCuentaBancariasDTO();
	}
	
	@GetMapping("/cuentas/{cuentaId}/operaciones")
	public List<OperacionCuentaDTO> listarhistorialDeCuenta(@PathVariable String cuentaId){
		return cuentaBancariaService.listHistorialDeCuentas(cuentaId);
	}
	
	@GetMapping("/cuentas/{cuentaId}/pageOperaciones")
	public HistorialCuentaDTO listarhistorialDeCuentaPaginado(@PathVariable String cuentaId,@RequestParam(name="page",defaultValue = "0") int page, @RequestParam(name="size",defaultValue = "5") int size) throws CuentaBancariaNotFoundException{
		return cuentaBancariaService.getHistorialCuenta(cuentaId,page,size);
	}
	@GetMapping("/cuentas/{cuentaId}/pageOperacionesOrder")
	public HistorialCuentaDTO listarhistorialDeCuentaPaginadoOrder(@PathVariable String cuentaId,@RequestParam(name="page",defaultValue = "0") int page, @RequestParam(name="size",defaultValue = "5") int size) throws CuentaBancariaNotFoundException{
		return cuentaBancariaService.getHistorialCuentaDesc(cuentaId,page,size);
	}
	
	@PostMapping("/cuentas/debito")
	public DebitoDTO realizarDebito(@RequestBody DebitoDTO debitoDTO) throws CuentaBancariaNotFoundException, BalanceInsuficienteException { 
	cuentaBancariaService.debit(debitoDTO.getCuentaId(),debitoDTO.getMonto(),debitoDTO.getDescripcion());
	return debitoDTO;
	}
	
	@PostMapping("/cuentas/credito")
	public CreditoDTO realizarCredito(@RequestBody CreditoDTO creditoDTO) throws CuentaBancariaNotFoundException { 
	cuentaBancariaService.credit(creditoDTO.getCuentaId(),creditoDTO.getMonto(),creditoDTO.getDescripcion());
	return creditoDTO;
	}
	
	@PostMapping("/cuentas/transferencia")
	public void realizarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) throws CuentaBancariaNotFoundException, BalanceInsuficienteException { 
	cuentaBancariaService.transfer(transferenciaRequestDTO.getCuentaPropietario(),transferenciaRequestDTO.getCuentaDestinatario(),transferenciaRequestDTO.getMonto());
	
	}
	
}
