package com.banca.digital.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.digital.entities.OperacionCuenta;

@Repository
public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Long>{

	List<OperacionCuenta> findByCuentaBancariaId(String cuentaId);
	Page<OperacionCuenta> findByCuentaBancariaId(String cuentaId, PageRequest pageRequest);
	Page<OperacionCuenta> findByCuentaBancariaIdOrderByFechaOperacionAsc(String cuentaId,Pageable pageable);  //si cambiamos Asc por Desc se odrdena por de mayor a menor por fecha

	
}
