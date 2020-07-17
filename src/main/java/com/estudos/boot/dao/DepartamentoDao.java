package com.estudos.boot.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.estudos.boot.domain.Departamento;


public interface DepartamentoDao {
	
	 void save(Departamento entity); 	
	 void update(Departamento entity);	
	 void delete(Long id);
	 Departamento findById(Long id);
	 List<Departamento> findAll();
	
	

}
