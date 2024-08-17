package com.banca.digital.dto;

import java.util.Date;

import com.banca.digital.enums.TipoOperacion;

import lombok.Data;

@Data
public class OperacionCuentaDTO {
	private Long id;
	private Date fechaDeOperacion;
	private double monto;
	private TipoOperacion tipoOperacion;
	private String descripcion;
}
