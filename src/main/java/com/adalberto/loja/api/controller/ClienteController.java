package com.adalberto.loja.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adalberto.loja.api.model.ClienteRepresentationModel;
import com.adalberto.loja.api.model.input.ClienteInput;
import com.adalberto.loja.domain.model.Cliente;
import com.adalberto.loja.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<ClienteRepresentationModel> listar() {

		List<Cliente> clientes = repository.findAll();
		return clientes.stream().map(cliente -> mapper.map(cliente, ClienteRepresentationModel.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	public ClienteRepresentationModel cadastrar(@Valid @RequestBody ClienteInput clienteInput) {

		Cliente cliente = mapper.map(clienteInput, Cliente.class);
		Cliente clienteSalvo = repository.save(cliente);
		return mapper.map(clienteSalvo, ClienteRepresentationModel.class);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {

		boolean clienteExistente = repository.existsById(id);
		if (clienteExistente) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
