package com.estudos.boot.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudos.boot.domain.Departamento;
import com.estudos.boot.services.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Departamento> departamentos = new ArrayList<>();
		departamentos = departamentoService.buscarTodos();
		model.addAttribute("departamentos", departamentos);
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result ,RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			 return "/departamento/cadastro";
		}
		
		departamentoService.salvar(departamento);
		attr.addFlashAttribute("success","Departamento incluido com sucesso");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id,ModelMap model) {
		model.addAttribute("departamento", departamentoService.buscarPorId(id));
		return "/departamento/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result ,RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			 return "/departamento/cadastro";
		}
		
		departamentoService.editar(departamento);
		attr.addFlashAttribute("success","Departamento editado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id,ModelMap model) {
		if(departamentoService.departamentoTemCargos(id)) {
			model.addAttribute("fail","Departamento nao removido.Possui cargo(s) vinculados(s).");
		}
		else {			
		model.addAttribute("success","Departamento removido com sucesso");
		departamentoService.excluir(id);
		}
		return listar(model);
	}


}
