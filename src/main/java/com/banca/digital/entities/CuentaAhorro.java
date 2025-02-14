package com.banca.digital.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("SA") // no se crea la entidad sino una columna que se llama cuenta de ahorro "SA" en ingles y hereda atributos de cuenta bancaria
@NoArgsConstructor
@AllArgsConstructor

public class CuentaAhorro extends CuentaBancaria{
	
	private double tasaDeInteres;
}
