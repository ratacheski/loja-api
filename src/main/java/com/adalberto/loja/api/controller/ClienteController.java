package com.adalberto.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adalberto.loja.domain.model.Cliente;
import com.adalberto.loja.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public List<Cliente> listar() {

		return repository.findAll();
	}
	@PostMapping
	public Cliente cadastrar(@Valid @RequestBody Cliente cliente) {
		return repository.save(cliente);
		
	}

}
