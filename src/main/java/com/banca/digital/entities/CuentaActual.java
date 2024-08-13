package com.banca.digital.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("CA") //sobregiro cuenta actual
@NoArgsConstructor
@AllArgsConstructor
public class CuentaActual extends CuentaBancaria {
	private double sobregiro;
}
