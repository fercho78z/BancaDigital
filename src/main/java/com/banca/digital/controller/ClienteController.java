package com.banca.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entities.Cliente;
import com.banca.digital.entities.CuentaBancaria;
import com.banca.digital.exception.ClienteNotFoundException;
import com.banca.digital.repository.CuentaBancariaRepository;
import com.banca.digital.servicios.CuentaBancariaService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/clientes")
	public List<ClienteDTO> listarClientes(){
		return cuentaBancariaService.listCliente();
	}
	
	@GetMapping("/clientes/{id}")
	public ClienteDTO listarDatosDelCliente(@PathVariable(name="id") Long clienteId) throws ClienteNotFoundException {
		return cuentaBancariaService.getCliente(clienteId);
	}

	@PostMapping("/clientes")
	public ClienteDTO guardarClienteDTO(@RequestBody ClienteDTO clienteDTO) {
		return cuentaBancariaService.saveClienteDTO(clienteDTO);
	}
	@PutMapping("/clientes/{clienteId}")
	public ClienteDTO actualizarCliente(@PathVariable Long clienteId,@RequestBody ClienteDTO clienteDTO) {
		clienteDTO.setId(clienteId);
		return cuentaBancariaService.updateClienteDTO(clienteDTO);
	}
	@DeleteMapping("/clientes/{id}")
	public void eliminarCliente(@PathVariable Long id) {
		cuentaBancariaService.deleteClienteDTO(id);
	}
	@GetMapping("/clientes/search")
	public List<ClienteDTO> buscarCliente(@RequestParam(name="keyword",defaultValue="") String keyword) {
			return cuentaBancariaService.searchClientes("%"+keyword+"%");
	}
}
