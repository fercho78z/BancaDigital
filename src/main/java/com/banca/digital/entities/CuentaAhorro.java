package com.banca.digital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("SA") // no se crea la entidad sino una columna que se llama cuenta de ahorro "SA" en ingles y hereda atributos de cuenta bancaria
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CuentaAhorro extends CuentaBancaria{
	@Column(insertable=false, updatable=false)
	private String tipo;
	private double tasaDeInteres;
}
