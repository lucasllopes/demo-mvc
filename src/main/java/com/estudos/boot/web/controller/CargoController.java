package com.estudos.boot.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudos.boot.domain.Cargo;
import com.estudos.boot.domain.Departamento;
import com.estudos.boot.services.CargoService;
import com.estudos.boot.services.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos",cargoService.buscarTodos());
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result,RedirectAttributes attr) {
		
		if(result.hasErrors()) {
		 return "/cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success","Cargo incluido com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(ModelMap model) {
		
		return departamentoService.buscarTodos();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id,ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "/cargo/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			 return "/cargo/cadastro";
			}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success","Cargo editado com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id,ModelMap model) {
		if(cargoService.cargoTemFuncionarios(id)) {
			model.addAttribute("fail","Cargo nao removido. Possui funcionarios(s) vinculados(s).");
		}
		else {		
		cargoService.excluir(id);
		model.addAttribute("success","Cargo removido com sucesso");
		}
		return listar(model);
	}

	

}
