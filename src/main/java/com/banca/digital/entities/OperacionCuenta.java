package com.banca.digital.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OperacionCuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Date fecheOperacion;
	private double monto;
	@Enumerated(EnumType.STRING)
	private TipoOperacion tipoOperacion;
	@ManyToOne 
	private CuentaBancaria cuentaBancaria;


}
