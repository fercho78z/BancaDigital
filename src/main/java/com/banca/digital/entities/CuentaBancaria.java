package com.banca.digital.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.banca.digital.enums.EstadoCuenta;

import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//esto hace que todos los tipo de cuentas bancarias heredan de esta clase
@DiscriminatorColumn(name="Tipo", length=4)
@Data
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)//esto hace que ahora se creen las tablas y no columnas heredando de esta clase cuenta actual y cuanta ahorro
//@Inheritance(strategy=InheritanceType.JOINED) hace que se cree otra la tabla cuenta bancaria mas las otras de cuenta actual y ahorro
@AllArgsConstructor
@NoArgsConstructor
@Entity
//public abstract class CuentaBancaria {  //se usa abstarct para crear las tablas  en InheritanceType.TABLE_PER_CLASS y JOINED
public class CuentaBancaria {

	@Id
	private String Id; //Es de tipo string pq el metodo de UUID.random no acepta Long
	
	private double balance;
	private Date fechaCreacion;

	@ManyToOne
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private EstadoCuenta estadoCuenta;
	
	//@OneToMany(mappedBy="cuentaBancaria")
	@OneToMany(mappedBy="cuentaBancaria", fetch=FetchType.LAZY)  // aparace la tabla solo cuando se usa lazy si se usa EAGER aparace en todo momento sin que se le llame
	private List<OperacionCuenta> operacionesCuenta;


}
