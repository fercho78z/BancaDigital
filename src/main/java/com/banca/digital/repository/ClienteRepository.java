package com.banca.digital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banca.digital.entities.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
@Query("select c from Cliente c where c.nombre LIKE :kw")
List<Cliente> searchCliente(@Param(value = "kw") String keyword);

}
