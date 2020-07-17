package com.estudos.boot.dao;

import java.util.List;

import com.estudos.boot.domain.Cargo;

public interface CargoDao {
	
	
	 void save(Cargo entity); 	
	 void update(Cargo entity);	
	 void delete(Long id);
	 Cargo findById(Long id);
	 List<Cargo> findAll();
	

}
