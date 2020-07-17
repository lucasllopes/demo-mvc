package com.estudos.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.estudos.boot.domain.Cargo;
import com.estudos.boot.domain.Departamento;
import com.estudos.boot.services.CargoService;
import com.estudos.boot.services.DepartamentoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService cargoService;
	
	@Override
	public Cargo convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		return cargoService.buscarPorId(Long.parseLong(text));
	}

}
