package com.banca.digital.dto;

import java.util.List;

import lombok.Data;

@Data
public class HistorialCuentaDTO {

	private String cuentaId;
	private double balance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<OperacionCuentaDTO> operacionCuentaDTO;
}

