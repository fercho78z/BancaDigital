package com.banca.digital.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nombre;
	private String email;

	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
	//private List<CuentaBancaria> cuentaBancaria = new ArrayList<>();
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY) // solo se usara la lista cuando sea escritura sino tare muchos datos sobrecargando la pagina
	private List<CuentaBancaria> cuentaBancaria;

}
