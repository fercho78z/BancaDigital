package com.banca.digital.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.banca.digital.enums.EstadoCuenta;

import jakarta.persistence.Enumerated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//esto hace que todos los tipo de cuentas bancarias heredan de esta clase
@DiscriminatorColumn(name="Tipo", length=4)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CuentaBancaria {

	@Id
	private String Id; //Es de tipo string pq el metodo de UUID.random no acepta Long
	
	private double balance;
	private Date fechaCreacion;

	@ManyToOne
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private EstadoCuenta estadoCuenta;
	
	@OneToMany(mappedBy="cuentaBancaria")
	private List<OperacionCuenta> operacionesCuenta;


}
