package com.estudos.boot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	
	@NotBlank(message = "Informe um nome.")
    @Size(min = 3, max = 60,message = "O nome do apartamento deve ter entre {min} e {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = false,length = 60)	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos = new ArrayList<>();

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
}
