package com.banca.digital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("CA") //sobregiro cuenta actual
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaActual extends CuentaBancaria {
	private double sobregiro;
	@Column(insertable=false, updatable=false)
	private String tipo;
}
